import { ShowtimeEntity } from "@/types/ShowtimeEntity"
import { SeatLockStates } from "@/types/SeatLockStates"
import { SeatSelectionCommand } from "@/lib/SeatSelectionCommand"
import { toast } from "react-toastify"
import { useAuthStore } from "@/context/useAuthStore"


function Seat({seatNumber,Showtime}: {seatNumber: number,Showtime:ShowtimeEntity}) {

    const soldSeats = [1,5,8]
    const {user} = useAuthStore();
    async function handleSeatSelection(){
        const response = await SeatSelectionCommand(Showtime.id,user!.id,seatNumber,SeatLockStates.AVAILABLE)
        if(response?.success){
            toast.success(response.message)
        }else{
            toast.error(response?.message || "Failed to select seat")
        }
    }

    return (
        <div className="bg-green-500 aspect-square flex justify-center items-center rounded-md w-full h-full cursor-pointer">
            <p className="text-zinc-900 font-black text-2xl">{seatNumber}</p>
        </div>
    )
}

export default Seat