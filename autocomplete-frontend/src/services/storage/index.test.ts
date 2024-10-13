import { saveToStorage, retrieveFromStorage, STORAGES } from './index';

describe('Storage utility functions', () => {
    beforeEach(() => {
        Object.defineProperty(window, 'localStorage', {
            value: {
                setItem: jest.fn(),
                getItem: jest.fn() as jest.Mock, // Type assertion here
            },
            writable: true,
        });

        Object.defineProperty(window, 'sessionStorage', {
            value: {
                setItem: jest.fn(),
                getItem: jest.fn() as jest.Mock, // Type assertion here
            },
            writable: true,
        });
    });

    afterEach(() => {
        jest.clearAllMocks();
    });

    describe('saveToStorage', () => {
        it('should save data to localStorage', () => {
            const key = 'testKey';
            const data = { name: 'John', age: 30 };
            saveToStorage(key, data, STORAGES.local);

            expect(window.localStorage.setItem).toHaveBeenCalledWith(
                key,
                JSON.stringify(data)
            );
        });

        it('should save data to sessionStorage', () => {
            const key = 'testKey';
            const data = { name: 'Jane', age: 25 };
            saveToStorage(key, data, STORAGES.session);

            expect(window.sessionStorage.setItem).toHaveBeenCalledWith(
                key,
                JSON.stringify(data)
            );
        });
    });

    describe('retrieveFromStorage', () => {
        it('should retrieve data from localStorage', () => {
            const key = 'testKey';
            const data = { name: 'John', age: 30 };
            const serializedData = JSON.stringify(data);
            (window.localStorage.getItem as jest.Mock).mockReturnValue(serializedData); // Type assertion here

            const result = retrieveFromStorage<{ name: string; age: number }>(key, STORAGES.local);

            expect(result).toEqual(data);
            expect(window.localStorage.getItem).toHaveBeenCalledWith(key);
        });

        it('should return null if no data is found in localStorage', () => {
            const key = 'nonExistentKey';
            (window.localStorage.getItem as jest.Mock).mockReturnValue(null); // Type assertion here

            const result = retrieveFromStorage<{ name: string; age: number }>(key, STORAGES.local);

            expect(result).toBeNull();
            expect(window.localStorage.getItem).toHaveBeenCalledWith(key);
        });

        it('should retrieve data from sessionStorage', () => {
            const key = 'testKey';
            const data = { name: 'Jane', age: 25 };
            const serializedData = JSON.stringify(data);
            (window.sessionStorage.getItem as jest.Mock).mockReturnValue(serializedData); // Type assertion here

            const result = retrieveFromStorage<{ name: string; age: number }>(key, STORAGES.session);

            expect(result).toEqual(data);
            expect(window.sessionStorage.getItem).toHaveBeenCalledWith(key);
        });

        it('should return null if no data is found in sessionStorage', () => {
            const key = 'nonExistentKey';
            (window.sessionStorage.getItem as jest.Mock).mockReturnValue(null); // Type assertion here

            const result = retrieveFromStorage<{ name: string; age: number }>(key, STORAGES.session);

            expect(result).toBeNull();
            expect(window.sessionStorage.getItem).toHaveBeenCalledWith(key);
        });
    });
});