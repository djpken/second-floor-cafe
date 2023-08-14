import React, {RefObject, useEffect} from "react";
import {useNavigate} from "react-router-dom";

export interface InitProps {
    path: string
}

export interface DomTitleProps {
    name: string;
    element: React.ReactNode;
    nodeRef: RefObject<any>
}

export interface RoutesItem extends DomTitleProps, InitProps {
    extended: boolean
    transition: boolean
}


export const Init = ({path}: InitProps) => {
    const navigate = useNavigate();
    useEffect(() => {
        navigate(path);
    }, [navigate, path]);
    return <></>;
};

export const DomTitle = ({name, element, nodeRef}: DomTitleProps) => {
    document.title = name;
    return <div ref={nodeRef}>
        {element}
    </div>;
};
