/** @jsxImportSource @emotion/react */
import {useState} from "react";
import {containerStyle, inputStyle} from "./styles";


export const AutocompleteInput = () => {
    const [isInputFocused, setIsInputFocused] = useState<boolean>(false);

    console.log(isInputFocused);
    return (
        <div css={containerStyle}>
            <input
                data-testid={"autocomplete-input"}
                css={inputStyle}
                placeholder="Search an English Word"
                type="text"
                onFocus={() => setIsInputFocused(true)}
                onBlur={() => setIsInputFocused(false)}
            />
        </div>
    );
};