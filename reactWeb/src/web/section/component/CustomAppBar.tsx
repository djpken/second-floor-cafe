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
                onClick={handleDrawerOpen}
                sx={{opacity: 1, ...(openNav && {display: "none"})}}
            >
                <MenuTwoToneIcon htmlColor={'black'}/>
            </IconButton>
        </Stack>
    )
}
