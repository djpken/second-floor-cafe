import SysUser from "../entity/SysUser";

export default interface TokenResponse{
    authorization:string
    authority:string
    sysUser:SysUser
}