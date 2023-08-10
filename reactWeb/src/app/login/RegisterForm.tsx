import {apiSecurityRegister} from "../../Api";
import * as yup from "yup";
import {useNavigate} from "react-router-dom";
import {useForm} from "react-hook-form";
import {yupResolver} from "@hookform/resolvers/yup";
import {useMutation} from "@tanstack/react-query";
import {Button, FormHelperText, Stack} from "@mui/material";
import React from "react";
import ControllerFullWidthField from "./ControllerFullWidthField/ControllerFullWidthField";

const securityRegister = async (data: { username: string; chineseName: string }) => {
    return await apiSecurityRegister({
        username: data.username,
        chineseName: data.chineseName,
    });
}

const register = yup.object().shape({
    chineseName: yup.string().required("請輸入名子"),
    username: yup.string().required("請輸入工號"),
    registerCode: yup.string().required("請輸入註冊碼"),
});

interface RegisterFormProps {
    value: string;
}

const RegisterForm: React.FC<RegisterFormProps> = ({value}) => {
    const navigate = useNavigate();
    const {
        handleSubmit,
        control,
        setError,
        formState: {errors},
    } = useForm({
        resolver: yupResolver(register),
        defaultValues: {
            chineseName: "",
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
        chineseName: string;
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
                        errors.chineseName?.message ||
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
export default RegisterForm


