
import { actionFetcher, fetcher } from "./fetcher";



export async function getShowtimesByDate(date:string) {

    const response = await fetcher("/showtimes/getShowTimesByDate/"+date)
    return response

}

