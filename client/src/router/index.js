import Vue from 'vue'
import VueRouter from 'vue-router'
import Register from '../components/Register';
import StudentRegister from '../components/StudentRegister';


Vue.use(VueRouter)

const routes = [

  {
    path: '/student',
    component: StudentRegister
  },
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/register',
    component: Register
  },
  {
    path: '/login',
    component: () => import('@/views/Login')
  },
  {
    path: '/registration',
    component: () => import('@/layouts/RegistrationLayout'),
    children: [
      {
        path: '',
        redirect: 'register-lecturer'
      },
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
