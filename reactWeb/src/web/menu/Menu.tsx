import React, {useState} from "react";
import Card from "./component/Card";
import ReturnPage from "./component/ReturnPage";
import {useQuery} from "@tanstack/react-query";
import {Button, Skeleton, Stack} from "@mui/material";
import styled from "@emotion/styled";
import {apiGetDish} from "../../api";
import {MenuDishText} from "../../entity";

export interface CardProps {
    id: number;
    chineseName: string;
    hint: string;
    check: boolean;
    handleResult: (result: boolean, id: number) => void
}


const getDish = async (season: number) => {
    const response = await apiGetDish(season);
    return response.data.textArray;
}

const Menu = () => {
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
    } = useQuery<MenuDishText[], Error>({
        queryKey: ["menuDishQuery"],
        queryFn: () => getDish(season)
    });
    if (isLoading) {
        return (
            <BackGround padding={1} spacing={1.5} useFlexGap>
                {[...Array(100)].map((_, i) => (
                    <Skeleton
                        key={i}
                        sx={{borderRadius: "15px"}}
                        variant="rounded"
                        width={"19vw"}
                        height={"36vh"}
                    />
                ))}
            </BackGround>
        );
    }
    if (isError) {
        return <span>Error{error?.message}</span>;
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
        <BackGround
            justifyContent={"center"}
            spacing={{xs: 0.5, md: 1, lg: 1.5}}
            useFlexGap
        >
            {menuDishQuery.map((card) => (
                <Card
                    key={card.id}
                    check={check}
                    id={card.id}
                    chineseName={card.chineseName}
                    hint={""}
                    handleResult={handleResult}
                />
            ))}
            <ResultDiv>
                <ResultButton onClick={() => resultButtonFunc()}>{resultButtonText}</ResultButton>
                {homePageDisplay ? <ReturnPage score={trueIdArray.length}>返回</ReturnPage> : <></>}
            </ResultDiv>
        </BackGround>
    );
}
const BackGround = styled(Stack)({
    backgroundColor: "#c29e7a",
    flexDirection: "row",
    flexWrap: "wrap",
});

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
