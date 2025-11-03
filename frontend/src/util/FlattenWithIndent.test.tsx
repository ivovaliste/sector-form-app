
import { FlattenWithIndent } from "./FlattenWithIndent";
import { Category } from "../types/user";

describe("FlattenWithIndent", () => {
  it("should flatten a simple one-level category list", () => {
    const categories: Category[] = [
      { id: 1, name: "Category 1", children: null },
      { id: 2, name: "Category 2", children: null },
    ];

    const result = FlattenWithIndent(categories);

    expect(result).toEqual([
      { value: 1, label: "Category 1" },
      { value: 2, label: "Category 2" },
    ]);
  });

  it("should flatten nested categories with indentation", () => {
    const categories: Category[] = [
      {
        id: 1,
        name: "Parent",
        children: [
          {
            id: 2,
            name: "Child",
            children: [
              { id: 3, name: "Grandchild", children: null },
            ],
          },
        ],
      },
    ];

    const result = FlattenWithIndent(categories);

    expect(result).toHaveLength(3);


    expect(result[0]).toEqual({ value: 1, label: "Parent" });


    expect(result[1].value).toBe(2);
    expect(result[1].label.endsWith("Child")).toBe(true);
    expect(result[1].label.length).toBeGreaterThan("Child".length); // ensures there is indentation


    expect(result[2].value).toBe(3);
    expect(result[2].label.endsWith("Grandchild")).toBe(true);
    expect(result[2].label.length).toBeGreaterThan("Grandchild".length); // ensures there is indentation
  });

  it("should handle empty category list", () => {
    const result = FlattenWithIndent([]);
    expect(result).toEqual([]);
  });
});
