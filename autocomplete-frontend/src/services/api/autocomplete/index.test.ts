import { getAutocompleteData, Term } from './';

describe('getAutocompleteData', () => {
    beforeEach(() => {
        global.fetch = jest.fn();
        jest.spyOn(console, 'error').mockImplementation(() => {});
        jest.spyOn(console, 'log').mockImplementation(() => {});

    });

    afterEach(() => {
        jest.clearAllMocks();
        (console.error as jest.Mock).mockRestore();
        (console.log as jest.Mock).mockRestore();
    });

    it('should fetch terms data successfully', async () => {
        const mockTerms: Term[] = [
            { id: 1, term: 'apple' },
            { id: 2, term: 'appleleple' },
        ];

        const mockResponse = {
            terms: mockTerms,
            total: mockTerms.length,
        };

        (fetch as jest.Mock).mockResolvedValueOnce({
            ok: true,
            json: jest.fn().mockResolvedValueOnce(mockResponse),
        });

        const abortController = new AbortController();
        const result = await getAutocompleteData('apple', abortController.signal);

        expect(fetch).toHaveBeenCalledWith(
            expect.stringContaining('query=apple'),  // Check the URL
            expect.objectContaining({ signal: abortController.signal }) // Check the options including signal
        );
        expect(result).toEqual(mockTerms);
    });

    it('should handle fetch errors gracefully', async () => {
        (fetch as jest.Mock).mockRejectedValueOnce(new Error('Network error'));

        const abortController = new AbortController();
        const result = await getAutocompleteData('', abortController.signal);

        expect(result).toEqual([]);
        expect(console.error).toHaveBeenCalledWith("Request error:", "Network error");
    });

    it('should handle HTTP errors gracefully', async () => {
        (fetch as jest.Mock).mockResolvedValueOnce({
            ok: false,
            status: 404,
            json: jest.fn(),
        });

        const abortController = new AbortController();
        const result = await getAutocompleteData('apple', abortController.signal);

        expect(result).toEqual([]);
        expect(console.error).toHaveBeenCalledWith("HTTP error! status: 404");
    });

    it('should handle abort errors gracefully', async () => {
        const abortFn = jest.fn();

        // @ts-ignore
        global.AbortController = jest.fn(() => ({
            abort: abortFn,
        }));

        const mockAbortController = new AbortController();
        const signal = mockAbortController.signal;

        mockAbortController.abort(); // Abort before awaiting the result
        const resultPromise = getAutocompleteData('apple', signal);

        const result = await resultPromise; // Await the promise to handle abort

        expect(result).toEqual([]);
        expect(abortFn).toHaveBeenCalled();
    });
});
