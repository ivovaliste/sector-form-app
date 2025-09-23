import { render, screen, fireEvent } from "@testing-library/react";
import { useForm } from "react-hook-form";
import { Checkbox } from "./Checkbox";

describe("Checkbox", () => {
  const Wrapper = () => {
    const { register } = useForm();
    return <Checkbox label="Agree to Terms" register={register("agreed")} />;
  };

  it("renders the label", () => {
    render(<Wrapper />);
    expect(screen.getByText(/agree to terms/i)).toBeInTheDocument();
  });

  it("can be checked and unchecked", () => {
    render(<Wrapper />);
    const checkbox = screen.getByRole("checkbox");
    expect(checkbox).not.toBeChecked();
    fireEvent.click(checkbox);
    expect(checkbox).toBeChecked();
    fireEvent.click(checkbox);
    expect(checkbox).not.toBeChecked();
  });
});
