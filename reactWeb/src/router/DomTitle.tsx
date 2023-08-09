import React from "react";
import RouterItem from "./RouterItem";

const DomTitle: React.FC<{ item: RouterItem }> = ({item}) => {
    document.title = item.title;
    return <item.component/>;
};
export default DomTitle
