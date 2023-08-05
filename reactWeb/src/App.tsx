import { Route, Routes, useLocation, useNavigate } from "react-router-dom";
import Login from "./main/Login";
import Section from "./main/Section";
import { createTheme, ThemeProvider } from "@mui/material";
import { TransitionGroup, CSSTransition } from "react-transition-group";
import Menu from "./main/Menu";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";
import React from "react";
const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
      refetchOnReconnect: false,
    },
  },
});
const theme = createTheme({
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
        root: ({ theme }) => ({
          color: theme.palette.info.main,
        }),
      },
    },
    MuiSvgIcon: {
      styleOverrides: {
        root: ({ theme }) => ({
          color: 'black',
        }),
      },
    },
    MuiListItem: {
      styleOverrides: {
        root: ({ theme }) => ({
          color: theme.palette.info.main,
        }),
      },
    },
    MuiIconButton: {
      styleOverrides: {
        root: ({ theme }) => ({
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
const Init:React.FC = () => {
  const navigate = useNavigate();
  React.useEffect(() => {
    navigate("/main/login");
  }, [navigate]);
  return <></>;
};
const DomTitle:React.FC<{item:RouterItem}> = ({ item }) => {
  document.title = item.title ;
  return <item.component />;
};
interface RouterItem {
  title: string;
  path: string;
  component: React.ComponentType<any>;
}
const routers:RouterItem[] = [
  {
    title: "Login",
    path: "/main/login/*",
    component: Login,
  },
  {
    title: "Section",
    path: "/main/section/*",
    component: Section,
  },
  {
    title: "Menu",
    path: "/main/menutest/*",
    component: Menu,
  },
  {
    title: "Init",
    path: "/",
    component: Init,
  },
];

export default function App() {
  const location = useLocation();

  return (
    <QueryClientProvider client={queryClient}>
      <ThemeProvider theme={theme}>
        <TransitionGroup>
          <CSSTransition
            timeout={2000}
            classNames={"fade"}
            key={
              location.pathname.includes("/main/section")
                ? "section"
                : location.pathname
            }
          >
            <Routes location={location}>
              {routers.map((item, index) => (
                <Route
                  key={`routers${index}`}
                  path={item.path}
                  element={<DomTitle item={item} />}
                />
              ))}
            </Routes>
          </CSSTransition>
        </TransitionGroup>
      </ThemeProvider>
      <ReactQueryDevtools initialIsOpen={false} />
    </QueryClientProvider>
  );
}
