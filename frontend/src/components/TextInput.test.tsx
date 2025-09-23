import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import { useForm } from "react-hook-form";
import { TextInput } from "./TextInput";

type FormValues = {
  name: string;
};

describe("TextInput", () => {
  it("renders the input", () => {
    const TestForm = () => {
      const { register } = useForm<FormValues>();
      return (
        <form>
          <TextInput label="Name" name="name" register={register} />
        </form>
      );
    };

    render(<TestForm />);

    // Query by name attribute
    const input = screen.getByRole("textbox", {
      hidden: true,
    }) as HTMLInputElement;

    // OR better: query by name directly
    const inputByName =
      screen.getByDisplayValue("") || screen.getByRole("textbox");
    const inputElement = screen.getByRole("textbox") as HTMLInputElement;

    expect(inputElement).toBeInTheDocument();
  });

  it("updates value when typing", () => {
    const TestForm = () => {
      const { register, watch } = useForm<FormValues>();
      const nameValue = watch("name", "");

      return (
        <form>
          <TextInput label="Name" name="name" register={register} />
          <p data-testid="value">{nameValue}</p>
        </form>
      );
    };

    render(<TestForm />);

    const input = screen.getByRole("textbox") as HTMLInputElement;
    fireEvent.change(input, { target: { value: "John Doe" } });

    expect(input.value).toBe("John Doe");
    expect(screen.getByTestId("value").textContent).toBe("John Doe");
  });
});
