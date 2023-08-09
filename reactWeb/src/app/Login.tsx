import styled from "@emotion/styled";
import {useNavigate} from "react-router-dom";
import {Box, Button, FormHelperText, InputAdornment, Stack, Tab, Tabs, TextField,} from "@mui/material";
import React, {useState} from "react";
import {Controller, useForm} from "react-hook-form";
import {Visibility, VisibilityOff} from "@mui/icons-material";
import IconButton from "@mui/material/IconButton";
import * as yup from "yup";
import {yupResolver} from "@hookform/resolvers/yup";
import {useMutation} from "@tanstack/react-query";
import {apiSecurityLogin, apiSecurityRegister} from "../Api";

export default function Login(): JSX.Element {
    const [value, setValue] = useState("LoginForm");
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
                        <Tab label="ERP登入" sx={{width: 1 / 2}} value={"LoginForm"}/>
                        <Tab label="ERP註冊" sx={{width: 1 / 2}} value={"RegisterForm"}/>
                    </Tabs>
                </Box>
                <LoginForm value={value}/>
                <RegisterForm value={value}/>
            </Box>
        </Bgd>
    );
}

const user = yup.object().shape({
    username: yup.string().required("請輸入工號"),
    password: yup.string().required("請輸入密碼"),
});

async function securityLogin(data: { username: string; password: string }) {
    return await apiSecurityLogin({
        userName: data.username,
        password: data.password,
    });
}

async function securityRegister(data: { username: string; nickname: string }) {
    return await apiSecurityRegister({
        userName: data.username,
        nickName: data.nickname,
    });
}

function LoginForm({value, ...props}: { value: string }): JSX.Element {
    const navigate = useNavigate();
    const [showPassword, setShowPassword] = useState(false);
    const {
        handleSubmit,
        control,
        setError,
        formState: {errors},
    } = useForm({
        resolver: yupResolver(user),
        defaultValues: {
            username: "",
            password: "",
        },
    });
    const loginMutation = useMutation({
        mutationFn: securityLogin,
        onSuccess: (response) => {
            localStorage.setItem("token", response.data.Authorization);
            localStorage.setItem("auth", response.data.authority);
            localStorage.setItem("user", JSON.stringify(response.data.user));
            navigate("/main/section");
        },
        onError: (error: any) => {
            setError("root", {
                message: error.response.data.message || error.message,
            });
        },
    });
    const onSubmit = (data: { username: string; password: string }) => {
        console.log(data);
        loginMutation.mutate(data);
    };
    const handleClickShowPassword = () => setShowPassword((show) => !show);
    const handleMouseDownPassword = (event: React.MouseEvent) => {
        event.preventDefault();
    };
    return (
        <Stack
            display={value === "LoginForm" ? "block" : "none"}
            component={"form"}
            onSubmit={handleSubmit(onSubmit)}
            padding={3}
            spacing={2}
        >
            <ControllerFullWidthField
                control={control}
                name={"username"}
                label={"工號"}
                error={errors.username}
            />
            <Stack>
                <ControllerFullWidthField
                    control={control}
                    name={"password"}
                    label={"密碼"}
                    error={errors.password}
                    type={showPassword ? "text" : "password"}
                    InputProps={{
                        endAdornment: (
                            <InputAdornment position="end">
                                <IconButton
                                    onClick={handleClickShowPassword}
                                    onMouseDown={handleMouseDownPassword}
                                    edge="end"
                                >
                                    {showPassword ? <VisibilityOff/> : <Visibility/>}
                                </IconButton>
                            </InputAdornment>
                        ),
                    }}
                />
                <FormHelperText
                    children={
                        errors.username?.message ||
                        errors.password?.message ||
                        errors.root?.message
                    }
                    error
                    sx={{height: 20, paddingLeft: 2}}
                ></FormHelperText>
            </Stack>
            <Stack paddingX={16} justifyContent={"center"}>
                <Button type={"submit"}>登入</Button>
            </Stack>
        </Stack>
    );
}

const register = yup.object().shape({
    nickname: yup.string().required("請輸入名子"),
    username: yup.string().required("請輸入工號"),
    registerCode: yup.string().required("請輸入註冊碼"),
});

function RegisterForm({value, ...props}: { value: string }): JSX.Element {
    const navigate = useNavigate();
    const {
        handleSubmit,
        control,
        setError,
        formState: {errors},
    } = useForm({
        resolver: yupResolver(register),
        defaultValues: {
            nickname: "",
            username: "",
            registerCode: "",
        },
    });
    const registerMutation = useMutation({
        mutationFn: securityRegister,
        onSuccess: (response) => {
            localStorage.setItem("token", response.data.Authorization);
            localStorage.setItem("auth", response.data.authority);
            localStorage.setItem("user", JSON.stringify(response.data.user));
            navigate("/main/section");
        },
        onError: (error: any) => {
            setError("root.message", {
                message: error.response?.data.message || error.message,
            });
        },
    });
    const onSubmit = (data: {
        nickname: string;
        username: string;
        registerCode: string;
    }) => {
        console.log(data);
        if (data.registerCode !== "SF") {
            setError("root", {
                message: "註冊碼輸入錯誤",
            });
            return;
        }
        registerMutation.mutate(data);
    };
    return (
        <Stack
            display={value === "RegisterForm" ? "block" : "none"}
            component={"form"}
            padding={3}
            spacing={2}
            onSubmit={handleSubmit(onSubmit)}
        >
            <ControllerFullWidthField
                control={control}
                name={"nickname"}
                error={errors.nickname}
                label={"名字"}
            />
            <ControllerFullWidthField
                control={control}
                name={"username"}
                error={errors.username}
                label={"工號"}
            />
            <Stack>
                <Stack direction={"row"}>
                    <ControllerFullWidthField
                        control={control}
                        name={"registerCode"}
                        error={errors.registerCode}
                        label={"註冊碼"}
                        sx={{width: "0.5"}}
                    />
                    <Stack width={0.5} justifyContent={"center"} alignItems={"center"}>
                        <Button type={"submit"}>註冊</Button>
                    </Stack>
                </Stack>
                <FormHelperText
                    children={
                        errors.nickname?.message ||
                        errors.username?.message ||
                        errors.registerCode?.message ||
                        errors.root?.message
                    }
                    error
                    sx={{height: 20, paddingLeft: 2}}
                />
            </Stack>
        </Stack>
    );
}

interface FieldProps {
    control: any;
    name: string;
    error: any;

    [key: string]: any;
}

const ControllerFullWidthField: React.FC<FieldProps> = ({
                                                            control,
                                                            name,
                                                            error,
                                                            ...props
                                                        }) => {
    return (
        <Controller
            control={control}
            name={name}
            render={({field}) => (
                <TextField {...props} error={Boolean(error)} fullWidth {...field} />
            )}
        />
    );
};
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
