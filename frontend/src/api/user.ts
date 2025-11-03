import { useQuery } from "@tanstack/react-query";
import { api } from ".";
import { UserDTO } from "../types/user";

export const fetchUserById = async (id: number): Promise<UserDTO> => {
  return await api.get(`users/${id}`).json<UserDTO>();
};

export const useUser = (id?: number) =>
  useQuery({
    queryKey: ["user", id],
    queryFn: () => fetchUserById(id!),
    enabled: Boolean(id),
  });

export const updateUser = (payload: UserDTO) =>
  api.post("users", { json: payload }).json<UserDTO>();
