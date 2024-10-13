import { useState, useEffect } from 'react';

type UseDebounceProps = {
    value?: string;
    delay?: number;
};

export const useDebounce = ({ value = '', delay = 250 }: UseDebounceProps): string => {
    const [debouncedValue, setDebouncedValue] = useState<string>(value);

    useEffect(() => {
        const timeoutReference = setTimeout(() => {
            setDebouncedValue(value);
        }, delay);

        return () => {
            clearTimeout(timeoutReference);
        };
    }, [value, delay]);

    return debouncedValue;
};