import type IFuncionario from "../funcionario/IFuncionario"

export default interface IDepartamento {
    nome: string
    codDepart: number
    funcionarios: IFuncionario[]
}