import * as yup from "yup";
import {useNavigate} from "react-router-dom";
import {useForm} from "react-hook-form";
import {yupResolver} from "@hookform/resolvers/yup";
import {useMutation} from "@tanstack/react-query";
import {Button, FormHelperText, Stack, useTheme} from "@mui/material";
import React from "react";
import ControllerFullWidthField from "../component/ControllerFullWidthField";
import {FormProps} from "../Login";
import {AxiosError} from "axios";
import {apiSecurityRegister} from "../../../api";
import {ApiResponse, TokenModel} from "../../../model";
import {SysUser} from "../../../entity";


const register = yup.object().shape({
    chineseName: yup.string().required("請輸入名子"),
    username: yup.string().required("請輸入工號"),
    inviteCode: yup.string().required("請輸入註冊碼"),
});


const securityRegister = async (sysUser: SysUser) => {
    const response = await apiSecurityRegister(sysUser);
    return response.data.data
}
const RegisterForm = ({display, path}: FormProps) => {
    const theme = useTheme();
    const navigate = useNavigate();
    const {
        handleSubmit,
        control,
        setError,
        formState: {errors},
    } = useForm({
        resolver: yupResolver(register),
    });
    const registerMutation = useMutation({
        mutationFn: securityRegister,
        onSuccess: (response) => {
            localStorage.setItem("authorization", response.authorization);
            localStorage.setItem("authority", response.authority);
            localStorage.setItem("user", JSON.stringify(response.sysUser));
            navigate(path);
        },
        onError: (error: AxiosError<ApiResponse<TokenModel>>) => {
            setError("root.message", {
                message: error.response?.data.message || error.message,
            });
        },
    });
    const onSubmit = (data: { chineseName: string, username: string, inviteCode: string }) => {
        registerMutation.mutate({id: 0, password: data.inviteCode, ...data});
    };
    return (
        <Stack
            display={display ? "block" : "none"}
            component={"form"}
            padding={0.8}
            spacing={0.8}
            onSubmit={handleSubmit(onSubmit)}
        >
            <ControllerFullWidthField
                control={control}
                name={"chineseName"}
                error={errors.chineseName}
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
                        name={"inviteCode"}
                        error={errors.inviteCode}
                        label={"註冊碼"}
                        sx={{width: "0.5"}}
                    />
                    <Stack width={0.5} justifyContent={"center"} alignItems={"center"}>
                        <Button type={"submit"}>註冊</Button>
                    </Stack>
                </Stack>
                <FormHelperText
                    children={
                        errors.chineseName?.message ||
                        errors.username?.message ||
                        errors.inviteCode?.message ||
                        errors.root?.message
                    }
                    error
                    sx={{height: theme.spacing(1.2), paddingLeft: 0.2}}
                />
            </Stack>
        </Stack>
    );
}
export default RegisterForm


