export interface Category {
  id: number;
  name: string;
  subcategories: Subcategories[];
}

export interface UserRequest {
  id?: number; // optional for updates
  name: string;
  subcategoryIds: number[];
  agreedToTerms: boolean;
}

export interface Option {
  value: number;
  label: string;
}

export interface Subcategories {
  id: number;
  name: string;
}

export interface UserResponse {
  id?: number; // optional for updates
  name: string;
  subcategories: Subcategories[];
  agreedToTerms: boolean;
}
