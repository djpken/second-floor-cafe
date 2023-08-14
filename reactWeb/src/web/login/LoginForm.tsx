import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import {useForm} from "react-hook-form";
import {yupResolver} from "@hookform/resolvers/yup";
import {useMutation} from "@tanstack/react-query";
import {Button, FormHelperText, InputAdornment, Stack} from "@mui/material";
import IconButton from "@mui/material/IconButton";
import {Visibility, VisibilityOff} from "@mui/icons-material";
import * as yup from "yup";
import ControllerFullWidthField from "./ControllerFullWidthField/ControllerFullWidthField";
import {FormProps} from "../Login";
import {AxiosError} from "axios";
import TokenModel from "../../model/TokenModel";
import {ApiResponse} from "../../model/PromiseApi";
import {apiSecurityLogin} from "../../api";
import SysUser from "../../entity/SysUser";


const user = yup.object().shape({
    username: yup.string().required("請輸入工號"),
    password: yup.string().required("請輸入密碼"),
});

const securityLogin = async (sysUser: SysUser) => {
    const response = await apiSecurityLogin(sysUser);
    return response.data
}


const LoginForm = ({display, path}: FormProps) => {
    const navigate = useNavigate();
    const [showPassword, setShowPassword] = useState(false);
    const {
        handleSubmit,
        control,
        setError,
        formState: {errors},
    } = useForm({
        resolver: yupResolver(user)
    });
    const loginMutation = useMutation({
        mutationFn: securityLogin,
        onSuccess: (tokenResponse) => {
            localStorage.setItem("authorization", tokenResponse.authorization);
            localStorage.setItem("authority", tokenResponse.authority);
            localStorage.setItem("user", JSON.stringify(tokenResponse.sysUser));
            navigate(path);
        },
        onError: (error: AxiosError<ApiResponse<TokenModel>>) => {
            setError("root", {
                message: error.response?.data.message || error.message,
            });
        },
    });
    const onSubmit = (data: { username: string, password: string }) => {
        loginMutation.mutate({id:0,...data});
    };
    const handleClickShowPassword = () => setShowPassword((show) => !show);
    const handleMouseDownPassword = (event: React.MouseEvent) => {
        event.preventDefault();
    };
    return (
        <Stack
            display={display ? "block" : "none"}
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

export default LoginForm