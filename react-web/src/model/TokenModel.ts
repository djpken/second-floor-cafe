import {SysUser} from "../entity";

export interface TokenModel {
    authorization: string
    authority: string
    sysUser: SysUser
}