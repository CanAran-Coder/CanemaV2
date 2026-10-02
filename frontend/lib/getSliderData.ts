import { fetcher } from "@/lib/fetcher";
import { MovieEntity } from "@/types/MovieEntity";

export async function getShowTimByDate(date:string) {
    const data = (await fetcher("/showtimes/getMoviesByDate/"+date)) as MovieEntity[];
    console.log(123);
    return data;

}