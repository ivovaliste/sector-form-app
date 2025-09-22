import React, { useState, useEffect } from "react";
import Select, { GroupBase } from "react-select";
import { useForm, Controller } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";

import { updateUser, useUser } from "../api/user";
import { useCategories } from "../api/sectors";
import { UserFormValues, userSchema } from "./schema";
import { UserRequest, Option } from "../types/user";

type Props = {
  onSubmitSuccess?: () => void;
};

const UserForm: React.FC<Props> = ({ onSubmitSuccess }) => {
  const [userId, setUserId] = useState<number | undefined>(() => {
    const storedId = sessionStorage.getItem("userId");
    return storedId ? Number(storedId) : undefined;
  });

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

    setUserId(updatedUser.id);
    sessionStorage.setItem("userId", updatedUser.id?.toString() || "");

    if (onSubmitSuccess) onSubmitSuccess();
  };

  return (
    <form
      onSubmit={handleSubmit(onSubmit)}
      className="max-w-lg mx-auto mt-10 bg-white shadow-lg rounded-2xl p-6 space-y-6"
    >
      {/* Name */}
      <div>
        <label className="block  text-left font-medium text-gray-700 mb-2">
          Name
        </label>

        <input
          {...register("name")}
          className="w-full rounded-lg border border-gray-300 p-2 text-gray-900 focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
        {errors.name && (
          <p className="text-red-500  text-sm text-left mt-1">
            {errors.name.message}
          </p>
        )}
      </div>

      {/* Subcategories */}
      <div>
        <label className="block  text-left font-medium text-gray-700 mb-2">
          Subcategories
        </label>
        <Controller
          control={control}
          name="subcategoryIds"
          render={({ field }) => (
            <Select
              options={groupedOptions}
              isMulti
              className="text-sm mb-2"
              value={groupedOptions
                .flatMap((group) => group.options)
                .filter((opt) => field.value.includes(opt.value))}
              onChange={(selected) =>
                field.onChange((selected as Option[]).map((s) => s.value))
              }
            />
          )}
        />
        {errors.subcategoryIds && (
          <p className="text-red-500 text-sm text-left mt-1">
            {errors.subcategoryIds.message}
          </p>
        )}
      </div>

      {/* Checkbox */}
      <div className="flex items-center">
        <input
          type="checkbox"
          {...register("agreedToTerms")}
          className="h-4 w-4 text-indigo-600 border-gray-300 rounded"
        />
        <label className="ml-2 block text-sm text-gray-700">
          Agreed to Terms
        </label>
      </div>
      {errors.agreedToTerms && (
        <p className="text-red-500 text-sm text-left mt-1">
          {errors.agreedToTerms.message}
        </p>
      )}

      {/* Submit button */}
      <button
        type="submit"
        className="w-full bg-indigo-600 hover:bg-indigo-700 text-white font-medium py-2 px-4 rounded-lg shadow-md transition duration-200"
      >
        {userId ? "Update User" : "Create User"}
      </button>
    </form>
  );
};

export default UserForm;
