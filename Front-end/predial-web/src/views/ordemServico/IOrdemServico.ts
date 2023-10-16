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
            status: string }>;
    };
    id_cli: {
        cnpj: string;
    };
    id_req: {
        data_abertura: string;
        descricao: string;
        status: string;
    };
    id_supervisor: {
        nome: string;
    }
}
