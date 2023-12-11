import type IDepartamento from "../departamento/IDepartament";

export default interface IOrdemServico {
    id: number;
    nome_ordem: string;
    tipo_inspecao: string;
    status_ordem: string;
    id_check: {
        nome: string;
        perguntas: Array<{
            id: number;
            pergunta: string;
            comentario: string;
            status: string;
        }>;
        departamento: {
            idDepart: number;
            nome: string;
        };
    };
    id_req: {
        nome: string;
        data_abertura: string;
        descricao: string;
        status: string;
        fkCliente: {
            id: number;
            cnpj: string;
            nome: string;
            email: string;
        };
    };
    fkSupervisor: {
        nome: string;
        cpf: string; 
        email: string; 
        departamento: {
            idDepart: number;
            nome: string;
        };
        supervisor: null | any; 
    };
}
