import {render} from "@testing-library/react";

import {Form} from "./Form";

describe("Form", () => {
    it("should render the form", () => {
        const {container} = render(<Form />);
        expect(container).toMatchSnapshot();
    });

    it("should have input field", () => {
        const {getByTestId} = render(<Form />);
        expect(getByTestId("autocomplete-input")).toBeInTheDocument();
    })
});