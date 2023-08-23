import React, {MouseEventHandler} from "react";
import {Button, Stack} from "@mui/material";
import {useForm} from "react-hook-form";
import {useMutation, useQuery} from "@tanstack/react-query";
import {apiGetDish, apiPostDish} from "../../../../api";
import {FieldValues} from "react-hook-form/dist/types/fields";
import menu from "../../../menu/Menu";
import {MenuDishModel} from "../../../../model/MenuDishModel";

const postDish = async (formData: FormData) => {
    const response = await apiPostDish(formData)
    return response
}
const getDish = async (season: number) => {
    const response = await apiGetDish(season)
    console.log(response)
    return response.data
}

const season: number = 2023
const TestManager = () => {
    const {
        register,
        handleSubmit,
        formState: {errors},
    } = useForm();
    const menuDishMutation = useMutation({
        mutationFn: (formData: FormData) => postDish(formData)
    });
    const {
        data: menuDishQuery,
        isLoading,
        isError,
        error,
    } = useQuery<MenuDishModel[], Error>({
        queryKey: ["menuDishQuery", season],
        queryFn: () => getDish(season)
    });
    const onSubmit = (data: FieldValues) => {
        const fileList: FileList = data.inputImage
        let formData = new FormData()
        for (let i = 0; i < fileList.length; i++) {
            formData.append("files", fileList[i])
        }
        console.log(formData)
    };

    if (isLoading) {
        return (
            <div>Loading</div>
        )
    }
    if (isError) {
        return <span>Error{error?.message}</span>;
    }
    return (
        <Stack component={'form'} onSubmit={handleSubmit(onSubmit)} direction={'row'} alignItems={'center'}
               spacing={2}>
            <Button variant={'contained'} component={'label'}>
                Upload
                <input {...register("inputImage")} type="file" multiple hidden accept={"image/*"}/>
            </Button>
            <Button type={'submit'}>Submit</Button>
            {}
            {menuDishQuery.map((it) => (
                <img src={it.file} alt=""/>
            ))}
        </Stack>
    )
}
export default TestManager