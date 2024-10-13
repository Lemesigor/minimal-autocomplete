/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import {AutocompleteInput} from "../AutocompleteInput/AutocompleteInput";

export const formStyles = css`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 1rem; /
`
export const Form  = () => {
    return (
        <section css={formStyles}>
           <AutocompleteInput />
        </section>
    )
}