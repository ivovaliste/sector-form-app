import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { useForm } from "react-hook-form";
import { GroupBase } from "react-select";
import { MultiSelect } from "./MultiSelect";

type Option = { value: string; label: string };

const groupedOptions: GroupBase<Option>[] = [
  {
    label: "Fruits",
    options: [
      { value: "1", label: "Apple" },
      { value: "2", label: "Banana" },
    ],
  },
  {
    label: "Vegetables",
    options: [
      { value: "3", label: "Carrot" },
      { value: "4", label: "Broccoli" },
    ],
  },
];

describe("MultiSelect", () => {
  const Wrapper = () => {
    const { control } = useForm({ defaultValues: { items: [] } });
    return (
      <MultiSelect
        control={control}
        name="items"
        label="Select Items"
        groupedOptions={groupedOptions}
      />
    );
  };

  it("renders the select input", () => {
    render(<Wrapper />);
    const selectInput = screen.getByRole("combobox");
    expect(selectInput).toBeInTheDocument();
  });
});
