import {Route, Routes, useLocation, useNavigate} from "react-router-dom";
import {Box, Theme, useTheme} from "@mui/material";
import styled from "@emotion/styled";
import {CSSTransition, TransitionGroup} from "react-transition-group";
import Authority from "./Authority";
import CustomAppBar from "../sectionBar/CustomAppBar";
import CustomDrawer from "../sectionBar/CustomDrawer";
import Menu from "../menu/Menu";
import Home from "./Home";
import {useMutation} from "@tanstack/react-query";
import {apiSecurityLogout} from "../../Api";
import {useState} from "react";
import {routesPathMap} from "../../Router";

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
            navigate("/main/login");
        },
        onError: () => {
        },
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
            <Main theme={theme} open={openNav}>
                <Box sx={{width: 1}}>
                    <TransitionGroup>
                        <CSSTransition
                            timeout={300}
                            classNames={"page"}
                            key={location.pathname}
                        >
                            <Routes location={location}>
                                <Route path={"home"} element={<Home/>}/>
                                <Route path={"authority"} element={<Authority/>}/>
                                <Route path={"menuTest"} element={<Menu/>}/>
                            </Routes>
                        </CSSTransition>
                    </TransitionGroup>
                </Box>
            </Main>
        </Bgd>
    );
};
export default Section
type MainProps = {
    open: boolean;
    theme: Theme;
};
const Main = styled("main", {shouldForwardProp: (prop) => prop !== "open"})(
    ({theme, open}: MainProps) => ({
        overflow: "auto",
        flexGrow: 1,
        padding: 16,
        marginTop: 64,
        marginLeft: 0,
        transition: theme.transitions.create("margin", {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
        ...(open && {
            marginLeft: 240,
            transition: theme.transitions.create("margin", {
                easing: theme.transitions.easing.easeOut,
                duration: theme.transitions.duration.enteringScreen,
            }),
        }),
    })
);

const Bgd = styled(Box)({
    width: "100vw",
    height: "100vh",
    position: "absolute",
    display: "flex",
    backgroundColor: "white",
    backgroundImage: `url(${process.env.PUBLIC_URL}/background/menu_background.jpg)`,
    backgroundSize: "cover",
    backgroundPosition: "bottom",
});
type MenuItem = {
    to: string;
    h: string;
};
type MenuGroup = {
    title: string;
    items: MenuItem[];
};

const menus: MenuGroup[] = [
    {
        title: "Admin",
        items: [
            {to: routesPathMap("Authority"), h: "人員權限"},
            {to: routesPathMap("Role"), h: "身分權限"},
        ],
    },
    {
        title: "Employee",
        items: [
            {to: routesPathMap("Menu"), h: "Menu Test"},
        ],
    },
];
