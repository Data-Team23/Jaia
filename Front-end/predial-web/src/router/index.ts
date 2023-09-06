import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '@/views/LoginView.vue'
import ListDepartmentViewVue from '@/views/department/ListDepartmentView.vue'
import AddDepartmentViewVue from '@/views/department/AddDepartmentView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: LoginView
    },
    {
      path: '/home',
      name: 'home',
      component: HomeView
    },
    {
      path: '/departamentos',
      name: 'departamentos',
      component: ListDepartmentViewVue
    },
    {
      path: '/adicionar-departamento',
      name: 'adicionar-departamento',
      component: AddDepartmentViewVue
    }
  ]
})

export default router
