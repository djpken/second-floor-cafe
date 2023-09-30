import React from "react";
import {Controller} from "react-hook-form";
import {TextField} from "@mui/material";

const ControllerFullWidthField = ({
                                      control,
                                      name,
                                      error,
                                      ...props
                                  }: FieldProps) => {
    return (
        <Controller
            control={control}
            name={name}
            render={({field}) => (
                <TextField {...props} error={Boolean(error)} fullWidth {...field} />
            )}
        />
    );
};

interface FieldProps {
    control: any;
    name: string;
    error: any | undefined;

    [key: string]: any;
}

export default ControllerFullWidthField
