import React, {useState} from "react";
import {Box, Button, Drawer, IconButton, Stack, Typography, useTheme,} from "@mui/material";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import CustomNavBar from "./CustomNavBar";
import {Link} from "react-router-dom";
import {styled} from "@mui/system";

interface CustomDrawerProps {
    openNav: boolean;
    handleDrawerClose: () => void;
    menus: any[];
    handleLogout: any;
}

export default function CustomDrawer({
                                         openNav,
                                         handleDrawerClose,
                                         menus,
                                         handleLogout,
                                     }: CustomDrawerProps) {
    const theme = useTheme();
    const [auth] = useState(localStorage.getItem("auth"));
    return (
        <Drawer
            sx={{
                "& .MuiDrawer-paper": {
                    width: {md: 180, lg: 240, xl: 300},
                    boxSizing: "border-box",
                },
            }}
            variant="persistent"
            anchor="left"
            open={openNav}
        >
            <DrawerHeader>
                <Typography
                    fontSize={"20px"}
                    component={Link}
                    to={"home"}
                    sx={{
                        margin: "20px",
                        textDecoration: "none",
                        color: "info.main",
                        "&:hover": {color: "info.main"},
                    }}
                >
                    SecondFloor
                </Typography>

                <Box width={"20px"}></Box>
                <IconButton onClick={handleDrawerClose}>
                    <ChevronLeftIcon/>
                </IconButton>
            </DrawerHeader>
            <Stack height={"83vh"}>
                {/*{menus.map((menu) => {*/}
                {/*  if (auth?.includes(menu.title.toLowerCase())) {*/}
                {/*    return <CustomNavBar key={menu.title} text={menu} />;*/}
                {/*  }*/}
                {/*  return null;*/}
                {/*})}*/}
                {menus.map((menu) => {
                    return <CustomNavBar key={menu.title} text={menu}/>
                })}
            </Stack>
            <Button
                sx={{color: "info.main"}}
                onClick={handleLogout}
                variant={"outlined"}
            >
                Logout
            </Button>
        </Drawer>
    );
}
const DrawerHeader = styled("div")(({theme}) => ({
    display: "flex",
    alignItems: "center",
    padding: "0 1",
    ...(theme: any) => theme.mixins.toolbar,
    justifyContent: "flex-end",
}));
