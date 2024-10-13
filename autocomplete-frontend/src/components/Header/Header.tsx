/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';

const headerStyle = css`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-top: 5rem; 
`;

const h2Style = css`
  font-size: 4.5rem;
    color: rgb(69, 168, 100);
`;

export const Header = () => {
    return (
        <header css={headerStyle}>
            <h2 css={h2Style}>Autocomplete</h2>
        </header>
    );
}