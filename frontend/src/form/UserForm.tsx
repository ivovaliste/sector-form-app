import React, { useState, useEffect } from "react";
import { GroupBase } from "react-select";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";

import { updateUser, useUser } from "../api/user";
import { useCategories } from "../api/sectors";
import { UserFormValues, userSchema } from "./schema";
import { UserRequest, Option } from "../types/user";
import {
  CustomButton,
  Checkbox,
  MultiSelect,
  Alert,
  TextInput,
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
    if (user) {
      reset({
        name: user.name,
        subcategoryIds: user.subcategoryIds,
        agreedToTerms: user.agreedToTerms,
      });
    }
  }, [user, reset]);

  const groupedOptions: GroupBase<Option>[] =
    categories?.map((cat) => ({
      label: cat.name,
      options: cat.subcategories.map((sub) => ({
        value: sub.id,
        label: sub.name,
      })),
    })) ?? [];

  const onSubmit = async (data: UserFormValues) => {
    const payload: UserRequest = {
      id: userId,
      name: data.name,
      subcategoryIds: data.subcategoryIds,
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
      <div className="mt-6">
        {errors.name && (
          <p className="text-red-500  text-sm text-left mt-1">
            {errors.name.message}
          </p>
        )}
      </div>

      <MultiSelect
        control={control}
        name="subcategoryIds"
        label="Categories"
        groupedOptions={groupedOptions}
      />
      {errors.subcategoryIds && (
        <p className="text-red-500 text-sm text-left mt-1">
          {errors.subcategoryIds.message}
        </p>
      )}

      <Checkbox label="Agreed to Terms" register={register("agreedToTerms")} />
      {errors.agreedToTerms && (
        <p className="text-red-500 text-sm text-left mt-1">
          {errors.agreedToTerms.message}
        </p>
      )}

      <CustomButton
        text={userId ? "Update User" : "Create User"}
        color={userId ? "green" : "blue"}
        type="submit"
      />
    </form>
  );
};

export default UserForm;
