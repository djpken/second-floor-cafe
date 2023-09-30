import React from "react";
import {DomTitleProps} from "../interface";

export const TitleRouter = ({name, element, nodeRef}: DomTitleProps) => {
    document.title = name;
    return <div ref={nodeRef}>
        {element}
    </div>;
};