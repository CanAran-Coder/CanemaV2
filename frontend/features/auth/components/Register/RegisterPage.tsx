"use client";

import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { registerSchema, type RegisterFormValues } from "../validation/registerSchema";
import { registerAction } from "../../action/registerAction";
import { toast } from "react-toastify";
import { useRouter } from "next/navigation";
import { loginAction } from "../../action/loginAction";
function RegisterPage({ setIsLogin }: { setIsLogin: (isLogin: boolean) => void }) {
    const router = useRouter();
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<RegisterFormValues>({
        resolver: zodResolver(registerSchema),
    });

    const onSubmit = async (_data: RegisterFormValues) => {await registerAction(_data); await loginAction(_data); toast.success("User registered successfully"); router.push("/"); };

    return (
        <div className=" flex h-screen w-full items-center justify-center" style={{ backgroundImage: "url('/images/authBG.webp')", backgroundSize: "cover", backgroundPosition: "center" }}>
            <form noValidate onSubmit={handleSubmit(onSubmit)} className="grid grid-cols-[1fr_2.5fr] grid-rows-4 items-center justify-center gap-y-4 border-2 rounded-lg border-white p-10 gap-x-2">
                <h1 className="col-span-full text-center text-4xl font-bold leading-relaxed">Register</h1>
                <label htmlFor="register-email" className="font-bold text-lg">Email:</label>
                <div>
                    <input id="register-email" type="email" placeholder="Email" className={`tracking-tight w-full p-2 border-2 rounded-md ${errors.email ? "border-red-500" : "border-gray-300"}`} {...register("email")} />
                    {errors.email && <p className="mt-1 text-sm text-red-600">{errors.email.message}</p>}
                </div>
                <label htmlFor="register-password" className="font-bold text-lg">Password:</label>
                <div>
                    <input id="register-password" type="password" placeholder="Password" className={`tracking-tight w-full p-2 border-2 rounded-md ${errors.password ? "border-red-500" : "border-gray-300"}`} {...register("password")} />
                    {errors.password && <p className="mt-1 text-sm text-red-600">{errors.password.message}</p>}
                </div>
                <button type="submit" className="hover:brightness-120 transition-all duration-300 cursor-pointer col-span-full w-full p-2 rounded-md bg-zinc-700 text-white font-black">Register</button>
                <p onClick={() => setIsLogin(true)} className="col-span-full text-center underline italic cursor-pointer">Already have an account?</p>
            </form>
        </div>
    )
}

export default RegisterPage;
