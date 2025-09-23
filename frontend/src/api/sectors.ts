import { useQuery } from "@tanstack/react-query";
import { api } from ".";
import { Category } from "../types/user";

export const fetchCategories = async (): Promise<Category[]> => {
  try {
    return await api.get("sectors").json<Category[]>();
  } catch (err) {
    console.error("Error fetching categories:", err);
    throw err;
  }
};

export const useCategories = () => {
  return useQuery<Category[], Error>({
    queryKey: ["categories"],
    queryFn: fetchCategories,
  });
};
