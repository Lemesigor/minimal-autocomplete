/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';

export const formStyles = css`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 3.5rem; /* This corresponds to mt-14 in Tailwind */
`
export const Form  = () => {
    return (
        <section css={formStyles}>
            <input type={"text"} placeholder={"Search"} />
        </section>
    )
}