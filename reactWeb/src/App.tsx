import {Route, Routes, useLocation} from "react-router-dom";
import {ThemeProvider} from "@mui/material";
import {CSSTransition, TransitionGroup} from "react-transition-group";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import {ReactQueryDevtools} from "@tanstack/react-query-devtools";
import Theme from "./material/Theme";
import Router from "./Router"
import DomTitle from "./router/DomTitle";
import React from "react";
import "./i18n";

const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            refetchOnWindowFocus: false,
            refetchOnReconnect: false,
        },
    },
});


const App: React.FC = () => {
    const location = useLocation()
    const sectionRoute = Router.find(route => route.title === "Section")
    return (
        <QueryClientProvider client={queryClient}>
            <ThemeProvider theme={Theme}>
                <TransitionGroup>
                    <CSSTransition
                        timeout={2000}
                        classNames={"fade"}
                        key={
                            location.pathname.includes(sectionRoute?.path || "")
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
export default App
