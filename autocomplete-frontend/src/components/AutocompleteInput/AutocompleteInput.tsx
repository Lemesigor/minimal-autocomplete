/** @jsxImportSource @emotion/react */
import {useEffect, useState} from "react";
import {containerStyle, inputStyle} from "./styles";
import {useRecentSearches} from "../../hooks/useRecentSearches";
import {useDebounce} from "../../hooks/useDebounce";
import {getAutocompleteData, Term} from "../../services/api/autocomplete";
import {AutocompleteList} from "../AutocompleteList/AutocompleteList";
import {retrieveFromStorage, saveToStorage, STORAGES} from "../../services/storage";


export const AutocompleteInput = () => {
    const [searchTerm, setSearchTerm] = useState<string>("");
    const [isInputFocused, setIsInputFocused] = useState<boolean>(false);
    const [suggestions, setSuggestions] = useState<Term[]>([]);
    const {recentSearches, addToRecentSearches} = useRecentSearches()
    const [isRecentSearchSuggestion, setIsRecentSearchSuggestion] = useState<boolean>(false)

    const debouncedSearchTerm = useDebounce({value: searchTerm, delay: 250})

    const shouldShowRecentSearchesList = !searchTerm && isRecentSearchSuggestion && recentSearches.length > 0

    const shouldShowSuggestionsList = isInputFocused && (shouldShowRecentSearchesList || suggestions.length)


    const handleSuggestionClick = (suggestion: Term) => {
        setSearchTerm(suggestion.value);
        addToRecentSearches({id: suggestion.id, value: suggestion.value});
    };

    useEffect(() => {
        const abortController = new AbortController();

        (async function getSuggestionsTerms() {
            if (!debouncedSearchTerm) {
                setSuggestions(recentSearches)
                setIsRecentSearchSuggestion(true)
                return
            }

            const cachedSearchTerms = retrieveFromStorage(debouncedSearchTerm, STORAGES.session)

            if (cachedSearchTerms) {
                setSuggestions(cachedSearchTerms as Term[])
                return
            }

            const signal = abortController.signal
            const termsListResult = await getAutocompleteData(debouncedSearchTerm, signal)
            setSuggestions(termsListResult ?? [])
            setIsRecentSearchSuggestion(false)

            saveToStorage(debouncedSearchTerm, termsListResult, STORAGES.session)
        })();
        return () => abortController.abort('Aborted due to new search term')
    }, [debouncedSearchTerm, recentSearches])

    return (
        <div css={containerStyle}>
            <input
                data-testid={"autocomplete-input"}
                css={inputStyle}
                placeholder="Search an English Word"
                type="text"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                onFocus={() => setIsInputFocused(true)}
                onBlur={() => setIsInputFocused(false)}
            />
            {shouldShowSuggestionsList ?
                (<AutocompleteList items={suggestions}
                                   handleClick={handleSuggestionClick}
                                   isRecentSearchSuggestion={isRecentSearchSuggestion}/>): null}
        </div>
    );
};