import {Alert, Box, Button, Stack, Typography} from "@mui/material";
import {useMutation, useQuery} from "@tanstack/react-query";
import {apiGetDish, apiUpdateDish} from "../../../../api";
import {MenuDishModel} from "../../../../model/MenuDishModel";
import React, {useState} from "react";
import TestManagerCard from "./component/TestManagerCard";
import Grid2 from "@mui/material/Unstable_Grid2";
import {MenuDishText} from "../../../../entity";
import DialogSlide from "./component/DialogSlide";

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

export interface TestManagerCardProps {
    menuDishText: MenuDishText
    handleUpdateText: (props: MenuDishText) => void
    image: string
}


const season: number = 2023
const TestManager = ({openNav}: TestManageProps) => {
    const [isUpdateLoading, setIsUpdateLoading] = useState<boolean>(false)
    const mutationUpdateDish = useMutation({
        mutationFn: (menuDishText: MenuDishText) => updateDish(menuDishText), onSuccess: () => {
            setIsUpdateLoading(false)
        }
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
    const handleUpdateText = (menuDishText: MenuDishText) => {
        setIsUpdateLoading(true)
        const finder = menuDishQuery?.find((it) => it.menuDishText.id === menuDishText.id)?.menuDishText
        const updater = {
            ...finder,
            id: menuDishText.id,
            chineseName: menuDishText.chineseName,
            hint: menuDishText.hint,
            season: menuDishText.season
        }

        mutationUpdateDish.mutate(updater)
    }
    if (isLoading) {
        return (
            <Box paddingX={0.5}>
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
            <Box paddingX={0.5}>
                <Alert variant={'standard'} severity={'error'} sx={{width: "15rem"}}>
                    <Stack direction={'row'} spacing={2}>
                        <Typography>{error?.message}</Typography>
                    </Stack>
                </Alert>
            </Box>
        );
    }
    return (
        <Stack spacing={0.5} >
            <Stack direction={'row'} spacing={2} paddingLeft={0.5}>
                <Alert variant={'standard'} severity={isUpdateLoading ? 'info' : 'success'} sx={{width: "15rem"}}>
                    {
                        isUpdateLoading ? <Stack spacing={2} direction={'row'}>
                            <Typography>同步中...</Typography>
                        </Stack> : <Typography>已與伺服器同步</Typography>
                    }
                </Alert>
                <DialogSlide/>
                <Button type={'submit'} color='secondary'>Upload Submit</Button>
            </Stack>
            <Grid2 container spacing={0.8}>
                {menuDishQuery.map((it, index) => (
                    <Grid2 key={index} xs={openNav ? 4 : 3} md={openNav ? 3 : 2.4} lg={openNav ? 2.4 : 2}>
                        <TestManagerCard
                            menuDishText={it.menuDishText}
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
