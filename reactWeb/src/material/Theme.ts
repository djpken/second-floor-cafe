import {createTheme} from "@mui/material";

const breakpoints = {
    values: {
        xs: 0,
        sm: 600, // Phone
        md: 900, // Tablet/Laptop
        lg: 1200, // Desktop
        xl: 1536
    }
};
const Theme =
    createTheme({
        breakpoints,
        palette: {
            primary: {
                main: "#6C757D",
            },
            secondary: {
                main: "#cea583",
            },

            info: {
                main: "#FFFFFF",
            },
        },
        typography: {
            h1: {
                fontSize: 24
            },
            subtitle1: {
                fontSize: 14
            },
            body1: {
                fontSize: 14
            },
            fontFamily: "Alibaba,Arial",
        },
        components: {
            MuiTextField: {
                defaultProps: {
                    variant: "filled",
                },
            },
            MuiButton: {
                defaultProps: {
                    variant: "contained",
                },
            },
            MuiDrawer: {
                styleOverrides: {
                    paper: ({theme}) => ({
                        backgroundColor: theme.palette.primary.main,
                    }),
                },
            },
            MuiListItemText: {
                styleOverrides: {
                    root: ({theme}) => ({
                        color: theme.palette.info.main,
                    }),
                },
            },
            MuiSvgIcon: {
                styleOverrides: {
                    root: ({theme}) => ({
                        color: 'black',
                    }),
                },
            },
            MuiListItem: {
                styleOverrides: {
                    root: ({theme}) => ({
                        color: theme.palette.info.main,
                    }),
                },
            },
            MuiIconButton: {
                styleOverrides: {
                    root: ({theme}) => ({
                        color: theme.palette.info.main,
                    }),
                },
            },
            MuiAppBar: {
                styleOverrides: {
                    root: {
                        height: "64px",
                    },
                },
            },
        },
    });
export default Theme;
