import Vue from 'vue'
import Electron from 'vue-electron'
import Resource from 'vue-resource'
import Router from 'vue-router'

import App from './App'
import routes from './routes'

import VueMaterial from 'vue-material'
import VeeValidate from 'vee-validate'
import VeeValidateMessagesBR from "vee-validate/dist/locale/pt_BR"
import 'vue-material/dist/vue-material.css'
import 'font-awesome/css/font-awesome.min.css'

Vue.use(Electron)
Vue.use(Resource)
Vue.use(Router)
Vue.use(VueMaterial)
Vue.use(VeeValidate, {locale: 'pt_BR'});
VeeValidate.Validator.addLocale(VeeValidateMessagesBR);

Vue.component('navbar', require('./components/Navbar'))

Vue.config.debug = true

export const router = new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes
})
// .beforeEach((to, from, next) => {
//   //console.log(new Date() + ": indo de " + from + "para" + to);
// });

Vue.material.registerTheme('default', {
  primary: 'blue',
  accent: 'light-green',
  warn: 'red',
  background: 'white'
})

/* eslint-disable no-new  */
new Vue({
  router,
  ...App
}).$mount('#app')
