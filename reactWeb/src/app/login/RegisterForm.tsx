import {apiSecurityRegister} from "../../Api";
import * as yup from "yup";
import {useNavigate} from "react-router-dom";
import {useForm} from "react-hook-form";
import {yupResolver} from "@hookform/resolvers/yup";
import {useMutation} from "@tanstack/react-query";
import {Button, FormHelperText, Stack} from "@mui/material";
import React from "react";
import ControllerFullWidthField from "./ControllerFullWidthField/ControllerFullWidthField";
import {InferType} from "yup";

interface registerFormProps {
    value: string;
}

interface registerProps extends InferType<typeof register> {
}

const securityRegister = async (data: registerProps) => {
    return await apiSecurityRegister({
        username: data.username,
        chineseName: data.chineseName,
        password: data.inviteCode
    });
}
const register = yup.object().shape({
    chineseName: yup.string().required("請輸入名子"),
    username: yup.string().required("請輸入工號"),
    inviteCode: yup.string().required("請輸入註冊碼"),
});


const RegisterForm: React.FC<registerFormProps> = ({value}) => {
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
            inviteCode: "",
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
    const onSubmit = (data: registerProps) => {
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
                    sx={{height: 20, paddingLeft: 2}}
                />
            </Stack>
        </Stack>
    );
}
export default RegisterForm


