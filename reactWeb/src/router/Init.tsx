import React from "react";
import {useNavigate} from "react-router-dom";

interface InitProps {
    path: string
}

const Init: React.FC<InitProps> = ({path}) => {
    const navigate = useNavigate();
    React.useEffect(() => {
        navigate(path);
    }, [navigate, path]);
    return <></>;
};
export default Init
