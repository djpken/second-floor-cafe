import {IBaseId} from "./IBaseId";

export interface IBaseTime extends IBaseId {
    createAt?: Date;
    updateAt?: Date;
}