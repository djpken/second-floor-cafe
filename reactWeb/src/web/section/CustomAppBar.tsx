import {IconButton, Stack} from "@mui/material";
import * as React from "react";
import MenuTwoToneIcon from '@mui/icons-material/MenuTwoTone';

interface AppProps {
    openNav: boolean;
    handleDrawerOpen: () => void;
}

export default function CustomAppBar({openNav, handleDrawerOpen}: AppProps) {
    return (
        <Stack justifyContent="center"
               alignItems="center" sx={{position: 'absolute', backgroundColor: 'secondary.main'}} margin={1}
               borderRadius={2}>
            <IconButton
                color="inherit"
                onClick={handleDrawerOpen}
                sx={{opacity: 1, ...(openNav && {display: "none"})}}
            >
                <MenuTwoToneIcon sx={{fontSize: {md: 20, lg: 25, xl: 30}}}/>
            </IconButton>
        </Stack>
    )
}
