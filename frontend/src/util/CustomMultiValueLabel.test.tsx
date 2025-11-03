import React from "react";
import { render, screen } from "@testing-library/react";
import { components } from "react-select";
import { CustomMultiValueLabel } from "./CustomMultiValueLabel";

describe("CustomMultiValueLabel", () => {
  it("renders the label without leading spaces", () => {
    render(<CustomMultiValueLabel data={{ label: "   Category Name" }} />);
    expect(screen.getByText("Category Name")).toBeInTheDocument();
  });

  it("renders the label without leading non-breaking spaces", () => {
    render(<CustomMultiValueLabel data={{ label: "\u00A0\u00A0Subcategory Name" }} />);
    expect(screen.getByText("Subcategory Name")).toBeInTheDocument();
  });



  it("renders correctly with react-select props", () => {
    render(<CustomMultiValueLabel data={{ label: "   Manager" }} selectProps={{}} />);
    expect(screen.getByText("Manager")).toBeTruthy();
  });
});
