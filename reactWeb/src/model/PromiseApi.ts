export interface ApiResponse<T> {
    code: number
    data: T
    message: string
}

interface PromiseApi<T> extends Promise<ApiResponse<T>> {
}

export default PromiseApi