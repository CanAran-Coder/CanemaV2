import { MovieEntity } from "@/types/MovieEntity"
import Image from "next/image"
import { useState } from "react"
function MovieCard({ movie }: { movie: MovieEntity }) {

    const hour = Math.floor(movie.duration / 60)
    const minute = movie.duration % 60

    return (

        <>
        <div className="cursor-pointer">

        
            <div className="my-3 relative  aspect-[3/4] rounded-xl overflow-hidden w-50 shadow-[0_14px_28px_-8px_rgba(0,0,0,0.7),0_6px_12px_-6px_rgba(0,0,0,0.45)] ring-1 ring-white/10">
                <Image src={movie.imageUrl} alt={movie.name} fill className="object-cover object-center" />
            </div>
            <div className="flex  flex-col items-center justify-center">
                <h1 className="font-black">{movie.name}</h1>
                <p className="font-black">{hour} Hours {minute} Minutes</p>
            </div>

        </div>
        </>


    )
}

export default MovieCard