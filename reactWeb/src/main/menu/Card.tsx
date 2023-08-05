import React, { useState, useEffect } from "react";
import { useInput } from "./card/useInput";
import { Box, InputBase, Stack } from "@mui/material";
import styled from "@emotion/styled";

type CardProps = {
  hint?: string;
  imgNumber: number;
  correctName: string;
  check?: boolean;
};
export default React.memo(function Card({
  hint = "",
  imgNumber,
  correctName,
  check = false,
}: CardProps): JSX.Element {
  const [result, setResult] = useState(false);
  const [errorVisible, setErrorVisible] = useState(false);
  const [valueProps] = useInput("");
  useEffect(() => {
    if (check === true) {
      const isCorrect =
        correctName.trim().toLowerCase() ===
        (valueProps as { value: string }).value.trim().toLowerCase();
      if (isCorrect) {
        setResult(true);
      } else {
        setErrorVisible(true);
      }
    }
  }, [check]);
  return (
    <CardBody
      sx={{
        transform: result ? "rotateY(180deg)" : "rotateY(0deg)",
      }}
    >
      <CardFront>
        <Stack
          position={"relative"}
          height={"0.8"}
          pt={"1vh"}
          px={"1vw"}
          alignItems={"center"}
        >
          <Picture src={"/img/" + imgNumber + ".jpg"} alt=""></Picture>
          <ErrorAnswer display={errorVisible ? "" : "none"}>
            {correctName}
          </ErrorAnswer>
        </Stack>
        <Stack
          spacing={1}
          px={"1vw"}
          pt={"1vh"}
          direction={"row"}
          alignItems={"center"}
        >
          <TextField {...valueProps} placeholder={hint} />
          <BCycle />
          <SCycle />
        </Stack>
      </CardFront>
      <CardFlip />
    </CardBody>
  );
});
const CardBody = styled(Stack)({
  borderRadius: "15px",
  boxShadow: "0px 0px 10px rgba(10, 10, 0.8)",
  height: "36vh",
  width: "19vw",
  transformStyle: "preserve-3d",
  transition: "1s all ease",
});
const Picture = styled("img")({
  border: "2px solid black",
  borderRadius: "10px",
  height: "100%",
  width: "100%",
  backfaceVisibility: "hidden",
});

const BCycle = styled(Box)({
  backgroundColor: "black",
  width: "4vh",
  height: "4vh",
  borderRadius: "50%",
});

const SCycle = styled(Box)({
  backgroundColor: "gray",
  width: "2vh",
  height: "2vh",
  borderRadius: "50%",
});

const TextField = styled(InputBase)({
  border: "2px black solid",
  borderRadius: "5px",
  cursor: "pointer",
  backfaceVisibility: "hidden",
  width: "70%",
  backgroundColor: "gray",
  height: "1.6em",
});

const ErrorAnswer = styled(Box)({
  position: "absolute",
  bottom: "0",
  left: "1vw",
  border: "2px black solid",
  borderRadius: "5px",
  backfaceVisibility: "hidden",
  backgroundColor: "white",
  color: "#f95738",
  fontWeight: "bold",
  whiteSpace: "nowrap",
});

const CardFront = styled(Box)({
  border: "2px solid black",
  borderRadius: "15px",
  height: "100%",
  width: "100%",
  backgroundImage: `url(${process.env.PUBLIC_URL}/background/card_background.jpg)`,
  backfaceVisibility: "hidden",
});

const CardFlip = styled(Box)({
  border: "2px solid black",
  borderRadius: "15px",
  height: "100%",
  width: "100%",
  position: "absolute",
  backgroundImage: `url(${process.env.PUBLIC_URL}/background/Menu_flip.png)`,
  backfaceVisibility: "hidden",
  transform: "rotateY(180deg)",
  backgroundPosition: "center",
  backgroundSize: "cover",
});
