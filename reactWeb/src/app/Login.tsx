import styled from "@emotion/styled";
import {Box, Tab, Tabs,} from "@mui/material";
import React, {useState} from "react";
import {useTranslation} from "react-i18next";
import LoginForm from "./login/LoginForm";
import RegisterForm from "./login/RegisterForm";

const Login: React.FC = () => {
    const [value, setValue] = useState("LoginForm");
    const {t} = useTranslation();
    const handleChange = (event: React.SyntheticEvent, newValue: string) => {
        setValue(newValue);
    };
    return (
        <Bgd>
            <Box
                width={"448px"}
                bgcolor={"white"}
                padding={1.5}
                borderRadius={5}
                sx={{opacity: 0.75}}
            >
                <Box sx={{borderBottom: 1, borderColor: "divider"}}>
                    <Tabs value={value} onChange={handleChange}>
                        <Tab label={t("login")} sx={{width: 1 / 2}} value={"LoginForm"}/>
                        <Tab label={t("register")} sx={{width: 1 / 2}} value={"RegisterForm"}/>
                    </Tabs>
                </Box>
                <LoginForm value={value}/>
                <RegisterForm value={value}/>
            </Box>
        </Bgd>
    );
}

const Bgd = styled(Box)({
    height: "100vh",
    width: "100vw",
    backgroundImage: `url(${process.env.PUBLIC_URL}/background/menu_background.jpg)`,
    backgroundSize: "cover",
    backgroundPosition: "40% 50%",
    position: "absolute",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
});
export default Login;
