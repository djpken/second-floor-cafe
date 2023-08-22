import {useNavigate} from "react-router-dom";
import React, {useEffect} from "react";
import {InitProps} from "../interface";

export const Init = ({path}: InitProps) => {
    const navigate = useNavigate();
    useEffect(() => {
        navigate(path);
    }, [navigate, path]);
    return <></>;
};