export interface Category {
  id: number;
  name: string;
  children?: Category[] | null;
}

export interface UserDTO {
  id?: number;
  name: string;
  sectorIds: number[];
  agreedToTerms: boolean;
}

export interface Option {
  value: number;
  label: string;
}




