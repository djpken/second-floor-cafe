import * as yup from "yup";
import {useNavigate} from "react-router-dom";
import {useForm} from "react-hook-form";
import {yupResolver} from "@hookform/resolvers/yup";
import {useMutation} from "@tanstack/react-query";
import {Button, FormHelperText, Stack} from "@mui/material";
import React from "react";
import ControllerFullWidthField from "./ControllerFullWidthField/ControllerFullWidthField";
import {FormProps} from "../Login";
import {AxiosError} from "axios";
import {ApiResponse} from "../../model/PromiseApi";
import TokenModel from "../../model/TokenModel";
import {apiSecurityRegister} from "../../api";
import SysUser from "../../entity/SysUser";


const register = yup.object().shape({
    chineseName: yup.string().required("請輸入名子"),
    username: yup.string().required("請輸入工號"),
    inviteCode: yup.string().required("請輸入註冊碼"),
});


const securityRegister = async (sysUser: SysUser) => {
    const response = await apiSecurityRegister(sysUser);
    return response.data
}
const RegisterForm = ({display, path}: FormProps) => {
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
            padding={3}
            spacing={2}
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
                        name={"password"}
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
                    sx={{height: 20, paddingLeft: 2}}
                />
            </Stack>
        </Stack>
    );
}
export default RegisterForm


