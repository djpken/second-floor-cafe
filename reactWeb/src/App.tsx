import {Route, Routes, useLocation} from "react-router-dom";
import {ThemeProvider} from "@mui/material";
import {CSSTransition, TransitionGroup} from "react-transition-group";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import {ReactQueryDevtools} from "@tanstack/react-query-devtools";
import Theme from "./material/Theme";
import Router from "./Router"
import DomTitle from "./router/DomTitle";

const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            refetchOnWindowFocus: false,
            refetchOnReconnect: false,
        },
    },
});


export default function App() {
    const location = useLocation();
    return (
        <QueryClientProvider client={queryClient}>
            <ThemeProvider theme={Theme}>
                <TransitionGroup>
                    <CSSTransition
                        timeout={2000}
                        classNames={"fade"}
                        key={
                            location.pathname.includes("/main/section")
                                ? "section"
                                : location.pathname
                        }
                    >
                        <Routes location={location}>
                            {Router.map((item, index) => (
                                <Route
                                    key={`routers${index}`}
                                    path={item.path}
                                    element={<DomTitle item={item}/>}
                                />
                            ))}
                        </Routes>
                    </CSSTransition>
                </TransitionGroup>
            </ThemeProvider>
            <ReactQueryDevtools initialIsOpen={false}/>
        </QueryClientProvider>
    );
}
