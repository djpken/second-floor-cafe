import {PromiseApi, TokenModel} from "../model";
import {settingApi} from "./Default";
import {Setting, SysUser} from "../entity";

export const apiGetSettingAll = (): PromiseApi<Map<string, string>> => settingApi.get("");
export const apiGetSettingValueByKey = (value: string): PromiseApi<string> => settingApi.get(`/${value}`);
export const apiPostSetting = (setting: Setting): PromiseApi<Map<string, string>> => settingApi.post("", setting);
export const apiPutSetting = (setting: Setting): PromiseApi<Map<string, string>> => settingApi.put("", setting);


export const getSettingValueByKey = async (value: string) => {
    const response = await apiGetSettingValueByKey(value);
    return response.data.data;
}
