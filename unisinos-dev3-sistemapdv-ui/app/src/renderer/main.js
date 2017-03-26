import Vue from 'vue'
import Electron from 'vue-electron'
import Resource from 'vue-resource'
import Router from 'vue-router'

import App from './App'
import routes from './routes'
import auth from './auth/index'

import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.css'
import 'font-awesome/css/font-awesome.min.css'


Vue.use(Electron)
Vue.use(Resource)
Vue.use(Router)
Vue.use(VueMaterial)
Vue.use(require('vue-moment'))

// Check the users auth status when the app starts
auth.checkAuth()

Vue.component('cleave', require('./elements/Cleave'))
Vue.component('sidebar', require('./components/Sidebar'))
Vue.component('navbar', require('./components/Navbar'))

Vue.config.debug = true

export const router = new Router({
    scrollBehavior: () => ({y: 0}),
    routes
})

router.beforeEach((to, from, next) => {
    console.log('Validando')
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // this route requires auth, check if logged in
        // if not, redirect to login page.

        if (!auth.checkAuth()) {
            console.log('Usuario não está logado')
            next({
                path: '/login',
                query: {redirect: to.fullPath}
            })
        } else {
            console.log('Usuário logado')
            next()
        }
    } else {
        console.log('Hmmm....')
        next() // make sure to always call next()!
    }
})


/* eslint-disable no-new */
new Vue({
    router,
    ...App
}).$mount('#app')
