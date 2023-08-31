import React, {useState} from "react";
import {useQuery} from "@tanstack/react-query";
import {Box, Button, Stack, styled, useTheme} from "@mui/material";
import {apiGetDish} from "../../api";
import {MenuDishModel} from "../../model/MenuDishModel";
import Grid2 from "@mui/material/Unstable_Grid2"
import Swal from 'sweetalert2'
import ReturnPage from "./component/ReturnPage";
import MenuCard from './component/MenuCard'
import SkeletonComponent from "./component/SkeletonComponent";

export interface CardProps {
    id: number;
    chineseName: string;
    hint: string;
    check: boolean;
    handleResult?: (result: boolean, id: number) => void
    image: string
}


const getDish = async (season: number) => {
    const response = await apiGetDish(season);
    return response.data.data
}

const Menu = () => {
    const theme = useTheme()
    const [season] = useState<number>(2023);
    const [check, setCheck] = useState<boolean>(false);
    const [homePageDisplay, setHomePageDisplay] = useState<boolean>(false);
    const [trueIdArray, setTrueIdArray] = useState<number[]>([]);
    const [resultButtonText, setResultButtonText] = useState<string>("對答案");
    const {
        data: menuDishQuery,
        isLoading,
        isError,
        error,
    } = useQuery<MenuDishModel[], Error>({
        queryKey: ["menuDishQuery"],
        queryFn: () => getDish(season)
    });
    if (isLoading || isError) {
        if (isError) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: error.message,
            })
        }
        return (
            <Box sx={{backgroundColor: theme.palette.secondary.main}}>
                <SkeletonComponent animation={isError ? false : 'pulse'}/>
            </Box>
        );
    }
    const resultButtonFunc = () => {
        setCheck(true);
        setHomePageDisplay(true);
    };
    const handleResult = (result: boolean, id: number) => {
        if (resultButtonText === "對答案") {
            if (result) {
                setTrueIdArray(prevTrueIdArray => {
                    const newTrueIdArray = [...prevTrueIdArray, id];
                    setResultButtonText(newTrueIdArray.length.toString() + "/" + menuDishQuery.length.toString());
                    console.log(newTrueIdArray)
                    return newTrueIdArray;
                });
            }
        }
        setCheck(false);
    };
    return (
        <Box sx={{backgroundColor: theme.palette.secondary.main}}>
            <Grid2 container spacing={1}>
                {menuDishQuery.map((card, i) => (
                    <Grid2 xs={4} md={3} lg={2.4} key={i}>
                        <MenuCard
                            check={check}
                            id={card.menuDishText.id}
                            chineseName={card.menuDishText.chineseName}
                            hint={""}
                            handleResult={handleResult}
                            image={card.image}
                        />
                    </Grid2>
                ))}
                <ResultDiv>
                    <ResultButton onClick={() => resultButtonFunc()}>{resultButtonText}</ResultButton>
                    {homePageDisplay ? <ReturnPage score={trueIdArray.length}>返回</ReturnPage> : <></>}
                </ResultDiv>
            </Grid2>
        </Box>
    );
}

const ResultButton = styled(Button)({
    border: "2px solid black",
    height: "50px",
    width: "100px",
    position: "relative",
    backgroundColor: "#FFFFFF",
    color: "black",
    borderRadius: "10px",
});

const ResultDiv = styled(Stack)({
    position: "fixed",
    right: "0%",
    top: "83%",
    zIndex: 999,
});

export default Menu;
