import { z } from "zod";

export const userSchema = z.object({
  name: z.string().min(1, "Name is required"),
  subcategoryIds: z.array(z.number()).min(1, "Select at least one subcategory"),
  agreedToTerms: z.boolean().refine((val) => val === true, {
    message: "You must agree to terms",
  }),
});

export type UserFormValues = z.infer<typeof userSchema>;
