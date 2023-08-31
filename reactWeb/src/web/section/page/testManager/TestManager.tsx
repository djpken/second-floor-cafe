import {Alert, Typography, Box, Button, CircularProgress, Stack, useTheme, ButtonGroup} from "@mui/material";
import {useForm} from "react-hook-form";
import {useMutation, useQuery} from "@tanstack/react-query";
import {apiGetDish, apiPostDish, apiUpdateDish} from "../../../../api";
import {FieldValues} from "react-hook-form/dist/types/fields";
import {MenuDishModel} from "../../../../model/MenuDishModel";
import React, {useState} from "react";
import TestManagerCard from "./component/TestManagerCard";
import Grid2 from "@mui/material/Unstable_Grid2";
import {MenuDishText} from "../../../../entity";

const postDish = async (formData: FormData) => {
    const response = await apiPostDish(formData)
    return response.data.data

}
const getDish = async (season: number) => {
    const response = await apiGetDish(season)
    return response.data.data
}
const updateDish = async (menuDishText: MenuDishText) => {
    const response = await apiUpdateDish(menuDishText)
    return response.data.data
}

interface TestManageProps {
    openNav: boolean
}

interface HandleUpdateTextProps {
    id: number,
    chineseName: string,
    hint: string
}

export interface TestManagerCardProps extends HandleUpdateTextProps {
    handleUpdateText: (props: HandleUpdateTextProps) => void
    image: string
}


const season: number = 2023
const TestManager = ({openNav}: TestManageProps) => {
    const theme = useTheme()
    const [isUpdateLoading, setIsUpdateLoading] = useState<boolean>(false)
    const {
        register,
        handleSubmit,
        formState: {errors},
    } = useForm();
    const mutationPostDish = useMutation({
        mutationFn: (formData: FormData) => postDish(formData)
    });
    const mutationUpdataDish = useMutation({
        mutationFn: (menuDishText: MenuDishText) => updateDish(menuDishText)
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
            let file = fileList[i]
            formData.append("files", file, file.name + "." + i)
        }
        mutationPostDish.mutate(formData)
    };
    const handleUpdateText = ({id, chineseName, hint}: HandleUpdateTextProps) => {
        setIsUpdateLoading(true)
        mutationUpdataDish.mutate({menuDishText: {id, chineseName, hint}}:MenuDis)
    }
    if (isLoading) {
        return (
            <Box paddingX={3.5}>
                <Alert variant={'standard'} severity={'info'} sx={{width: "15rem"}}>
                    <Stack direction={'row'} spacing={2}>
                        <Typography>載入中...</Typography>
                    </Stack>
                </Alert>
            </Box>
        )
    }
    if (isError) {
        return (
            <Box paddingX={3.5}>
                <Alert variant={'standard'} severity={'error'} sx={{width: "15rem"}}>
                    <Stack direction={'row'} spacing={2}>
                        <Typography>{error?.message}</Typography>
                    </Stack>
                </Alert>
            </Box>
        );
    }
    return (
        <Stack component={'form'} onSubmit={handleSubmit(onSubmit)} spacing={0.5}>
            <Stack direction={'row'} spacing={2} paddingLeft={0.5}>
                <Alert variant={'standard'} severity={isUpdateLoading ? 'info' : 'success'} sx={{width: "15rem"}}>
                    {
                        isUpdateLoading ? <Stack spacing={2} direction={'row'}>
                            <Typography>同步中</Typography>
                        </Stack> : <Typography>已與伺服器同步</Typography>
                    }
                </Alert>
                <Button component={'label'} color='secondary'>
                    Upload
                    <input {...register("inputImage")} type="file" multiple hidden accept={"image/*"}/>
                </Button>
                <Button type={'submit'} color='secondary'>Upload Submit</Button>
            </Stack>
            <Grid2 container spacing={1}>
                {menuDishQuery.map((it, index) => (
                    <Grid2 key={index} xs={openNav ? 4 : 3} md={openNav ? 3 : 2.4} lg={openNav ? 2.4 : 2}>
                        <TestManagerCard
                            chineseName={it.menuDishText.chineseName}
                            hint={it.menuDishText.chineseName}
                            id={it.menuDishText.id}
                            image={it.image}
                            handleUpdateText={handleUpdateText}
                        ></TestManagerCard>
                    </Grid2>
                ))}
            </Grid2>
        </Stack>
    )
}
export default TestManager
