import {menuDishApi} from "./Default";
import {MenuDishModel} from "../model/MenuDishModel";
import {PromiseApi} from "../model";
import {MenuDishText} from "../entity";

export const apiGetDish = (season: number): PromiseApi<MenuDishModel[]> => menuDishApi.get(`/${season}`);
export const apiPostDish = (formData: FormData): PromiseApi<FormData> => menuDishApi.post("", formData);
export const apiUpdateDish = (menuDishText: MenuDishText): PromiseApi<MenuDishText> => menuDishApi.put("", menuDishText);
