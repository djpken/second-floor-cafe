import {createTheme} from "@mui/material";

const Theme =
    createTheme({
        palette: {
            primary: {
                main: "#6C757D",
            },
            info: {
                main: "#FFFFFF",
            },
        },
        typography: {
            fontSize: 14,
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
                    paper: {
                        backgroundColor: "#6C757D",
                    },
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
