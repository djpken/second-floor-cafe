import axios from "axios";

const base = "http://localhost:8082/api";

axios.defaults.headers.common["Authorization"] = localStorage.getItem("authorization");


export const menuDishApi = axios.create({baseURL: `${base}/menuDish`});
export const securityApi = axios.create({baseURL: `${base}/security`});
export const scoreApi = axios.create({baseURL: `${base}/score`});
export const settingApi = axios.create({baseURL: `${base}/setting`});

