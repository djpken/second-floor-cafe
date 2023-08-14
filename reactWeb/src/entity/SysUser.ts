import IBaseTime from "./IBaseTime";

export default interface SysUser extends IBaseTime {
    username: string
    password?: string
    chineseName?: string
    englishName?: string
    email?: string
}
