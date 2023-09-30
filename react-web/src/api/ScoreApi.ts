import {PromiseApi} from "../model";
import {scoreApi} from "./Default";
import {Score} from "../entity";

export const apiGetScoreById = (userId: number): PromiseApi<Score> => scoreApi.get(`/${userId}`);
export const apiPostScore = (score: Score): PromiseApi<Score> => scoreApi.post("", score);
