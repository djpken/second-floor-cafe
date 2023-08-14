import IBaseTime from "./IBaseTime";

export default interface Score extends IBaseTime{
    userId: number
    score: number
}
