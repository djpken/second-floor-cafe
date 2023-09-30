import React, {RefObject, useEffect} from "react";
import {useNavigate} from "react-router-dom";

export interface InitProps {
    path: string
}

export interface DomTitleProps {
    name: string;
    element: React.ReactNode;
    nodeRef?: RefObject<any>
}

export interface RoutesItem extends DomTitleProps, InitProps {
    extended: boolean
    transition: boolean
}




