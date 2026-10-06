function LoginPage({ setIsLogin }: { setIsLogin: (isLogin: boolean) => void }) {
    return (
        <div className=" flex h-screen w-full items-center justify-center" style={{ backgroundImage: "url('/images/authBG.webp')", backgroundSize: "cover", backgroundPosition: "center" }}>
            <form className="grid grid-cols-[1fr_2.5fr] grid-rows-4 items-center justify-center gap-y-4 border-2 rounded-lg border-white p-10 gap-x-2">
                <h1 className="col-span-full text-center text-4xl font-bold leading-relaxed">Login</h1>
                <label className="font-bold text-lg">Email:</label>
                <input type="email" placeholder="Email" className="tracking-tight w-full p-2  border-2 border-gray-300 rounded-md" />
                <label className="font-bold text-lg">Password:</label>
                <input type="password" placeholder="Password" className="tracking-tight w-full p-2  border-2 border-gray-300 rounded-md" />
                <button type="submit" className="hover:brightness-120 transition-all duration-300 cursor-pointer col-span-full w-full p-2 rounded-md bg-zinc-700 text-white font-black">Login</button>
                <p onClick={() => setIsLogin(false)} className="col-span-full text-center underline italic cursor-pointer">Don't have an account?</p>
            </form>
        </div>
    )
}

export default LoginPage;