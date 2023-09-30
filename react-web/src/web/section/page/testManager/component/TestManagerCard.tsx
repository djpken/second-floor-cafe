import React, {FocusEventHandler} from "react";
import {Box, InputBase, styled} from "@mui/material";
import useInput from "./useInput/useInput";
import {TestManagerCardProps} from "../TestManager";

const TestManagerCard = ({
                             menuDishText,
                             handleUpdateText,
                             image,
                         }: TestManagerCardProps) => {
    const {value, onChange} = useInput<string>(menuDishText.chineseName);
    const handleOnBlur: FocusEventHandler<HTMLInputElement> = (e) => {
        if (value === menuDishText.chineseName) {
            return
        }
        const updater = {...menuDishText, chineseName: value}
        handleUpdateText(updater)
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
        height: "30vh",
        transition: "1s all ease",
        backgroundImage: `url(${process.env.PUBLIC_URL}/background/card_background.jpg)`,
        paddingTop: theme.spacing(0.8),
        paddingLeft: theme.spacing(0.4),
        paddingRight: theme.spacing(0.4),
        paddingBottom: theme.spacing(0.8)
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
