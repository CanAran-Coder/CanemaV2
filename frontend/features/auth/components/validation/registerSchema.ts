import { z } from "zod";
import { emailSchema } from "./emailSchema";
import { passwordSchema } from "./passwordSchema";

export const registerSchema = z.object({
    email: emailSchema,
    password: passwordSchema,
});

export type RegisterFormValues = z.infer<typeof registerSchema>;
