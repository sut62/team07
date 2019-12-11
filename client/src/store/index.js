import Vue from 'vue'
import Vuex from 'vuex'
import axios from '@/plugins/axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    majors: null,
    institutes: null,
    genders: null,
    courses: null,
    specificMajor: null
  },
  mutations: {
    setMajors(state) {
      axios.get('majors').then(response => {
        state.majors = response.data
      })
    },
    setInstitutes(state) {
      axios.get('institutes').then(response => {
        state.institutes = response.data
      })
    },
    setGenders(state) {
      axios.get('genders').then(response => {
        state.genders = response.data
      })
    },
    setCourses(state) {
      axios.get('courses').then(response => {
        state.courses = response.data
      })
    },
    setSpecificMajor(state, payload) {
      axios.get(`majors/institute/${payload}`).then(response => {
        state.specificMajor = response.data
      })
    }
  },
  actions: {
  },
  modules: {
  }
})
