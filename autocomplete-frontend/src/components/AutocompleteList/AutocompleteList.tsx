/** @jsxImportSource @emotion/react */
import {css} from '@emotion/react';
import {SuggestionItem} from '../SuggestionItem/SuggestionItem';

export type Term = {
    id: string | number;
    term: string;
};

type AutocompleteListProps = {
    items: Term[];
    handleClick: (item: Term) => void;
    isRecentSearchSuggestion: boolean;
};

export const AutocompleteList = ({
                                    items,
                                    handleClick,
                                    isRecentSearchSuggestion,
                                }: AutocompleteListProps) => {
    const dataTestId = isRecentSearchSuggestion ? 'recent-searches-list' : 'results-list';

    const listStyle = css`
        border: 1px solid #1f2937;
        border-radius: 0.375rem;
        width: 24rem;
        font-size: 1.125rem;
        background-color: white;
`;

    return (
        <ul data-testid={dataTestId} css={listStyle}>
            {items.map((item) => (
                <SuggestionItem key={item.id} item={item} handleClick={handleClick}/>
            ))}
        </ul>
    );
}
