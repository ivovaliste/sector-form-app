import React from "react";

type Props = {
  text: string;
};

export const InfoText: React.FC<Props> = ({
  text = "Please enter your name and pick the Sectors you are currently involved in.",
}) => {
  return <div className="mt-4 text-gray-700">{text}</div>;
};
