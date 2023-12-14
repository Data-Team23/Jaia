import ListClientViewVue from '@/views/cliente/ListClientView.vue'
import AddDepartmentView from '@/views/departamento/AddDepartmentView.vue'
import ListDepartmentView from '@/views/departamento/ListDepartmentView.vue'
import UpdateDepartmentView from '@/views/departamento/UpdateDepartmentView.vue'
import AddFuncionarioViewVue from '@/views/funcionario/AddFuncionarioView.vue'
import ListFuncionarioViewVue from '@/views/funcionario/ListFuncionarioView.vue'
import UpdateFuncionarioViewVue from '@/views/funcionario/UpdateFuncionarioView.vue'
import AddFuncionarioViewVue from '@/views/funcionario/AddFuncionarioView.vue'
import ListRequisicoesView from '@/views/requisicoes/ListRequisicoesView.vue'
import AddRequisicoesView from '@/views/requisicoes/AddRequisicoesView.vue'
import UpdateRequisicoesView from '@/views/requisicoes/UpdateRequisicoesView.vue'
import ListChecklistViewVue from '@/views/checklist/ListChecklistView.vue'
import ListOrdemServicoViewVue from '@/views/ordemServico/ListOrdemServicoView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: LoginView,
      meta: {
        hideNavBar: true,
      }
    },
    {
      path: '/departamentos',
      name: 'departamentos',
      component: ListDepartmentView
    },
    {
      path: '/adicionar-departamento',
      name: 'adicionar-departamento',
      component: AddDepartmentView
    },
    {
      path: '/editar-departamento',
      name: 'editar-departamento',
      component: UpdateDepartmentView
    },
    {
      path: '/clientes',
      name: 'clientes',
      component: ListClientViewVue
    },
    {
      path: '/funcionarios',
      name: 'funcionario',
      component: ListFuncionarioViewVue
    },
    {
      path: '/editar-funcionario',
      name: 'editar-funcionario',
      component: UpdateFuncionarioViewVue
    },
    {
      path: '/adicionar-funcionario',
      name: 'adicionar-funcionario',
      component: AddFuncionarioViewVue
    },
    {
      path: '/requisicoes',
      name: 'requisicoes',
      component: ListRequisicoesView
    },
    {
      path: '/adicionar-requisicao',
      name: 'adicionar-requisicao',
      component: AddRequisicoesView
    },
    {
      path: '/editar-requisicao',
      name: 'editar-requisicao',
      component: UpdateRequisicoesView
    },
    {
      path: '/check-list',
      name: 'check-list',
      component: ListChecklistViewVue
    },
    {
      path: '/ordens-de-servico-Admin',
      name:'ordem-de-servico-Admin',
      component: ListOrdemServicoView
    },
    {
      path: '/ordens-de-servico',
      name:'ordem-de-servico',
      component: ListOrdemServicoViewFuncionario
    },
    {
      path: '/requisicoesAdmin',
      name: 'requisicoesAdmin',
      component: ListRequisicoesAdminView
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: DashboardViewVue
    }
  ]
})

export default router
