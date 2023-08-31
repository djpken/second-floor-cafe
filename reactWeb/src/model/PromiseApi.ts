import {AxiosPromise} from "axios";

export interface ApiResponse<T> {
    code: number
    data: T
    message: string
}

export interface PromiseApi<T> extends AxiosPromise<ApiResponse<T>> {
}
