import React, { useEffect, useState } from "react";

type AlertProps = {
  message: string;
  duration?: number; // in milliseconds, default 2000ms
  type?: "success" | "error" | "info";
  onClose?: () => void; // called when alert disappears
};

export const Alert: React.FC<AlertProps> = ({
  message,
  duration = 2000,
  type = "success",
  onClose,
}) => {
  const [visible, setVisible] = useState(!!message);

  useEffect(() => {
    if (!message) return;

    setVisible(true);

    const timer = setTimeout(() => {
      setVisible(false);
      if (onClose) onClose();
    }, duration);

    return () => clearTimeout(timer);
  }, [message, duration, onClose]);

  if (!visible || !message) return null;

  const bgColor =
    type === "success"
      ? "bg-green-100 border-green-400 text-green-700"
      : type === "error"
      ? "bg-red-100 border-red-400 text-red-700"
      : "bg-blue-100 border-blue-400 text-blue-700";

  return (
    <div className={`${bgColor} border px-4 py-3 rounded relative`}>
      {message}
    </div>
  );
};
