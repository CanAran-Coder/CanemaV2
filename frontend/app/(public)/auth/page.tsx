'use client'
import LoginPage from "@/features/auth/components/Login/LoginPage";
import RegisterPage from "@/features/auth/components/Register/RegisterPage";
import { useState } from "react";

function AuthPage() {

    const [isLogin, setIsLogin] = useState(true);
    
    return (
        <>{isLogin ? <LoginPage setIsLogin={setIsLogin} /> : <RegisterPage setIsLogin={setIsLogin} />}</>
    )
}

export default AuthPage