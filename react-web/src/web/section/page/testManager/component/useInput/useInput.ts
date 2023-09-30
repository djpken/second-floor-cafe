import {ChangeEvent, useState} from "react";

interface useInputProps<T> {
    value: T;
    onChange: (e: ChangeEvent<HTMLInputElement>) => void;
    resetValue: () => void;
}

const useInput = <T>(initialValue: T): useInputProps<T> => {
    const [value, setValue] = useState<T>(initialValue);

    const onChange = (e: ChangeEvent<HTMLInputElement>) => {
        setValue(e.target.value as T);
    };

    const resetValue = () => setValue(initialValue);

    return {value, onChange, resetValue};
};

export default useInput
