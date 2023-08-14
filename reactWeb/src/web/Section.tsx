import {Route, Routes, useLocation, useNavigate} from "react-router-dom";
import {Box, Stack, Theme, useTheme} from "@mui/material";
import styled from "@emotion/styled";
import Authority from "./sectionPage/Authority";
import CustomAppBar from "./section/CustomAppBar";
import CustomDrawer from "./section/CustomDrawer";
import Home from "./sectionPage/Home";
import {useMutation} from "@tanstack/react-query";
import React, {useState} from "react";
import {apiSecurityLogout} from "../api";
import MenuManager from "./sectionPage/MenuManager";
import {initPath} from "../Router";

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
            <Stack direction={'row'} spacing={2}>
                <Main theme={theme} openNav={openNav}>
                    <Box sx={{width: 1}}>
                        <Routes location={location}>
                            <Route path={"home"} element={<Home/>}/>
                            <Route path={"authority"} element={<Authority/>}/>
                            <Route path={"menuManager"} element={<MenuManager/>}/>
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

const mainStyles = ({ theme, openNav }:MainProps) => ({
    overflow: "auto",
    transition: theme.transitions.create("margin", {
        easing: theme.transitions.easing.sharp,
        duration: openNav
            ? theme.transitions.duration.enteringScreen
            : theme.transitions.duration.leavingScreen,
    }),
});;

const MainContainer = styled(Box, {shouldForwardProp: (prop) => prop !== "open"})(
    ({theme, openNav}: MainProps) => mainStyles({theme, openNav})
);

const Main = ({theme, children, openNav}: MainProps) => (
    <MainContainer theme={theme} openNav={openNav}
                   sx={{paddingLeft: {md: -180, lg: -240, xl: -300}}}>
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
            {to: 'menuManager', h: "考試管理"},
        ],
    },
    {
        title: "Employee",
        items: [
            {to: 'employee/menu', h: "Menu Test"},
        ],
    },
];
