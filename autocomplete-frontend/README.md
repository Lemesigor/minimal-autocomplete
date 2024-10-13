# Autocomplete Frontend

This project is a frontend application for an autocomplete feature. It includes functionalities such as debouncing input, using an AbortController for API requests, and caching recent searches.

## Table of Contents
 
- [Running Locally](#running-locally)
- [Testing](#testing)
- [Main Functionalities](#main-functionalities)


## Running Locally

```bash
npm install
npm run dev
```

## Testing

```bash
npm run test
```


## Main Functionalities

### Debouncing input
Debouncing is used to limit the rate at which a function is executed. In this project, the useDebounce hook is used to debounce the search term input. This ensures that the API is not called on every keystroke but only after the user has stopped typing for a specified delay.

### AbortController

The AbortController is used to cancel an API request when a new request is made. This is useful when the user types quickly and the previous request is no longer needed.

### Caching recent searches

The recent searches are stored in the browser's local storage after the user clicks in any input. This allows the user to see their recent searches even after refreshing the page.
