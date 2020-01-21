import Vue from 'vue'
import VueRouter from 'vue-router'
import Register from '../components/Register';
import ViewRegister from '../components/ViewRegister';
import StudentRegister from '../components/StudentRegister';
import StudentData from '../components/StudentData';
import ExamSchedule from '../components/ExamSchedule';
import addTeachtable from '../components/addTeachtable';
import Course from '../components/Course';

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/student',
    component: StudentRegister
  },
  {
    path: '/studentdata',
    component: StudentData
  },
  {
    path: '/courses',
    component: Course
  },
  {
    path: '/login',
    component: () => import('@/views/Login')
  },
  {
    path: '/RegisterStudent',
    component: () => import('@/layouts/StudentLayout'),
    children: [
      {
        path: '',
        redirect: 'register'
      },
      {
        path: 'register',
        component: Register
      },
      {
        path: 'viewreg',
        component: ViewRegister
      },
      {
        path: 'lecturer-profile',
        component: () => import('@/views/LecturerList')
      }
    ]
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
      },
      {
        path: 'register-lecturer-list',
        component: () => import('@/views/LecturerList')
      },
      {
        path: 'addteachtable',
        component: addTeachtable
      },
      {
        path: 'examSchedule',
        component: ExamSchedule
      },
      {
        path: 'courses',
        component: () => import('@/components/Course')
      }
    ]
  },
  {
    path: '/teachers',
    component: () => import('@/layouts/TeacherLayout'),
    children: [
      {
        path: '',
        redirect: 'courses'
      },
      {
        path: 'courses',
        component: () => import('@/components/Course')
      },
      {
        path: ':lecturerCode',
        component: () => import('@/views/LecturerProfile')
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
