import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/home/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      meta: {
        hideNavBar: false,
      }
    },
    {
      path: '/solucoes',
      component: () => import('@/views/home/HomeView.vue'),
    },
  ]
})

export default router
