import {
    Alert,
    Button,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    Stack,
    Typography,
    useTheme
} from "@mui/material";
import {useMutation, UseMutationOptions, useQueries, useQuery} from "@tanstack/react-query";
import {apiGetDish, apiUpdateDish, getSettingValueByKey} from "../../../../api";
import {MenuDishModel} from "../../../../model/MenuDishModel";
import React, {useState} from "react";
import TestManagerCard from "./component/TestManagerCard";
import Grid2 from "@mui/material/Unstable_Grid2";
import {MenuDishText} from "../../../../entity";
import DialogSlide from "./component/DialogSlide";
import {SettingKey} from "../../../../constant";

const getDish = async (season: number) => {
    const response = await apiGetDish(season)
    return response.data.data
}

const updateDish: UseMutationOptions<any, any, any, any> = {
    mutationFn: async (menuDishText: MenuDishText) => {
        const response = await apiUpdateDish(menuDishText)
        return response.data.data
    }
}

interface TestManageProps {
    openNav: boolean
}

export interface TestManagerCardProps {
    menuDishText: MenuDishText
    handleUpdateText: (props: MenuDishText) => void
    image: string
}

const TestManager = ({openNav}: TestManageProps) => {
    const theme = useTheme()
    const [season, setSeason] = useState<number>(0)
    const [isUpdateLoading, setIsUpdateLoading] = useState<boolean>(false)
    const mutationUpdateDish = useMutation(updateDish);
    const menuDishQuery
        = useQuery<MenuDishModel[], Error>({
        queryKey: ["menuDishQuery", season],
        queryFn: () => getDish(season as number)
    });
    const searchQuery = useQueries({
        queries: [{
            queryFn: () => getSettingValueByKey(SettingKey.SEASON_OPTIONAL),
            queryKey: [SettingKey.SEASON_OPTIONAL]
        }, {
            queryFn: () => getSettingValueByKey(SettingKey.INIT_SEASON)
            , queryKey: [SettingKey.INIT_SEASON]
        }],
    })
    const handleUpdateText = (menuDishText: MenuDishText) => {
        setIsUpdateLoading(true)
        const finder = menuDishQuery.data?.find((it) => it.menuDishText.id === menuDishText.id)?.menuDishText
        const updater = {
            ...finder,
            id: menuDishText.id,
            chineseName: menuDishText.chineseName,
            hint: menuDishText.hint,
            season: menuDishText.season
        }

        mutationUpdateDish.mutate(updater)
    }
    const handleSeasonChange = (event: any) => {
        setSeason(event.target.value)
    }
    if (menuDishQuery.isLoading || searchQuery[0].isLoading || searchQuery[1].isLoading) {
        return (
            <Stack paddingLeft={0.5} spacing={2} direction={'row'}>
                <Alert variant={'standard'} severity={'info'} sx={{width: "15rem"}}>
                    <Stack direction={'row'} spacing={2}>
                        <Typography>載入中...</Typography>
                    </Stack>
                </Alert>
                <Button color='secondary'>
                    上傳圖片
                </Button>
                <FormControl>
                    <InputLabel id="season">年份</InputLabel>
                    <Select
                        id="season"
                        value={season}
                        label="年份"
                        onChange={handleSeasonChange}
                        sx={{backgroundColor: theme.palette.secondary.main}}
                    > </Select>
                </FormControl>

            </Stack>
        )
    }
    if (menuDishQuery.isError || searchQuery[0].isError || searchQuery[1].isError) {
        return (
            <Stack paddingLeft={0.5} spacing={2} direction={'row'}>
                <Alert variant={'standard'} severity={'error'} sx={{width: "15rem"}}>
                    <Stack direction={'row'} spacing={2}>
                        <Typography>{menuDishQuery.error?.message}</Typography>
                    </Stack>
                </Alert>
                <Button color='secondary'>
                    上傳圖片
                </Button>

                <FormControl>
                    <InputLabel id="season">年份</InputLabel>
                    <Select
                        id="season"
                        value={season}
                        label="年份"
                        onChange={handleSeasonChange}
                        sx={{backgroundColor: theme.palette.secondary.main}}
                    >
                    </Select>
                </FormControl>
            </Stack>
        );
    }

    return (
        <Stack spacing={0.5}>
            <Stack direction={'row'} spacing={2} paddingLeft={0.5}>
                <Alert variant={'standard'} severity={isUpdateLoading ? 'info' : 'success'} sx={{width: "15rem"}}>
                    {
                        isUpdateLoading ? <Stack spacing={2} direction={'row'}>
                            <Typography>同步中...</Typography>
                        </Stack> : <Typography>已與伺服器同步</Typography>
                    }
                </Alert>
                <DialogSlide searchQuery={searchQuery[0].data || ""} defaultValue={searchQuery[1].data || ""}/>
                <FormControl>
                    <InputLabel id="season">年份</InputLabel>
                    <Select
                        id="season"
                        value={season}
                        defaultValue={parseInt(searchQuery[1].data || "")}
                        label="年份"
                        onChange={handleSeasonChange}
                        sx={{backgroundColor: theme.palette.secondary.main}}
                    >
                        {searchQuery[0].data?.split(",").map((it, index) => {
                                if (index === 0) {
                                    return <MenuItem value={it}>All</MenuItem>
                                } else {
                                    return <MenuItem value={parseInt(it)}>{it}</MenuItem>
                                }
                            }
                        )}
                    </Select>
                </FormControl>
            </Stack>
            <Grid2 container spacing={0.8}>
                {menuDishQuery.data.map((it, index) => (
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
