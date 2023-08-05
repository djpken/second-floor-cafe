import  { useState } from "react";
import Card from "./menu/Card";
import ReturnPage from "./menu/ReturnPage";
import { useQuery } from "@tanstack/react-query";
import { Button, Skeleton, Stack } from "@mui/material";
import styled from "@emotion/styled";
import { apiGetDish } from "../api";

const MainInMenu = styled(Stack)({
  backgroundColor: "#c29e7a",
  width: "100vw",
  flexDirection: "row",
  flexWrap: "wrap",
  position: "absolute",
});

const ResultButton = styled(Button)({
  border: "2px solid black",
  height: "50px",
  width: "100px",
  position: "relative",
  backgroundColor: "#FFFFFF",
  color: "black",
});
const ResultDiv = styled(Stack)({
  position: "fixed",
  right: "0%",
  top: "83%",
  zIndex: 999,
});
type Dish={
  id:number,
  name:string
}
async function getDish() {
  const response = await apiGetDish();
  return response.data.data;
}

export default function Menu():JSX.Element {
  const [check, setCheck] = useState(false);
  const [homePage, setHomePage] = useState(false);
  const {
    isLoading,
    isError,
    data: dishQuery,
    error,
  } = useQuery<Dish[],Error>({
    queryKey: ["dishQuery"],
    queryFn: getDish,
  });
  if (isLoading) {
    return (
      <MainInMenu padding={1} spacing={1.5} useFlexGap>
        {[...Array(100)].map((_, i) => (
          <Skeleton
            key={i}
            sx={{ borderRadius: "15px" }}
            variant="rounded"
            width={"19vw"}
            height={"36vh"}
          />
        ))}
      </MainInMenu>
    );
  }
  if (isError) {
    return <span>Error{error?.message}</span>;
  }
  const resultButtonFunc = () => {
    setCheck(true);
    setTimeout(() => {
      setCheck(false);
    }, 1000);
    setHomePage(true);
  };
  return (
    <MainInMenu
      justifyContent={"center"}
      spacing={{ xs: 0.5, md: 1, lg: 1.5 }}
      useFlexGap
    >
      {dishQuery?.map((card) => (
        <Card
          key={card.id}
          check={check}
          imgNumber={card.id}
          correctName={card.name}
        />
      ))}
      <ResultDiv>
        <ResultButton onClick={() => resultButtonFunc()}>對答案</ResultButton>
        {homePage ? <></> : <ReturnPage>返回</ReturnPage>}
      </ResultDiv>
    </MainInMenu>
  );
}
