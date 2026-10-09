

export async function fetcher(url: string) {

    const apiUrl = process.env.NEXT_PUBLIC_API_URL;

    const response = await fetch(`${apiUrl}${url}`,{method: "GET", headers: {
        "Content-Type": "application/json",
    },credentials: "include"});

    if (!response.ok) {
        throw new Error("Failed to fetch data");
    }

    return response.json();
}


export async function actionFetcher(url: string, data: unknown, method: string) {
    const apiUrl = process.env.NEXT_PUBLIC_API_URL;

    const response = await fetch(`${apiUrl}${url}`,{method: method, headers: {
        "Content-Type": "application/json",
    }, body: JSON.stringify(data), credentials: "include"});

    if (!response.ok) {
        throw new Error("Failed to fetch data");
    }

    return response;
}