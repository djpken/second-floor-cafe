import React, {createRef} from "react";
import {createBrowserRouter, Outlet, useLocation} from "react-router-dom";
import {Init, RoutesItem, TitleRouter} from "./router";
import {Login, Menu, Section} from "./web";

export const initPath: string = "/web/login"
export const webRoutes: RoutesItem[] = [
    {
        name: "Init",
        path: "/",
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
export const routesPathMap: (name: string) => string = (name: string) => (webRoutes.find(route => route.name === name)?.path) ?? ""
export const Root = () => {
    const location = useLocation();
    const {nodeRef, name, transition} = webRoutes.find((route) => route.path === location.pathname) ?? webRoutes[0];
    return (
        <TitleRouter nodeRef={nodeRef} name={name} element={<Outlet/>}/>
    )
}

const WebRouter = createBrowserRouter([
    {
        path: "/",
        element: <Root/>,
        children: webRoutes.map((route) => ({
            index: route.path === '/',
            path: (route.extended ? route.path + "/*" : route.path),
            element: route.element
        })),
        errorElement: <Login/>
    }
])

export default WebRouter
