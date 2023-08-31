import {Route, Routes, useLocation, useNavigate} from "react-router-dom";
import {Box, Stack, styled} from "@mui/material";
import Authority from "./page/authority/Authority";
import Home from "./page/home/Home";
import {useMutation} from "@tanstack/react-query";
import React, {useState} from "react";
import {apiSecurityLogout} from "../../api";
import TestManager from "./page/testManager/TestManager";
import {initPath} from "../../WebRouter";
import {CustomAppBar, CustomDrawer} from "./component";
import {TitleRouter} from "../../router";


const securityLogout = async () => {
    const response = await apiSecurityLogout();
    return response.data
}


const Section = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const [openNav, setOpenNav] = useState(false);
    const logoutMutation = useMutation({
        mutationFn: securityLogout,
        onSuccess: () => {
            navigate(initPath);
        }
    });

    const handleDrawerOpen = () => {
        setOpenNav(true);
    };
    const handleDrawerClose = () => {
        setOpenNav(false);
    };
    const handleLogout = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        logoutMutation.mutate();
    };
    return (
        <Bgd>
            <CustomAppBar openNav={openNav} handleDrawerOpen={handleDrawerOpen}/>
            <CustomDrawer
                openNav={openNav}
                handleDrawerClose={handleDrawerClose}
                menus={menus}
                handleLogout={handleLogout}
            />
            <Stack direction={'row'}>
                <Main open={openNav}>
                    <Routes location={location}>
                        <Route path={"home"} element={<TitleRouter name={"Home"} element={<Home/>}/>}/>
                        <Route path={"authority"} element={<TitleRouter name={"Authority"} element={<Authority/>}/>}/>
                        <Route path={"testManager"} element={<TitleRouter name={"TestManager"} element={<TestManager
                            openNav={openNav}/>}/>}/>
                    </Routes>
                </Main>
            </Stack>
        </Bgd>
    );
};
export default Section

const Main = styled('main', {shouldForwardProp: (prop) => prop !== 'open'})<{
    open?: boolean;
}>(({theme, open}) => ({
    flexGrow: 1,
    padding: theme.spacing(1),
    paddingLeft: theme.spacing(5),
    transition: theme.transitions.create('margin', {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    }),
    ...(open && {
        transition: theme.transitions.create('margin', {
            easing: theme.transitions.easing.easeOut,
            duration: theme.transitions.duration.enteringScreen,
        }),
        [theme.breakpoints.up('xs')]: {
            marginLeft: theme.spacing(7),
        },
        [theme.breakpoints.up('lg')]: {
            marginLeft: theme.spacing(11),
        },
        [theme.breakpoints.up('xl')]: {
            marginLeft: theme.spacing(15),
        }
    })
}));

const Bgd = styled(Box)({
    width: "100vw",
    height: "100vh",
    display: "flex",
    overflow: "scroll",
    backgroundAttachment: "scroll",
    backgroundImage: `url(${process.env.PUBLIC_URL}/background/menu_background.jpg)`,
    backgroundSize: "cover",
    backgroundPosition: "bottom",
});

interface MenuItem {
    to: string;
    h: string;
}

interface MenuGroup {
    title: string;
    items: MenuItem[];
}

const menus: MenuGroup[] = [
    {
        title: "Admin",
        items: [
            {to: 'Authority', h: "人員權限"},
            {to: 'role', h: "身分權限"},
            {to: 'testManager', h: "考試管理"},
        ],
    },
    {
        title: "Employee",
        items: [
            {to: 'employee/menu', h: "Menu Test"},
        ],
    },
];
