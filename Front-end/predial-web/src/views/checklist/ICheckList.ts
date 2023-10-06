import type IListToCheck from "./IListToCheck";

export default interface ICheckList {

    id: number,
    checkList: string,
    departamento: string,
    listToCheck: IListToCheck[]
    
}