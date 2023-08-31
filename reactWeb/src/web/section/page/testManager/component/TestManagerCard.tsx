import React, {FocusEventHandler} from "react";
import {Box, InputBase, styled, useTheme} from "@mui/material";
import useInput from "./useInput/useInput";
import {TestManagerCardProps} from "../TestManager";

const TestManagerCard = ({
                             chineseName,
                             id,
                             handleUpdateText,
                             hint,
                             image,
                         }: TestManagerCardProps) => {
    const {value, onChange} = useInput<string>(chineseName);
    const handleOnBlur: FocusEventHandler<HTMLInputElement> = (e) => {
        if (value === chineseName) {
            return
        }
        handleUpdateText({id, chineseName: value, hint})
    }
    return (
        <CardBody>
            <Picture src={`data:image/png;base64,${image}`} alt=""></Picture>
            <TextField onBlur={handleOnBlur} value={value} onChange={onChange}/>
        </CardBody>
    );
}
const CardBody = styled(Box)(({theme}) => (
    {
        border: "2px solid black",
        borderRadius: "15px",
        boxShadow: "0px 0px 10px rgba(10, 10, 0.8)",
        height: "32vh",
        transition: "1s all ease",
        backgroundImage: `url(${process.env.PUBLIC_URL}/background/card_background.jpg)`,
        padding: theme.spacing(1)
    }
));
const Picture = styled('img')({
    padding: 'theme.spacing(1)',
    border: "2px solid black",
    borderRadius: "10px",
    height: "85%",
    width: "100%",
});
const TextField = styled(InputBase)({
    border: "2px black solid",
    borderRadius: "5px",
    cursor: "pointer",
    backfaceVisibility: "hidden",
    width: '100%',
    backgroundColor: "gray",
    height: "1.6em",
});
export default TestManagerCard
