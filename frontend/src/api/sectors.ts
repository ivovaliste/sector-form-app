import { useQuery } from "@tanstack/react-query";
import { api } from ".";
import { Category } from "../types/user";

export const fetchCategories = (): Promise<Category[]> =>
  api.get("sectors").json<Category[]>();

export const useCategories = () =>
  useQuery({
    queryKey: ["categories"],
    queryFn: fetchCategories,
  });
