import {render} from "@testing-library/react";

import {AutocompleteInput} from "./AutocompleteInput";

describe("AutocompleteInput", () => {
    it("should render the autocomplete input", () => {
        const {container} = render(<AutocompleteInput />);
        expect(container).toMatchSnapshot();
    });

    it("should have input field", () => {
        const {getByPlaceholderText} = render(<AutocompleteInput />);
        expect(getByPlaceholderText("Search an English Word")).toBeInTheDocument();
    })
});