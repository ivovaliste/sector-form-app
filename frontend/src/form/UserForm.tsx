// UserForm.tsx
import React, { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { updateUser, useUser } from "../api/user";
import { useCategories } from "../api/sectors";
import { UserFormValues, userSchema } from "./schema";
import { UserDTO, Category } from "../types/user";
import {
  CustomButton,
  Checkbox,
  MultiSelect,
  Alert,
  TextInput,
  RenderError,
} from "../components";

type Props = {
  onSubmitSuccess?: () => void;
};

const UserForm: React.FC<Props> = ({ onSubmitSuccess }) => {
  const [userId, setUserId] = useState<number | undefined>(() => {
    const storedId = sessionStorage.getItem("userId");
    return storedId ? Number(storedId) : undefined;
  });
  const [successMessage, setSuccessMessage] = useState<string>("");

  const { data: categories } = useCategories();
  const { data: user } = useUser(userId);

  const {
    control,
    register,
    handleSubmit,
    reset,
    watch,
    formState: { errors },
  } = useForm<UserFormValues>({
    resolver: zodResolver(userSchema),
    defaultValues: {
      name: "",
      subcategoryIds: [],
      agreedToTerms: false,
    },
  });




useEffect(() => {
  if (user && categories) {
    reset({
      name: user.name,
      subcategoryIds: user.sectorIds,
      agreedToTerms: user.agreedToTerms,
    });
  }
}, [user, categories, reset]);

  const onSubmit = async (data: UserFormValues) => {
    const payload: UserDTO = {
      id: userId,
      name: data.name,
      sectorIds: data.subcategoryIds,
      agreedToTerms: data.agreedToTerms,
    };
    const updatedUser = await updateUser(payload);
    setSuccessMessage(
      userId ? "User updated successfully!" : "User created successfully!"
    );
    setUserId(updatedUser.id);
    sessionStorage.setItem("userId", updatedUser.id?.toString() || "");
    if (onSubmitSuccess) onSubmitSuccess();
  };

  return (
    <form
      onSubmit={handleSubmit(onSubmit)}
      className="max-w-lg mx-auto mt-10 bg-white shadow-lg rounded-2xl p-6 space-y-6"
    >
      <Alert
        message={successMessage}
        duration={2000}
        type="success"
        onClose={() => setSuccessMessage("")}
      />

      <TextInput label="Name" name="name" register={register} />
        {RenderError(errors.name)}
        <MultiSelect
          key={user?.id ?? "new"}
          control={control}
          name="subcategoryIds"
          label="Categories"
          categories={categories || []}
        />
        {RenderError(errors.subcategoryIds)}
      <Checkbox label="Agreed to Terms" register={register("agreedToTerms")} />
        {RenderError(errors.agreedToTerms)}
      <CustomButton
        text={userId ? "Update User" : "Create User"}
        color={userId ? "green" : "blue"}
        type="submit"
      />
    </form>
  );
};

export default UserForm;
