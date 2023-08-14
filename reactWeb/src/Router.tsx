import Login from "./web/Login";
import Section from "./web/Section";
import Menu from "./web/Menu";
import MenuManager from "./web/sectionPage/MenuManager";
import {DomTitle, Init, RoutesItem} from "./router/RoutesItem";
import React, {createRef, useEffect} from "react";
import {createBrowserRouter, Outlet, useLocation} from "react-router-dom";

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
        <DomTitle nodeRef={nodeRef} name={name} element={<Outlet/>}/>
        //     </CSSTransition>
        // </SwitchTransition>
    )
}

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root/>,
        children: routes.map((route) => ({
            index: route.path === '/',
            path: (route.extended ? route.path + "/*" : route.path),
            element: route.element
        }))
    }
])

export default router
