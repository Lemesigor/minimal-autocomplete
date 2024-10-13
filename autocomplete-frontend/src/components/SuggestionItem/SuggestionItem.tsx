/** @jsxImportSource @emotion/react */
import {css} from '@emotion/react';
import {Term} from "../../services/api/autocomplete";

type SuggestionItemProps = {
    item: Term;
    handleClick: (item: Term) => void;
    isRecentSearch: boolean;
};

const itemStyle = css`
    display: flex;
    align-items: center;
    background-color: rgba(255, 255, 255, 0.5);
    transition: all 0.2s ease;
    padding: 0.25rem 0;

    &:hover {
        background-color: rgba(226, 232, 240, 1);
    }
`;

const iconStyle = css`
    margin-right: 0.5rem;
    height: 15px;
    height: 15px;
`;


export const SuggestionItem = ({item, handleClick, isRecentSearch}: SuggestionItemProps) => {
    const iconPath = isRecentSearch ? "recent_search.png" : "search.png";
    return (
        <li
            tabIndex={-1}
            key={item.id}
            css={itemStyle}
            onMouseDown={(e) => e.preventDefault()}
            onClick={() => handleClick(item)}
        >
            <img src={iconPath} css={iconStyle} alt={"search"}/>
            <span className="cursor-default">{item.value}</span>
        </li>
    )
};

