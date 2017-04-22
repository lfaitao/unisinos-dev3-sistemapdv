import Vue from 'vue'
import Electron from 'vue-electron'
import Resource from 'vue-resource'
import Router from 'vue-router'

import App from './App'
import routes from './routes'
import auth from './views/login/index'

import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.css'
import 'font-awesome/css/font-awesome.min.css'

Vue.use(Electron)
Vue.use(Resource)
Vue.use(Router)
Vue.use(VueMaterial)

Vue.component('navbar', require('./components/Navbar'))

Vue.config.debug = true

export const router = new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes
})

Vue.material.registerTheme('default', {
  primary: 'blue',
  accent: 'red',
  warn: 'orange',
  background: 'white'
})

/* eslint-disable no-new  */
new Vue({
  router,
  ...App
}).$mount('#app')
