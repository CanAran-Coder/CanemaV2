'use client'
import { useEffect, useState } from "react"
import { getNext7Days } from "@/lib/getNext7Days";
import SeatSelection from "./SeatSelection"
import { ShowtimeEntity } from "@/types/ShowtimeEntity"
import { getShowtimesByDate } from "@/lib/getShowTimesByDate"
import MovieCard from "./MovieCard"
function MovieCardList() {
    const [showtimes, setShowtimes] = useState<ShowtimeEntity[]>([])
    const [day, setDay] = useState<string>(new Date().toISOString().split('T')[0])
    const [days, setDays] = useState<string[]>([])
    

    useEffect(() => {

        setDays(getNext7Days())

    }, [])


    useEffect(() => {

        async function getShowtimes() {
            const showtimes = await getShowtimesByDate(day)
            setShowtimes(showtimes)
            
        }
        getShowtimes()

    }, [day])




    return (
        <>

           

            <div className='grid bg-zinc-900 grid-cols-4 place-items-center gap-2 px-3 py-10 h-120 relative'>
                <select className="font-bold border-2 col-span-full place-self-start border-white rounded-xl cursor-pointer px-4 py-2" value={day} onChange={(e) => setDay(e.target.value)}>
                    {days.map((item, index) => {
                        return <option key={index} value={item}>{item}</option>
                    })}
                </select>

                
                {showtimes.length > 0 ? (
                    showtimes.map((item, index) => {
                        return <MovieCard Showtime={item} key={index} />
                    })
                ) :

                    (<h1 className="text-white col-span-full text-2xl font-bold">There are no movies for this day!</h1>)

                }
 	


            </div>


        </>

    )
}

export default MovieCardList