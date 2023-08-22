import {Route, Routes, useLocation, useNavigate} from "react-router-dom";
import {Box, Stack, Theme, useTheme} from "@mui/material";
import styled from "@emotion/styled";
import Authority from "./page/Authority";
import Home from "./page/Home";
import {useMutation} from "@tanstack/react-query";
import React, {useState} from "react";
import {apiSecurityLogout} from "../../api";
import TestManager from "./page/TestManager";
import {initPath} from "../../BrowserRouter";
import {CustomAppBar, CustomDrawer} from "./component";

const securityLogout = async () => {
    const response = await apiSecurityLogout();
    return response.data
}

const Section = () => {
    const theme = useTheme();
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
                <Box width={{sx: '140px', lg: '220px', xl: '300px'}}/>
                <Main theme={theme} openNav={openNav}>
                    <Box sx={{width: 1}}>
                        <Routes location={location}>
                            <Route path={"home"} element={<Home/>}/>
                            <Route path={"authority"} element={<Authority/>}/>
                            <Route path={"menuManager"} element={<TestManager/>}/>
                        </Routes>
                    </Box>
                </Main>
            </Stack>
        </Bgd>
    );
};
export default Section

interface MainProps {
    openNav: boolean;
    theme: Theme;
    children?: React.ReactNode;
}

const mainStyles = ({theme, openNav}: MainProps) => ({
    overflow: "auto",
    transition: theme.transitions.create("margin", {
        easing: theme.transitions.easing.sharp,
        duration: openNav
            ? theme.transitions.duration.enteringScreen
            : theme.transitions.duration.leavingScreen,
    }),
});

const MainContainer = styled(Box, {shouldForwardProp: (prop) => prop !== "open"})(
    ({theme, openNav}: MainProps) => mainStyles({theme, openNav})
);

const Main = ({theme, children, openNav}: MainProps) => (
    <MainContainer theme={theme} openNav={openNav}>
        {children}
    </MainContainer>
);

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
