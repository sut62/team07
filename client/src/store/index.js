import Vue from 'vue'
import Vuex from 'vuex'
import axios from '@/plugins/axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    majors: [],
    institutes: [],
    genders: [],
    courses: [],
    prefixs: [],
    specificMajor: null,
    lecturers: [],
    username: null,
    profile: null
  },
  mutations: {
    async setMajors(state) {
      await axios.get('majors').then(response => {
        state.majors = response.data
      })
    },
    async setInstitutes(state) {
      await axios.get('institutes').then(response => {
        state.institutes = response.data
      })
    },
    async setGenders(state) {
      await axios.get('genders').then(response => {
        state.genders = response.data
      })
    },
    async setCourses(state) {
      await axios.get('courses').then(response => {
        state.courses = response.data
      })
    },
    async setPrefixs(state) {
      await axios.get('prefixs').then(response => {
        state.prefixs = response.data
      })
    },
    async setSpecificMajor(state, payload) {
      await axios.get(`majors/institute/${payload}`).then(response => {
        state.specificMajor = response.data
      })
    },
    async setLecturers(state) {
      await axios.get(`lecturers`).then(response => {
        state.lecturers = response.data
      })
    },
    async setUsername(state, payload) {
      state.username = payload
    },
    async setProfile(state, payload) {
      await axios.get(`lecturers/lecturer/code/${payload}`).then(response => {
        state.profile = response.data
      })
    }
  },
  actions: {
    async createLecturer(commit, payload) {
      await axios.post(`lecturers`, payload)
    }
  },
  modules: {
  }
})
