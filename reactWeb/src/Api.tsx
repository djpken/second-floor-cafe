import axios, {AxiosResponse} from "axios";

axios.defaults.headers.common["Authorization"] = localStorage.getItem("token");
const base = "http://localhost:8082/api";
const dishApi = axios.create({baseURL: `${base}/dish`});
const securityApi = axios.create({baseURL: `${base}/security`});
//dish
export const apiGetDish = (season: number): Promise<AxiosResponse> => dishApi.get(`/${season}`);

//security
export const apiSecurityLogin = (data: any): Promise<AxiosResponse> => securityApi.post("/login", data);
export const apiSecurityRegister = (data: any): Promise<AxiosResponse> => securityApi.post("/register", data);
export const apiSecurityLogout = (): Promise<AxiosResponse> => securityApi.get("/logout");
