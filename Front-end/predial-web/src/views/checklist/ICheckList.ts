import type IListToCheck from "./IListToCheck";

export default interface ICheckList {

    id: number,
    departamento: string,
    checkList: IListToCheck[]
    
}