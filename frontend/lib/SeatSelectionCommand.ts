import { actionFetcher } from "./fetcher"
import { SeatLockStates } from "@/types/SeatLockStates"



interface SeatRequestBody{
    showtimeId: string
    userId: string
    seatNumber: number
}

export async function SeatSelectionCommand(showtimeId: string,userId: string,seatNumber:number,state:SeatLockStates){
    const requestBody:SeatRequestBody = {showtimeId,userId,seatNumber}

    if(state === SeatLockStates.SOLD){
        return {success:false,message:"Seat is sold out"}
    }

    if(state === SeatLockStates.RESERVED){
        const response = await actionFetcher("/release",requestBody,"POST")

        if(response.ok){
            const body = await response.json().catch(() => null)
            if(body){
                return {success:true,message:"Seat released!"}
            }
            return {success:false,message:"You cannot release another user's seat!"}
        }
        return {success:false,message:"Failed to release seat!"}

    }

    if(state === SeatLockStates.AVAILABLE){
        const response = await actionFetcher("/lock",requestBody,"POST")
        if(response.status === 409){
            return {success:false,message:"Seat is already locked by another user!"}
        }
        if(response.ok){
            const body = await response.json().catch(() => null)
            if(body){
                return {success:true,message:"Seat locked!"}
            }
        }

    }


}