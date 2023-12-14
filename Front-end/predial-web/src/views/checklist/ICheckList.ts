import type IDepartamento from "../departamento/IDepartament";
import type IListToCheck from "./IListToCheck";

export default interface ICheckList {

    no: number
    id: number
    departamento: IDepartamento
    nome: string
    perguntas: any
    
}

const checklistData: ICheckList[] = [];
