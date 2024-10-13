import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import {userEvent} from "@testing-library/user-event";
import { AutocompleteInput } from "./AutocompleteInput";
import { getAutocompleteData } from "../../services/api/autocomplete";
import { useRecentSearches } from "../../hooks/useRecentSearches";

jest.mock("../../services/api/autocomplete", () => ({
    getAutocompleteData: jest.fn(),
}));
jest.mock("../../hooks/useRecentSearches", () => ({
    useRecentSearches: jest.fn(),
}));

describe("AutocompleteInput", () => {
    beforeEach(() => {
        (useRecentSearches as jest.Mock).mockReturnValue({
            recentSearches: [{ id: 1, value: "apple" }],
            addToRecentSearches: jest.fn(),
        });
    });

    afterEach(() => {
        jest.clearAllMocks();
    });

    it("should match the snapshot", () => {
        const { container } = render(<AutocompleteInput />);
        expect(container).toMatchSnapshot();
    });

    it("should render input and display recent searches when focused", async () => {
        render(<AutocompleteInput />);

        const input = screen.getByTestId("autocomplete-input");
        fireEvent.focus(input);

        await waitFor(() => {
            expect(screen.getByText("apple")).toBeInTheDocument();
        });
    });

    it("should fetch autocomplete data on search term input", async () => {
        const mockSuggestions = [{ id: 2, value: "banana" }];
        (getAutocompleteData as jest.Mock).mockResolvedValue(mockSuggestions);

        render(<AutocompleteInput />);

        const input = screen.getByTestId("autocomplete-input");
        fireEvent.change(input, { target: { value: "ban" } });



        await waitFor(() => {
            expect(getAutocompleteData).toHaveBeenCalledWith("ban", expect.any(AbortSignal));
        });
    });

    it("should handle suggestion click and update input", async () => {
        const mockSuggestions = [{ id: 2, value: "banana" }];
        (getAutocompleteData as jest.Mock).mockResolvedValue(mockSuggestions);

        render(<AutocompleteInput />);

        const input = screen.getByTestId("autocomplete-input");
        await userEvent.type(input, "ban");

        await waitFor(() => {
            expect(screen.getByText("banana")).toBeInTheDocument();
        });

        fireEvent.click(screen.getByText("banana"));

        expect(input).toHaveValue("banana");
    });
});
