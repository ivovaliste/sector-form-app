import React from "react";
import { UseFormRegisterReturn } from "react-hook-form";

type CheckboxProps = {
  label: string;
  register: UseFormRegisterReturn;
};

export const Checkbox: React.FC<CheckboxProps> = ({ label, register }) => {
  return (
    <div className="flex items-center">
      <input
        type="checkbox"
        {...register}
        className="h-4 w-4 text-indigo-600 border-gray-300 rounded"
      />
      <label className="ml-2 block text-sm text-gray-700">{label}</label>
    </div>
  );
};
