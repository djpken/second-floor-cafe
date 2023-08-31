import {createTheme} from "@mui/material";

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
                main: "#c29e7a",
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
            body2: {
                fontSize: '0.8rem',
                '@media (min-width:1200px)': {
                    fontSize: '0.9rem'
                },
                '@media (min-width:1536px)': {
                    fontSize: '1rem'
                },
            },

            button: {
                fontSize: '1rem'
            },
            fontFamily: "AlibabaPuHuiTi-3,Arial",
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
                            width: theme.spacing(11)
                        },
                        [theme.breakpoints.up('lg')]: {
                            width: theme.spacing(15)
                        },
                        [theme.breakpoints.up('xl')]: {
                            width: theme.spacing(19)
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
                    fontSizeMedium: ({theme}) => ({
                        [theme.breakpoints.up('xs')]: {
                            fontSize: '1.5rem'
                        },
                        [theme.breakpoints.up('lg')]: {
                            fontSize: '1.9rem'
                        },
                        [theme.breakpoints.up('lg')]: {
                            fontSize: '2.3rem'
                        },
                    }),
                    fontSizeSmall: ({theme}) => ({
                        [theme.breakpoints.up('xs')]: {
                            fontSize: '1.2rem'
                        },
                        [theme.breakpoints.up('lg')]: {
                            fontSize: '1.5rem'
                        },
                        [theme.breakpoints.up('lg')]: {
                            fontSize: '1.8rem'
                        },
                    }),
                },
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
            MuiAlert: {
                styleOverrides: {
                    standard: ({theme}) => ({
                        backgroundColor: theme.palette.secondary.main,
                        fontSize: theme.typography.body2.fontSize,
                    })
                }
            }
        },
    })
;

export default Theme;
