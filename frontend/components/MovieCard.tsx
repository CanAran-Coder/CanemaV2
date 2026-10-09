'use client'

import { MovieEntity } from "@/types/MovieEntity"
import Image from "next/image"
import SeatSelection from "./SeatSelection"
import { ShowtimeEntity } from "@/types/ShowtimeEntity"
import { useState } from "react"
function MovieCard({Showtime }: { Showtime: ShowtimeEntity }) {
    const [seats, setSeats] = useState<boolean>(false)
    const hour = Math.floor(Showtime.movie.duration / 60)
    const minute = Showtime.movie.duration % 60


    return (

        <>

            {seats && (
                <SeatSelection setSeats={setSeats} Showtime={Showtime} />
            )}

            <div className="cursor-pointer" onClick={() => setSeats(true)}>


                <div className="my-3 relative  aspect-[3/4] rounded-xl overflow-hidden w-50 shadow-[0_14px_28px_-8px_rgba(0,0,0,0.7),0_6px_12px_-6px_rgba(0,0,0,0.45)] ring-1 ring-white/10">
                    <Image src={Showtime.movie.imageUrl} alt={Showtime.movie.name} fill className="object-cover object-center" />
                </div>
                <div className="flex  flex-col items-center justify-center">
                    <h1 className="font-black">{Showtime.movie.name}</h1>
                    <p className="font-black">{hour} Hours {minute} Minutes</p>
                </div>

            </div>
        </>


    )
}

export default MovieCard