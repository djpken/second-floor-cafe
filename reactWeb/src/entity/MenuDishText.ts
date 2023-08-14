import IBaseId from "./IBaseId";

export default interface MenuDishText extends IBaseId{
    chineseName: string;
    englishName?: string;
    price?: number;
    season?: number;
    visible?: string;
}