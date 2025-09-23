import React from "react";

type ButtonProps = {
  text: string;
  color?: "blue" | "green";
  type?: "button" | "submit" | "reset";
  onClick?: () => void;
};

export const CustomButton: React.FC<ButtonProps> = ({
  text,
  color = "blue",
  type = "button",
  onClick,
}) => {
  const baseStyles =
    "w-full text-white font-medium py-2 px-4 rounded-lg shadow-md transition duration-200";

  const colorStyles =
    color === "blue"
      ? "bg-indigo-600 hover:bg-indigo-700"
      : "bg-green-600 hover:bg-green-700";

  return (
    <button
      type={type}
      onClick={onClick}
      className={`${baseStyles} ${colorStyles}`}
    >
      {text}
    </button>
  );
};
