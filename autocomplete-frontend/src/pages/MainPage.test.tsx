import {render} from "@testing-library/react";

import {MainPage} from "./MainPage";

describe("MainPage", () => {
    it("should render the main page", () => {
        const {container} = render(<MainPage />);
        expect(container).toMatchSnapshot();
    });
})
