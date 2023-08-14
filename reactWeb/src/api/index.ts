import axios from "axios";
import Score from "../entity/Score";
import PromiseApi from "../model/PromiseApi";
import TokenModel from "../model/TokenModel";
import SysUser from "../entity/SysUser";
import MenuDishModel from "../model/MenuDishModel";

axios.defaults.headers.common["Authorization"] = localStorage.getItem("authorization");
const base = "http://localhost:8082/api";
const menuDishApi = axios.create({baseURL: `${base}/menuDish`});
const securityApi = axios.create({baseURL: `${base}/security`});
const scoreApi = axios.create({baseURL: `${base}/score`});

//MenuDish
export const apiGetDish = (season: number): PromiseApi<MenuDishModel> => menuDishApi.get(`/${season}`);
export const apiPostDish = (menuDish: MenuDishModel): PromiseApi<MenuDishModel> => menuDishApi.post("", menuDish);

//security
export const apiSecurityLogin = (sysUser: SysUser): PromiseApi<TokenModel> => securityApi.post("/login", sysUser);
export const apiSecurityRegister = (sysUser:SysUser ): PromiseApi<TokenModel> => securityApi.post("/register", sysUser);
export const apiSecurityLogout = (): PromiseApi<TokenModel> => securityApi.get("/logout");

//score
export const apiGetScoreById = (userId: number): PromiseApi<Score> => scoreApi.get(`/${userId}`);
export const apiPostScore = (score: Score): PromiseApi<Score> => scoreApi.post("", score);
