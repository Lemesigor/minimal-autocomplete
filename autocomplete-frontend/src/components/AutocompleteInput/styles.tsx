import { css } from '@emotion/react';

export const containerStyle = css`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  padding-top: 5rem;
  `;

export const inputStyle = css`
    font-size: 1.125rem;
    color: #1f2937;
    border: 1px solid #1f2937;
    border-radius: 0.375rem;
    width: 15rem;
    padding: 0.25rem;
    margin-bottom: 0.5rem;
    transition: all 0.2s ease; 
  height: 30px;

    &:focus,
    &:hover {
        outline: none;
    }
`;