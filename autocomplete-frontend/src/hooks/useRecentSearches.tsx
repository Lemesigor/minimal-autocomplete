import {useState, useEffect} from 'react';
import {retrieveFromStorage, saveToStorage, STORAGES} from '../services/storage';
import {Term} from "../services/api/autocomplete";


export const useRecentSearches = () => {
    const [recentSearches, setRecentSearches] = useState<Term[]>([]);

    const RECENT_SEARCHES_KEY = "recentSearches";

    useEffect(() => {
        const storedSearches = retrieveFromStorage(RECENT_SEARCHES_KEY, STORAGES.local) as Term[];
        if (storedSearches) {
            setRecentSearches(storedSearches);
        }
    }, []);

    const addToRecentSearches = (searchTerm: Term) => {
        let updatedSearches = [...recentSearches];

        const isAlreadyInRecentSearchesIndex = recentSearches.findIndex(
            (storedSearchTerm) => storedSearchTerm.id === searchTerm.id
        );

        if (isAlreadyInRecentSearchesIndex !== -1) {
            updatedSearches.splice(isAlreadyInRecentSearchesIndex, 1);
            updatedSearches.unshift(searchTerm);
        } else {
            updatedSearches.unshift(searchTerm);
            updatedSearches = updatedSearches.slice(0, 10);
        }

        setRecentSearches(updatedSearches);
        saveToStorage(RECENT_SEARCHES_KEY, updatedSearches, STORAGES.local);
    };

    return {recentSearches, addToRecentSearches};
};
