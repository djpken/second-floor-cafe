import Login from "./app/Login";
import Section from "./app/Section";
import Menu from "./app/Menu";
import Init from "./router/Init";
import RouterItem from "./router/RouterItem";

const Router: RouterItem[] = [
    {
        title: "Login",
        path: "/main/login/*",
        component: Login,
    },
    {
        title: "Section",
        path: "/main/section/*",
        component: Section,
    },
    {
        title: "Menu",
        path: "/main/menutest/*",
        component: Menu,
    },
    {
        title: "Init",
        path: "/",
        component: Init,
    },
];
export default Router