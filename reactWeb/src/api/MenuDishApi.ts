import {PromiseApi} from "../model";
import {menuDishApi} from "./Default";
import {MenuDishModel} from "../model/MenuDishModel";

export const apiGetDish = (season: number): PromiseApi<MenuDishModel[]> => menuDishApi.get(`/${season}`);
export const apiPostDish = (formData: FormData): PromiseApi<FormData> => menuDishApi.post("", formData);
