import {ThemeProvider} from "@mui/material";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import {ReactQueryDevtools} from "@tanstack/react-query-devtools";
import Theme from "./material/Theme";
import BrowserRouter from "./BrowserRouter"
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
                <RouterProvider router={BrowserRouter}/>
            </ThemeProvider>
            <ReactQueryDevtools initialIsOpen={false}/>
        </QueryClientProvider>
    );
}
export default App
