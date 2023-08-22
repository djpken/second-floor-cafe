import {IBaseTime} from "./base";

export interface SysUser extends IBaseTime {
    username: string
    password?: string
    chineseName?: string
    englishName?: string
    email?: string
}
