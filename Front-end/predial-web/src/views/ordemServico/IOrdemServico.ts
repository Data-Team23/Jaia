import type IDepartamento from "../departamento/IDepartament";

export default interface IOrdemServico {
    id: number;
    nome_ordem: string;
    tipo_inspecao: string;
    status_ordem: string;
    id_check: {
        nome: string;
        departamentos: IDepartamento[]
        perguntas: Array<{ 
            id: number; 
            pergunta: string; 
            comentario: string; 
            status: string }>;
    };
    id_req: {
        data_abertura: string;
        descricao: string;
        status: string;
        fk_cliente_id: {
            id: number,
            cnpj: string,
        }
    };
    id_supervisor: {
        nome: string;
    }
}
