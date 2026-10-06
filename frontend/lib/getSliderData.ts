import { fetcher } from "@/lib/fetcher";
import { MovieEntity } from "@/types/MovieEntity";

export async function getMoviesByDate(date:string) {
    const data = (await fetcher("/showtimes/getMoviesByDate/"+date)) as MovieEntity[];
    return data;
}