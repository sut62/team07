import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Vuelidate from 'vuelidate'
import vuetify from './plugins/vuetify'
import axios from './plugins/axios'
import '@babel/polyfill'

Vue.config.productionTip = false

Vue.use(Vuelidate)
Vue.prototype.$http = axios

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
