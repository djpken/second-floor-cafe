import React, {useEffect, useState} from "react";
import useInput from "./useInput/useInput";
import {Box, InputBase, Stack} from "@mui/material";
import styled from "@emotion/styled";
import {CardProps} from "../Menu";


const Card = ({
                  chineseName,
                  id,
                  check,
                  hint,
                  handleResult
              }: CardProps) => {
    const [result, setResult] = useState(false);
    const [errorVisible, setErrorVisible] = useState(false);
    const {value, onChange} = useInput<string>("");
    useEffect(() => {
        if (check) {
            const isCorrect =
                chineseName?.trim().toLowerCase() ===
                value.trim().toLowerCase();
            if (isCorrect) {
                setResult(true);
            } else {
                setErrorVisible(true);
                setResult(false);
            }
        }
    }, [check, chineseName, handleResult, id, value]);
    useEffect(() => {
        handleResult(result, id );
    }, [handleResult, id, result]);
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
                    <Picture src={"/img/" + id + ".jpg"} alt=""></Picture>
                    <ErrorAnswer display={errorVisible ? "" : "none"}>
                        {chineseName}
                    </ErrorAnswer>
                </Stack>
                <Stack
                    spacing={1}
                    px={"1vw"}
                    pt={"1vh"}
                    direction={"row"}
                    alignItems={"center"}
                >
                    <TextField value={value} onChange={onChange} placeholder={hint}/>
                    <BCycle/>
                    <SCycle/>
                </Stack>
            </CardFront>
            <CardFlip/>
        </CardBody>
    );
}

const CardBody = styled(Stack)({
    borderRadius: "15px",
    boxShadow: "0px 0px 10px rgba(10, 10, 0.8)",
    height: "36vh",
    flexBasis: "18.5vw",
    transformStyle: "preserve-3d",
    transition: "1s all ease",
});
const Picture = styled.img({
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
export default React.memo(Card);
