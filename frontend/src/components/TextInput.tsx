import React from "react";
import { UseFormRegister } from "react-hook-form";

type TextInputProps = {
  label: string;
  name: string;
  register: UseFormRegister<any>;
  type?: string;
  placeholder?: string;
};

export const TextInput: React.FC<TextInputProps> = ({
  label,
  name,
  register,
  type = "text",
  placeholder = "",
}) => {
  return (
    <div>
      <label className="block text-left font-medium text-gray-700 mb-2">
        {label}
      </label>

      <input
        {...register(name)}
        type={type}
        placeholder={placeholder}
        className="w-full rounded-lg border border-gray-300 p-2 text-gray-900 focus:outline-none focus:ring-2 focus:ring-blue-500"
      />
    </div>
  );
};
