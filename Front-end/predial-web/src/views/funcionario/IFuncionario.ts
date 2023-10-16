import type IDepartament from "../departamento/IDepartament"

export default interface IFuncionario {
    id: number
    nome: string
    cpf: string
    email:string
    telefone: string
    senha: string
    departamento: IDepartament
}