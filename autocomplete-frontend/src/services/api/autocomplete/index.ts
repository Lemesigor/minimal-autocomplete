const BASE_ENDPOINT = "https://localhost:4567/autocomplete?";

const DEFAULT_LIMIT = 10;

export type Term = {
    id: number;
    term: string;
}

export type TermResponse = {
    terms: Term[];
    total: number;
}

export const getAutocompleteData = async (query: string = "", signal: AbortSignal): Promise<Term[]> => {
    try {
        const autocompleteEndpoint = `${BASE_ENDPOINT}query=${encodeURIComponent(query)}&limit=${DEFAULT_LIMIT}`;
        const response = await fetch(autocompleteEndpoint, { signal });
        if (!response.ok) {
            console.error(`HTTP error! status: ${response.status}`);
            return [];
        }
        const data: TermResponse = await response.json();
        return data?.terms || []; // Return terms instead of results
    } catch (error) {
        // @ts-ignore
        if (error.name === "AbortError") {
            // @ts-ignore
            console.log("Request aborted:", error.message);
        } else {
            // @ts-ignore
            console.error("Request error:", error.message);
        }
        return [];
    }
};
