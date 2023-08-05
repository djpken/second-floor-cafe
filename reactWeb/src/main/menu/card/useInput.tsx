import { ChangeEvent, useState } from "react";

export function useInput<T>(initialValue: T) {
  const [value, setValue] = useState<T>(initialValue);
  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value as T);
  };
  const resetValue = () => setValue(initialValue);
  return [{ value, onChange: handleChange }, resetValue];
}
