/** @jsxImportSource @emotion/react */
import { render, fireEvent } from "@testing-library/react";
import { SuggestionItem } from "./SuggestionItem";
import { Term } from "../AutocompleteList/AutocompleteList";

describe("SuggestionItem", () => {
    const mockTerm: Term = { id: 1, term: "test" };
    const mockHandleClick = jest.fn();

    it("should match the snapshot", () => {
        const { container } = render(<SuggestionItem item={mockTerm} handleClick={mockHandleClick} />);
        expect(container).toMatchSnapshot();
    });

    it("should call handleClick when the item is clicked", () => {
        const { getByText } = render(<SuggestionItem item={mockTerm} handleClick={mockHandleClick} />);
        const itemElement = getByText("test");
        fireEvent.click(itemElement);
        expect(mockHandleClick).toHaveBeenCalledTimes(1);
        expect(mockHandleClick).toHaveBeenCalledWith(mockTerm);
    });
});