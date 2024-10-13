/** @jsxImportSource @emotion/react */
import {render, fireEvent} from "@testing-library/react";
import {SuggestionItem} from "./SuggestionItem";
import {Term} from "../../services/api/autocomplete";

describe("SuggestionItem", () => {
    const mockTerm: Term = {id: 1, value: "test"};
    const mockHandleClick = jest.fn();

    it("should match the snapshot with search", () => {
        const {container} = render(<SuggestionItem item={mockTerm} handleClick={mockHandleClick}
                                                   isRecentSearch={true}/>);
        expect(container).toMatchSnapshot();
    });

    it("should match the snapshot without search", () => {
        const {container} = render(<SuggestionItem item={mockTerm} handleClick={mockHandleClick}
                                                   isRecentSearch={false}/>);
        expect(container).toMatchSnapshot();
    });

    it("should call handleClick when the item is clicked", () => {
        const {getByText} = render(<SuggestionItem item={mockTerm} handleClick={mockHandleClick}
                                                   isRecentSearch={false}/>);
        const itemElement = getByText("test");
        fireEvent.click(itemElement);
        expect(mockHandleClick).toHaveBeenCalledTimes(1);
        expect(mockHandleClick).toHaveBeenCalledWith(mockTerm);
    });
});