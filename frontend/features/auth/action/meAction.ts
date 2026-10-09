

export async function meAction(){
    const data = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/users/me`, {
        method: "POST",
        credentials: "include",
    });
    if(data.status !== 200){
        const errorBody = await data.json().catch(() => null);
        throw new Error(errorBody?.message ?? "Failed to get user");
    }

    const responseBody = await data.json().catch(()=> null);
    return responseBody;

} 