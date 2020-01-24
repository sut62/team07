import Vue from 'vue'
import VueRouter from 'vue-router'
import Register from '../components/Register';
import ViewRegister from '../components/ViewRegister';
import TableRegister from '../components/TableRegister';
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
        path: 'tablereg',
        component: TableRegister
      },
      {
        path: 'lecturer',
        component: () => import('@/views/LecturerList')
      }
      // {
      //   path: 'courselist',
      //   component: () => import('@/views/CourseList')
      // }

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
      },
      {
        path: 'register-lecturer-list',
        component: () => import('@/views/LecturerList')
      }
      // {
      //   path: 'courselist',
      //   component: () => import('@/views/CourseList')
      // }
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
      // {
      //   path: ':lecturerCode',
      //   component: () => import('@/views/LecturerProfile')
      // },
      {
        path: 'list',
        component: () => import('@/views/LecturerList')
      }
      // {
      //   path: 'courselist',
      //   component: () => import('@/views/CourseList')
      // }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
