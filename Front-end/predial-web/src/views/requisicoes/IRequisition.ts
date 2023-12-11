export default interface IRequisition {
    id: number;
    nome: string;
    inspecao: string;
    descricao: string;
    status: string;
    data_abertura: string;
    status_os: string;
    fkCliente: {
      id: number;
      cnpj: string;
      nome: string;
      senha: string;
      email: string;
      endereco: string;
      telefone: string;
    };
  }
  