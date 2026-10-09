import Seat from "./Seat"
import { ShowtimeEntity } from "@/types/ShowtimeEntity"
import { RxCross2 } from "react-icons/rx"
function SeatSelection({setSeats,Showtime}: {setSeats: (seats: boolean) => void,Showtime:ShowtimeEntity}) {


    
    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/75 p-4 backdrop-blur-sm">
            <div className="relative w-full max-w-3xl rounded-3xl border border-white/10 bg-zinc-950 px-6 py-8 shadow-[0_30px_80px_-24px_rgba(0,0,0,0.85)] sm:px-10">
                <button
                    type="button"
                    aria-label="Close"
                    className="absolute top-4 right-4 flex size-10 cursor-pointer items-center justify-center rounded-full border border-white/10 bg-white/5 text-zinc-200 transition hover:bg-white/15"
                    onClick={() => setSeats(false)}
                >
                    <RxCross2 className="text-2xl" />
                </button>

                <h1 className="text-center text-3xl font-black tracking-tight text-white sm:text-4xl">Select Your Seats</h1>

                <div className="mt-8 grid grid-cols-3 items-end gap-4">
                    <p className="justify-self-start rounded-full border border-white/10 bg-white/5 px-4 py-1.5 text-xs font-black tracking-[0.22em] text-zinc-400 uppercase">Door</p>
                    <div className="flex flex-col items-center gap-2">
                        <div className="h-2.5 w-full rounded-[50%] bg-gradient-to-b from-white via-zinc-200 to-zinc-600 shadow-[0_12px_32px_rgba(255,255,255,0.28)]" />
                        <p className="text-xs font-black tracking-[0.28em] text-zinc-400 uppercase">Screen</p>
                    </div>
                    <button className="justify-self-end cursor-pointer rounded-full bg-white px-5 py-2.5 text-sm font-black tracking-tight text-zinc-950 transition hover:bg-zinc-200">Payment</button>
                </div>

                <div className="mx-auto mt-8 grid w-fit grid-cols-5 grid-rows-5 gap-2.5 rounded-2xl bg-zinc-900 p-5 ring-1 ring-white/10 sm:gap-3 sm:p-6">
                    {Array.from({ length: 25 }).map((item, index) => (
                        <div className="size-14 sm:size-16" key={index}>
                            <Seat seatNumber={index + 1} Showtime={Showtime} />
                        </div>
                    ))}
                </div>
            </div>
        </div>
    )
}

export default SeatSelection
