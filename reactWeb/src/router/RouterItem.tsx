import React from "react";

interface RouterItem {
    title: string;
    path: string;
    component: React.FC<any>;
}

export default RouterItem