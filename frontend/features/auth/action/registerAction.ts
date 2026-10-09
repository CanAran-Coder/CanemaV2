import { RegisterFormValues } from "../components/validation/registerSchema";
import {actionFetcher} from "@/lib/fetcher";



export async function registerAction(data:RegisterFormValues){

    
    const response = await actionFetcher("/users/register", data, "POST");
    if(response.status !== 201){
        const errorBody = await response.json().catch(() => null);
        throw new Error(errorBody?.message ?? "Failed to register user");
        
    }

    return null;

}