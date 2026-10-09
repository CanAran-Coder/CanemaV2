'use client'
import Link from 'next/link'
import { MdMovie } from "react-icons/md";
import { meAction } from '../features/auth/action/meAction';
import { useEffect } from 'react';
import { logoutAction } from '../features/auth/action/logoutAction';
import { useAuthStore } from '@/context/useAuthStore';
function Navbar() {
    const {user,setUser} = useAuthStore();
    useEffect(() => {
        console.log(user);
        
        async function fetchUser() {
            if(user){
                return
            }
            const u = await meAction();
            setUser(u);
        }
        fetchUser();
    }, [user,setUser]);
    return (
        <div className='grid grid-cols-[1fr_2.5fr] p-4 h-18 fixed top-0 left-0 right-0 z-50 bg-black/50 backdrop-blur-sm '>
            <div className='flex items-center justify-start gap-x-2'>
                
                <h1 className=' text-start text-4xl font-bold'>Canema</h1>
                <MdMovie className='text-4xl' />
            </div>
            <div className='flex w-full h-full justify-around items-center'>
                <Link href='/' className='font-black text-lg hover:underline'>Home</Link>
                <Link href="/contact" className='font-black text-lg hover:underline'>Contact</Link>
                {user?.email !=null ? <Link onClick={() => logoutAction()} href="/auth" className='font-black text-lg hover:underline'>Logout</Link> : <Link href="/auth" className='font-black text-lg hover:underline'>Login</Link>}
            </div>



        </div>
    )
}

export default Navbar