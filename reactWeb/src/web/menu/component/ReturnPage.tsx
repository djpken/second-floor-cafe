import {useNavigate} from "react-router-dom";
import React, {useEffect} from "react";
import {Button} from "@mui/material";
import {useMutation} from "@tanstack/react-query";
import {Score, SysUser} from "../../../entity";
import {apiPostScore} from "../../../api";

interface ReturnPageProps {
    children: React.ReactNode
    score: number
}

const postScore = async (score: Score) => {
    const response = await apiPostScore(score)
    return response.data
}
export default function ReturnPage({children, score}: ReturnPageProps) {
    const navigate = useNavigate();
    const postScoreMutation = useMutation({
        mutationFn: postScore
    })
    useEffect(() => {
        const user = JSON.parse(localStorage.getItem("user") ?? "") as SysUser
        postScoreMutation.mutate({id: 0, userId: user.id, score: score})
        console.log("request")
    }, []);
    return (
        <Button onClick={() => navigate("/main/section")} sx={style}>
            {children}
        </Button>
    );
}
const style = {
    border: "2px solid black",
    height: "50px",
    width: "100px",
    position: "relative",
    backgroundColor: "#FFFFFF",
    color: "black",
    borderRadius: "10px"
};
