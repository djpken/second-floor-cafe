import React, {createRef} from "react";
import {createBrowserRouter, Outlet, useLocation} from "react-router-dom";
import {Init, RoutesItem, TitleRouter} from "./router";
import {Menu, Section, Login} from "./web";

export const initPath: string = "/web/login"
export const routes: RoutesItem[] = [
    {
        name: "Init",
        path: "/",
        transition: false,
        extended: false,
        element: <Init path={initPath}/>,
        nodeRef: createRef()
    },
    {
        name: "Init",
        path: "/web",
        transition: false,
        extended: false,
        element: <Init path={initPath}/>,
        nodeRef: createRef()
    },
    {
        name: "Login",
        path: initPath,
        transition: true,
        extended: false,
        element: <Login/>,
        nodeRef: createRef()
    },
    {
        name: "Section",
        path: "/web/section",
        transition: true,
        extended: true,
        element: <Section/>,
        nodeRef: createRef()
    },
    {
        name: "Menu",
        path: "/web/section/employee/menu",
        transition: true,
        extended: true,
        element: <Menu/>,
        nodeRef: createRef()
    },
];
export const routesPathMap: (name: string) => string = (name: string) => (routes.find(route => route.name === name)?.path) ?? ""
const Root = () => {
    const location = useLocation();
    const {nodeRef, name, transition} = routes.find((route) => route.path === location.pathname) ?? routes[0];
    return (
        // <SwitchTransition>
        //     <CSSTransition
        //         key={transition ? location.pathname : initPath}
        //         nodeRef={nodeRef}
        //         timeout={1000}
        //         classNames={"fade"}
        //         unmountOnExit
        //     >
        <TitleRouter nodeRef={nodeRef} name={name} element={<Outlet/>}/>
        //     </CSSTransition>
        // </SwitchTransition>
    )
}

const BrowserRouter = createBrowserRouter([
    {
        path: "/",
        element: <Root/>,
        children: routes.map((route) => ({
            index: route.path === '/',
            path: (route.extended ? route.path + "/*" : route.path),
            element: route.element
        })),
        errorElement: <Login/>
    }
])

export default BrowserRouter
