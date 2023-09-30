import {menuDishApi} from "./Default";
import {MenuDishModel} from "../model/MenuDishModel";
import {PromiseApi} from "../model";
import {MenuDishText} from "../entity";

export const apiGetDish = (season: number): PromiseApi<MenuDishModel[]> => menuDishApi.get(`/${season}`);
export const apiPostMenuDishPhoto = (formData: FormData): PromiseApi<FormData> => menuDishApi.post("menuDishPhoto", formData);
export const apiPostMenuDishText = (menuDishTextArray:MenuDishText[]): PromiseApi<MenuDishText[]> => menuDishApi.post("menuDishText", menuDishTextArray);
export const apiUpdateDish = (menuDishText: MenuDishText): PromiseApi<MenuDishText> => menuDishApi.put("", menuDishText);
