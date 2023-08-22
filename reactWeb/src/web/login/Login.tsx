import styled from "@emotion/styled";
import {Box, Tab, Tabs,} from "@mui/material";
import React, {useState} from "react";
import {useTranslation} from "react-i18next";
import LoginForm from "./Form/LoginForm";
import RegisterForm from "./Form/RegisterForm";
import {routesPathMap} from "../../BrowserRouter";

export interface FormProps {
    display: boolean,
    path: string
}

const form: Function[] = [
    LoginForm,
    RegisterForm
]
const Login = () => {
    const [value, setValue] = useState<string>(form[0].name);
    const {t} = useTranslation();
    const [sectionPath] = useState<string>(routesPathMap("Section"))
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
                        {form.map((item, index) =>
                            <Tab key={index} label={t(item.name)} sx={{width: 1 / 2}} value={item.name}/>
                        )}
                    </Tabs>
                </Box>
                <LoginForm display={value === LoginForm.name} path={sectionPath}/>
                <RegisterForm display={value === RegisterForm.name} path={sectionPath}/>
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
