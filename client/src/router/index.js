import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/registration'
  },
  {
    path: '/registration',
    component: () => import('@/layouts/RegistrationLayout'),
    children: [
      {
        path: 'register-lecturer',
        component: () => import('@/views/LecturerForm')
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
