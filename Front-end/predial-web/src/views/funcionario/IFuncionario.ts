import type IDepartament from "../departamento/IDepartament"

export default interface IFuncionario {
    
    no: number
    id: number
    nome: string
    cpf: string
    email:string
    telefone: string
    senha: string
    departamento: IDepartament
}

const funcionarioData: IFuncionario[] = [];
