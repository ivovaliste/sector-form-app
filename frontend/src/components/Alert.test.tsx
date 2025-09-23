import React from "react";
import { render, screen } from "@testing-library/react";
import { act } from "react"; // ✅ import from react
import { Alert } from "./Alert";

jest.useFakeTimers();

describe("Alert component", () => {
  it("renders the alert message", () => {
    render(<Alert message="Success!" />);
    expect(screen.getByText("Success!")).toBeInTheDocument();
  });

  it("hides the alert after duration", () => {
    render(<Alert message="Success!" duration={2000} />);
    expect(screen.getByText("Success!")).toBeInTheDocument();

    act(() => {
      jest.advanceTimersByTime(2000);
    });

    expect(screen.queryByText("Success!")).not.toBeInTheDocument();
  });

  it("calls onClose when alert disappears", () => {
    const onCloseMock = jest.fn();
    render(<Alert message="Success!" duration={2000} onClose={onCloseMock} />);

    act(() => {
      jest.advanceTimersByTime(2000);
    });

    expect(onCloseMock).toHaveBeenCalledTimes(1);
  });

  it("does not render anything if message is empty", () => {
    render(<Alert message="" />);
    expect(screen.queryByText(/./)).not.toBeInTheDocument();
  });
});
