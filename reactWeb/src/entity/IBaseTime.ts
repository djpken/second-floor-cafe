import IBaseId from "./IBaseId";

export default interface IBaseTime extends IBaseId{
    createAt?: Date;
    updateAt?: Date;
}