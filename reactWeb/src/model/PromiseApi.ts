export interface ApiResponse<T> {
    code: number
    data: T
    message: string
}

export default interface PromiseApi<T> extends Promise<ApiResponse<T>> {
}
