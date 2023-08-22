export interface ApiResponse<T> {
    code: number
    data: T
    message: string
}

export interface PromiseApi<T> extends Promise<ApiResponse<T>> {
}
