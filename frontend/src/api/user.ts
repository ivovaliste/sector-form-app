import { useQuery } from "@tanstack/react-query";
import { api } from ".";
import { UserRequest, UserResponse } from "../types/user";

export const fetchUserById = async (id: number): Promise<UserRequest> => {
  const data = await api.get(`users/${id}`).json<UserResponse>();

  return {
    id: data.id,
    name: data.name,
    agreedToTerms: data.agreedToTerms,
    subcategoryIds: data.subcategories?.map((s) => s.id) || [],
  };
};

export const useUser = (id?: number) => {
  return useQuery<UserRequest, Error>({
    queryKey: ["user", id],
    queryFn: () => fetchUserById(id!),
    enabled: !!id,
  });
};

export const updateUser = async (
  payload: UserRequest
): Promise<UserResponse> => {
  const res = await api.post("users", { json: payload });
  return res.json<UserResponse>();
};
