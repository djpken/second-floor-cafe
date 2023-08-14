import {Box, IconButton, Stack, Theme, Toolbar, useTheme} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import * as React from "react";
import MenuTwoToneIcon from '@mui/icons-material/MenuTwoTone';

interface AppProps {
    openNav: boolean;
    handleDrawerOpen: () => void;
}

export default function CustomAppBar({openNav, handleDrawerOpen}: AppProps) {
    const theme = useTheme();
    return (
        <Stack justifyContent="center"
               alignItems="center" sx={{position: 'absolute'}} margin={1}>
            <IconButton
                color="inherit"
                onClick={handleDrawerOpen}
                sx={{opacity: 1, ...(openNav && {display: "none"})}}
            >
                <MenuTwoToneIcon sx={{fontSize: {sm: 30, md: 35, lg: 40}}}/>
            </IconButton>
        </Stack>
    )
}
