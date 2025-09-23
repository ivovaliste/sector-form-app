import { render, screen, fireEvent } from "@testing-library/react";
import { CustomButton } from "./Button";

describe("CustomButton", () => {
  it("renders with correct text and color", () => {
    render(<CustomButton text="Update User" color="green" type="button" />);
    const button = screen.getByRole("button", { name: /update user/i });
    expect(button).toBeInTheDocument();
    expect(button).toHaveClass("bg-green-600");
  });

  it("calls onClick handler when clicked", () => {
    const handleClick = jest.fn();
    render(<CustomButton text="Click Me" color="blue" onClick={handleClick} />);
    const button = screen.getByRole("button", { name: /click me/i });
    fireEvent.click(button);
    expect(handleClick).toHaveBeenCalledTimes(1);
  });
});
