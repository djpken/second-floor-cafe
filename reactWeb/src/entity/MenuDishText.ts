import {IBaseId} from "./base";

export  interface MenuDishText extends IBaseId{
    chineseName: string;
    englishName?: string;
    price?: number;
    season?: number;
    visible?: string;
}