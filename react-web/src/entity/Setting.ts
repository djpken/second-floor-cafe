import {IBaseId} from "./base";

export interface Setting extends IBaseId {
    key: string
    value: string
}
