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
        };
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
        fk_cliente_id: {
            id: number;
            cnpj: string;
            nome: string;
            email: string;
        };
    };
    id_supervisor: {
        nome: string;
        cpf: string; // Adicionei a propriedade "cpf" que estava ausente no JSON
        email: string; // Adicionei a propriedade "email" que estava ausente no JSON
        departamento: {
            idDepart: number;
            nome: string;
        };
        supervisor: null | any; // Supus que "supervisor" poderia ser de qualquer tipo
    };
}
