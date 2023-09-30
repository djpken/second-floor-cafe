import {PromiseApi, TokenModel} from "../model";
import {securityApi} from "./Default";
import {SysUser} from "../entity";

export const apiSecurityLogin = (sysUser: SysUser): PromiseApi<TokenModel> => securityApi.post("/login", sysUser);
export const apiSecurityRegister = (sysUser: SysUser): PromiseApi<TokenModel> => securityApi.post("/register", sysUser);
export const apiSecurityLogout = (): PromiseApi<TokenModel> => securityApi.get("/logout");
