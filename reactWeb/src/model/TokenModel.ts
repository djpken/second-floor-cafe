import SysUser from "../entity/SysUser";

export default interface TokenModel {
    authorization: string
    authority: string
    sysUser: SysUser
}