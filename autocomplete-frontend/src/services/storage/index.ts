export const STORAGES = {
    session: 'sessionStorage',
    local: 'localStorage',
} as const;

type StorageType = typeof STORAGES[keyof typeof STORAGES];

export const saveToStorage = (key: string, data: any, storage: StorageType): void => {
    const serializedData = JSON.stringify(data);
    window[storage].setItem(key, serializedData);
};

export const retrieveFromStorage = <T>(key: string, storage: StorageType): T | null => {
    const serializedData = window[storage].getItem(key);
    return serializedData ? JSON.parse(serializedData) : null;
};
