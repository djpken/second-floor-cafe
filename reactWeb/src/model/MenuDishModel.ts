import SysUser from "../entity/SysUser";
import MenuDishText from "../entity/MenuDishText";
import MenuDishPhoto from "../entity/MenuDishPhoto";

export default interface MenuDishModel {
    textArray: MenuDishText[]
    photoArray: Record<number, any>
}