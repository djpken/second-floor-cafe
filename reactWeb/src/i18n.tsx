import zhTw from "./i18n/locale/zh-tw/translation.json";
import enUs from "./i18n/locale/en-us/translation.json";
import {initReactI18next} from "react-i18next";
import i18n from "i18next";

const resources = {
    enUs: {
        translation: enUs
    },
    zhTw: {
        translation: zhTw
    }
}
i18n.use(initReactI18next).init({
    resources,
    fallBackLng: "zhTw",
    lng: "zhTw",
    interpolation: {
        escapeValue: false
    }
}).then(r => {
})