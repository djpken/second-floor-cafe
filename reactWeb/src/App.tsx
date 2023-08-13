import {ThemeProvider} from "@mui/material";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import {ReactQueryDevtools} from "@tanstack/react-query-devtools";
import Theme from "./material/Theme";
import router from "./Router"
import React from "react";
import "./i18n";
import {RouterProvider} from "react-router-dom";

const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            refetchOnWindowFocus: false,
            refetchOnReconnect: false,
        },
    },
});


const App = () => {
    return (
        <QueryClientProvider client={queryClient}>
            <ThemeProvider theme={Theme}>
                <RouterProvider router={router}/>
            </ThemeProvider>
            <ReactQueryDevtools initialIsOpen={false}/>
        </QueryClientProvider>
    );
}
export default App
