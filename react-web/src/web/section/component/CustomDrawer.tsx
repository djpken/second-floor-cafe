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

                    boxSizing: "border-box",
                },
            }}
            variant="persistent"
            anchor="left"
            open={openNav}
        >
            <DrawerHeader>
                <Typography
                    variant={'h6'}
                    component={Link}
                    to={"home"}
                    sx={{
                        margin: 1,
                        textDecoration: "none",
                        color: theme.palette.info.main,
                        "&:hover": {color: theme.palette.info.main},
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
                {menus.map((menu) => {
                    return <CustomNavBar key={menu.title} text={menu}/>
                })}
            </Stack>
            <Button
                sx={{color: theme.palette.info.main}}
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
