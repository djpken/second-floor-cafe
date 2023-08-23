import {createTheme, responsiveFontSizes} from "@mui/material";

const Theme =
    createTheme({
        breakpoints:
            {
                values: {
                    xs: 0,
                    sm: 600, // Phone
                    md: 900, // Tablet/Laptop
                    lg: 1200, // Desktop
                    xl: 1536
                }
            },
        spacing: (factor: number) => `${factor}rem`,
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
            htmlFontSize: 10,
            h1: {
                fontSize: '1rem',
            },
            h6: {
                fontSize: "1rem",
                '@media (min-width:1200px)': {
                    fontSize: '1.2rem'
                },
                '@media (min-width:1536px)': {
                    fontSize: '1.4rem'
                },
            },
            subtitle1: {
                fontSize: "1.2rem"
            },
            body1: {
                fontSize: '0.8rem',
                '@media (min-width:1200px)': {
                    fontSize: '1rem'
                },
                '@media (min-width:1536px)': {
                    fontSize: '1.2rem'
                },
            },
            button: {
                fontSize: '1rem'
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
                        [theme.breakpoints.up('xs')]: {
                            width: 140
                        },
                        [theme.breakpoints.up('lg')]: {
                            width: 220
                        },
                        [theme.breakpoints.up('lg')]: {
                            width: 300
                        },
                    }),
                },
            },
            MuiListItemText: {
                styleOverrides: {
                    root: ({theme}) => ({
                        color: theme.palette.info.main,
                    }),
                }
                ,
            }
            ,
            MuiSvgIcon: {
                styleOverrides: {
                    root: ({theme}) => ({
                        color: 'black',
                        [theme.breakpoints.up('xs')]: {
                            fontSize: '1.5rem'
                        },
                        [theme.breakpoints.up('lg')]: {
                            fontSize: '1.9rem'
                        },
                        [theme.breakpoints.up('lg')]: {
                            fontSize: '2.4rem'
                        },
                    }),
                }
                ,
            }
            ,
            MuiListItem: {
                styleOverrides: {
                    root: ({theme}) => ({
                        color: theme.palette.info.main,
                    }),
                }
                ,
            }
            ,
            MuiIconButton: {
                styleOverrides: {
                    root: ({theme}) => ({
                        color: theme.palette.info.main,
                    }),
                }
                ,
            }
            ,
            MuiAppBar: {
                styleOverrides: {
                    root: {
                        height: "64px",
                    }
                    ,
                }
                ,
            }
            ,
        },
    })
;

export default Theme;
