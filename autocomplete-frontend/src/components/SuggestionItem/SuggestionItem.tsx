/** @jsxImportSource @emotion/react */
import {css} from '@emotion/react';
import {Term} from "../AutocompleteList/AutocompleteList";

type SuggestionItemProps = {
    item: Term
    handleClick: (item: Term) => void;
};

const itemStyle = css`
    padding: 0.25rem;
    background-color: rgba(255, 255, 255, 0.5);
    transition: all 0.2s ease;

    &:hover {
        background-color: rgba(226, 232, 240, 1); /* hover:bg-gray-200 equivalent */
    }
`;

export const SuggestionItem = ({item, handleClick}: SuggestionItemProps) => (
    <li
        tabIndex={-1}
        key={item.id}
        css={itemStyle}
        onMouseDown={(e) => e.preventDefault()}
        onClick={() => handleClick(item)}
    >
        <span className="cursor-default">{item.term}</span>
    </li>
);