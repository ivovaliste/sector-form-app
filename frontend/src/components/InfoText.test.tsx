import { render, screen } from "@testing-library/react";
import { InfoText } from "./InfoText";

describe("InfoText component", () => {
  it("renders the provided text prop", () => {
    const customText = "Custom information for the user.";
    render(<InfoText text={customText} />);
    const displayedText = screen.getByText(customText);
    expect(displayedText).toBeInTheDocument();
  });

  it("applies the correct CSS classes", () => {
    const text = "Test CSS";
    render(<InfoText text={text} />);
    const element = screen.getByText(text);
    expect(element).toHaveClass("mt-4");
    expect(element).toHaveClass("text-gray-700");
  });
});
