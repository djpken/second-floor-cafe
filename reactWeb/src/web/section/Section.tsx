import {Route, Routes, useLocation, useNavigate} from "react-router-dom";
import {Box, Stack, styled} from "@mui/material";
import Authority from "./page/Authority";
import Home from "./page/Home";
import {useMutation} from "@tanstack/react-query";
import React, {useState} from "react";
import {apiSecurityLogout} from "../../api";
import TestManager from "./page/testManager/TestManager";
import {initPath} from "../../BrowserRouter";
import {CustomAppBar, CustomDrawer} from "./component";


const securityLogout = async () => {
    const response = await apiSecurityLogout();
    return response.data
}

const Section = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const [open, setOpen] = useState(false);
    const logoutMutation = useMutation({
        mutationFn: securityLogout,
        onSuccess: () => {
            navigate(initPath);
        }
    });

    const handleDrawerOpen = () => {
        setOpen(true);
    };
    const handleDrawerClose = () => {
        setOpen(false);
    };
    const handleLogout = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        logoutMutation.mutate();
    };
    return (
        <Bgd>
            <CustomAppBar openNav={open} handleDrawerOpen={handleDrawerOpen}/>
            <CustomDrawer
                openNav={open}
                handleDrawerClose={handleDrawerClose}
                menus={menus}
                handleLogout={handleLogout}
            />
            <Stack direction={'row'}>
                <Main open={open}>
                    <Routes location={location}>
                        <Route path={"home"} element={<Home/>}/>
                        <Route path={"authority"} element={<Authority/>}/>
                        <Route path={"testManager"} element={<TestManager/>}/>
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
    paddingLeft: theme.spacing(4),
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
            paddingLeft: theme.spacing(14),
        },
        [theme.breakpoints.up('lg')]: {
            paddingLeft: theme.spacing(22),
        },
        [theme.breakpoints.up('xl')]: {
            paddingLeft: theme.spacing(30),
        }
    })
}));

const Bgd = styled(Box)({
    width: "100vw",
    height: "100vh",
    display: "flex",
    backgroundColor: "white",
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
