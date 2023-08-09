import { IconButton, Theme, Toolbar, useTheme } from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import * as React from "react";
import styled from "@emotion/styled";
import MuiAppBar from "@mui/material/AppBar";

interface AppProps {
  openNav: boolean;
  handleDrawerOpen: () => void;
}

export default function CustomAppBar({ openNav, handleDrawerOpen }: AppProps) {
  const theme = useTheme();
  return (
    <AppBar theme={theme} position="fixed" open={handleDrawerOpen}>
      <Toolbar>
        <IconButton
          color="inherit"
          onClick={handleDrawerOpen}
          sx={{ opacity: 1, mr: 2, ...(openNav && { display: "none" }) }}
        >
          <MenuIcon />
        </IconButton>
      </Toolbar>
    </AppBar>
  );
}
const drawerWidth = 200;

interface AppBarProps {
  openNav?: boolean;
  theme: Theme;
  open: () => void;
}

const AppBar = styled(MuiAppBar, {
  shouldForwardProp: (prop) => prop !== "openNav",
})<AppBarProps>(({ theme, openNav }) => ({
  transition: theme.transitions.create(["margin", "width"], {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }) as string,
  ...(openNav && {
    width: `calc(100% - ${drawerWidth}px)`,
    marginLeft: `${drawerWidth}px`,
    transition: theme.transitions.create(["margin", "width"], {
      easing: theme.transitions.easing.easeOut,
      duration: theme.transitions.duration.enteringScreen,
    }),
  }),
}));
