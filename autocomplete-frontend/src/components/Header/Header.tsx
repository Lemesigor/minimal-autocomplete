/** @jsxImportSource @emotion/react */

import {h2Style, headerStyle} from "./styles";
export const Header = () => {
    return (
        <header css={headerStyle}>
            <h2 css={h2Style}>Autocomplete</h2>
        </header>
    );
}