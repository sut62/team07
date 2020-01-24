import Vue from 'vue'
import VueRouter from 'vue-router'
import Register from '../components/Register';
import ViewRegister from '../components/ViewRegister';
import TableRegister from '../components/TableRegister';
import StudentRegister from '../components/StudentRegister';
import StudentData from '../components/StudentData';
import ExamSchedule from '../components/ExamSchedule';
import addTeachtable from '../components/addTeachtable';
import SearchTeachtable from '../components/SearchTeachtable';
import Course from '../components/Course';
import StudentShowHistory from '../components/StudentShowHistory';
import Petition from '../components/Petition.vue';

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
        path: 'searchteachtable',
        component: SearchTeachtable
      },
      {
        path: 'lecturer',
        component: () => import('@/views/LecturerList')
      },
      {
        path: 'studentShowHistory',
        component: StudentShowHistory
      },
      {
        path: 'student-course',
        component: () => import('@/views/CourseList')
      },
      {
        path: 'petition',
        component: Petition
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
        path: 'addteachtable',
        component: addTeachtable
      },
      {
        path: 'searchteachtable',
        component: SearchTeachtable
      },
      {
        path: 'examSchedule',
        component: ExamSchedule
      },
      {
        path: 'tablereg',
        component: TableRegister
      }, 
      {
        path: 'studentShowHistory',
        component: StudentShowHistory
      },
      {
        path: 'courses',
        component: () => import('@/components/Course')
      },
      {
        path: 'register-lecturer-list',
        component: () => import('@/views/LecturerList')
      },
      {
        path: 'register-course-list',
        component: () => import('@/views/CourseList')
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
        path: 'tablereg',
        component: TableRegister
      },
      {
        path: 'courses',
        component: () => import('@/components/Course')
      },
      {
        path: 'searchteachtable',
        component: SearchTeachtable
      }, 
      {
        path: 'studentShowHistory',
        component: StudentShowHistory
      },
      // {
      //   path: ':lecturerCode',
      //   component: () => import('@/views/LecturerProfile')
      // },
      {
        path: 'list',
        component: () => import('@/views/LecturerList')
      },
      {
        path: 'courselist',
        component: () => import('@/views/CourseList')
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
