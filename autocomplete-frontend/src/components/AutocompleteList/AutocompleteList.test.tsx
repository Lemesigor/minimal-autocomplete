/** @jsxImportSource @emotion/react */
import { render } from "@testing-library/react";
import { AutocompleteList } from "./AutocompleteList";
import {Term} from "../../services/api/autocomplete";

jest.mock("../SuggestionItem/SuggestionItem", () => ({
    SuggestionItem: jest.fn(() => <li>Mocked Suggestion Item</li>),
}));

describe("AutocompleteList", () => {
    const mockItems: Term[] = [
        { id: 1, value: "apple" },
        { id: 2, value: "banana" },
    ];
    const mockHandleClick = jest.fn();

    it("should match the snapshot", () => {
        const { container } = render(
            <AutocompleteList items={mockItems} handleClick={mockHandleClick} isRecentSearchSuggestion={false} />
        );
        expect(container).toMatchSnapshot();
    });

    it("should have the correct data-testid for recent search suggestions", () => {
        const { getByTestId } = render(
            <AutocompleteList items={mockItems} handleClick={mockHandleClick} isRecentSearchSuggestion={true} />
        );
        expect(getByTestId("recent-searches-list")).toBeInTheDocument();
    });

    it("should have the correct data-testid for normal results", () => {
        const { getByTestId } = render(
            <AutocompleteList items={mockItems} handleClick={mockHandleClick} isRecentSearchSuggestion={false} />
        );
        expect(getByTestId("results-list")).toBeInTheDocument();
    });
});
