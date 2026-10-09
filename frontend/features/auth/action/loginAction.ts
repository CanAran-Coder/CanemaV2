import { LoginFormValues } from "../components/validation/loginSchema";
import { actionFetcher } from "@/lib/fetcher";



export async function loginAction(data:LoginFormValues){
    
    
    const response = await actionFetcher("/users/login", data, "POST");
    if(response.status !== 200){
        const errorBody = await response.json().catch(() => null);
        throw new Error(errorBody?.message ?? "Failed to login");
    }



    return null;
}