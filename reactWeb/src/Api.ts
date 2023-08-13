import axios from "axios";
import Score from "./entity/Score";
import PromiseApi from "./model/PromiseApi";
import TokenResponse from "./model/TokenResponse";
import MenuDish from "./entity/Dish";
import SysUser from "./entity/SysUser";

axios.defaults.headers.common["Authorization"] = localStorage.getItem("authorization");
const base = "http://localhost:8082/api";
const dishApi = axios.create({baseURL: `${base}/dish`});
const securityApi = axios.create({baseURL: `${base}/security`});
const scoreApi = axios.create({baseURL: `${base}/score`});

//dish
export const apiGetDish = ({season}: MenuDish): PromiseApi<MenuDish[]> => dishApi.get(`/${season}`);
//security
export const apiSecurityLogin = (sysUser: SysUser): PromiseApi<TokenResponse> => securityApi.post("/login", sysUser);
export const apiSecurityRegister = (sysUser: SysUser): PromiseApi<TokenResponse> => securityApi.post("/register", sysUser);
export const apiSecurityLogout = (): PromiseApi<TokenResponse> => securityApi.get("/logout");

//score
export const apiGetScoreById = ({userId}: Score): PromiseApi<Score> => scoreApi.get(`/${userId}`);
export const apiPostScore = (score: Score): PromiseApi<Score> => scoreApi.post("", score);