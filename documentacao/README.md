<h1>Documentação e Fluxo de Acessos ao Sistema</h1>

# Sumário
* [Níveis de acesso do usuário](#niveis-de-acesso-do-usuario)
  * [Acesso do Administrador](#acesso-adm)
  * [Acesso do Funcionário](#acesso-fc)
  * [Acesso do Cliente](#acesso-cliente)
    

# Níveis de acesso do usuário

<h3>Acesso do Administrador</h3>

<p>O administrador tem acesso aos dados de toda a aplicação, podendo criar, deletar e editar cadastros de usuários, também cria ordens
de serviço através da solicitação feita pelos clientes, tem controle de funcionários e supervisores, para mandar as requisições de 
serviço.</p>
<img src="https://github.com/Data-Team23/Jaia/blob/doc-flux/documentacao/img/Fluxograma%20administrador.png">

<h3>Acesso do Funcionário</h3>

<p>O funcionário tem dois acessos, o acesso de supervisor, e o acesso de funcionário. O supervisor tem acesso aos funcionários designados
a ele, e cada supervisor tem um departamento em que ele é encarregado, o supervisor também recebe a ordem de serviço que foi aprovada pelo
administrador, e designa os funcionários certos para o serviço solicitado e insere os itens necessários para dar inicio à inspeção.</p>

<p>O funcionário normal tem acesso ao seu departamento e seus serviços que foram incluídos, e também do checklist de serviço. <br>
Nota: o funcionário supervisor também tem acesso ao checklist.</p>

<img src="https://github.com/Data-Team23/Jaia/blob/doc-flux/documentacao/img/Fluxograma%20funcion%C3%A1rios.png">

<h3>Acesso do Cliente</h3>
<p>O cliente tem um acesso limitado, onde ele faz o seu cadastro no site, recebe o e-mail de confirmação de conta, e ao confirmar, ele
pode criar solicitações de ordens de serviço, além de poder atualizar seus dados cadastrais caso mude algum desses dados.</p>
