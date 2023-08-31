import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import {useForm} from "react-hook-form";
import {yupResolver} from "@hookform/resolvers/yup";
import {useMutation} from "@tanstack/react-query";
import {Button, FormHelperText, InputAdornment, Stack, useTheme} from "@mui/material";
import IconButton from "@mui/material/IconButton";
import {Visibility, VisibilityOff} from "@mui/icons-material";
import * as yup from "yup";
import ControllerFullWidthField from "../component/ControllerFullWidthField";
import {FormProps} from "../Login";
import {AxiosError} from "axios";
import {apiSecurityLogin} from "../../../api";
import {SysUser} from "../../../entity";
import {ApiResponse, TokenModel} from "../../../model";


const user = yup.object().shape({
    username: yup.string().required("請輸入工號"),
    password: yup.string().required("請輸入密碼"),
});

const securityLogin = async (sysUser: SysUser) => {
    const response = await apiSecurityLogin(sysUser);
    return response.data.data
}


const LoginForm = ({display, path}: FormProps) => {
    const theme = useTheme();
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
    const onSubmit = (data: {
        username: string,
        password: string
    }) => {
        loginMutation.mutate({id: 0, ...data});
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
            padding={0.8}
            spacing={0.8}
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
                                    {showPassword ? <VisibilityOff fontSize={'small'} htmlColor={'black'}/> :
                                        <Visibility htmlColor={'black'} fontSize={'small'}/>}
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
                    sx={{height: theme.spacing(1.2), paddingLeft: 0.2}}
                ></FormHelperText>
            </Stack>
            <Stack paddingX={9} justifyContent={"center"}>
                <Button type={"submit"}>登入</Button>
            </Stack>
        </Stack>
    );
}

export default LoginForm