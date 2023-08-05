import axios, { AxiosResponse } from "axios";

axios.defaults.headers.common["Authorization"] = localStorage.getItem("token");
const base = "http://localhost:8080";
const dishApi = axios.create({ baseURL: `${base}/api/dish` });
const securityApi = axios.create({ baseURL: `${base}/security` });
const sysUserApi = axios.create({ baseURL: `${base}/api/sysuser` });
//dish
export const apiGetDish = (): Promise<AxiosResponse> => dishApi.get("");



//sysUser
export const apiGetSysUser = (
  page: number,
  perPage: number
): Promise<AxiosResponse> =>
  sysUserApi.get("", {
    params: {
      page,
      perPage,
    },
  });
export const apiGetSysUserOne = (data: any): Promise<AxiosResponse> =>
  sysUserApi.get(`/${data}`);
export const apiPostSysUser = (data: any): Promise<AxiosResponse> =>
  sysUserApi.post("", data);
export const apiDeleteSysUser = (data: any): Promise<AxiosResponse> =>
  sysUserApi.delete("");
export const apiPutSysUser = (data: any): Promise<AxiosResponse> =>
  sysUserApi.put("");


//security
export const apiSecurityLogin = (data: any): Promise<AxiosResponse> =>
  securityApi.post("/login", data);
export const apiSecurityRegister = (data: any): Promise<AxiosResponse> =>
  securityApi.post("/register", data);
export const apiSecurityLogout = (): Promise<AxiosResponse> =>
  securityApi.get("/logout");
