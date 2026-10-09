import { MovieEntity } from "./MovieEntity";
import { HallEntity } from "./HallEntity";


export interface ShowtimeEntity {


    id:string,
    movie:MovieEntity,
    hall:HallEntity,
    price:BigInt,
    date:string

    


}