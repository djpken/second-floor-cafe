import {MenuDishModel, PromiseApi} from "../model";
import {menuDishApi} from "./Default";

export const apiGetDish = (season: number): PromiseApi<MenuDishModel> => menuDishApi.get(`/${season}`);
export const apiPostDish = (menuDish: MenuDishModel): PromiseApi<MenuDishModel> => menuDishApi.post("", menuDish);
