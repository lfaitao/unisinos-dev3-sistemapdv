/**
 * Created by lfaitao on 4/19/17.
 */

import {router} from '../../main'
import request from 'request'
import Config from 'electron-config'
// import {ipcRenderer} from 'electron'
const cfg = new Config()

// Endpoints
const LOGIN_URL = cfg.get('apiUrl') + '/login'

export default {
    user: {
        authenticated: false
    },
    login(context, credentials, redirect) {
        request.get({
            url: LOGIN_URL,
            auth: {
                user: credentials.username,
                pass: credentials.password
            }
        }, (err, res, body) => {
            if (err) {
                if (err.code == 'ECONNREFUSED') {
                    context.error = 'Não foi possível se conectar com o servidor ' + cfg.get('apiUrl')
                    context.openAlert()
                    return;
                } else {
                    console.log(err)
                    context.error = 'Ocorreu um erro inesperado, por favor entre em contato com um Administrador do Sistema'
                    context.openAlert()
                    return;
                }
            }

            if (res.statusCode == 200) {
                localStorage.setItem('user', credentials)
                this.user.authenticated = true
                // ipcRenderer.sendSync('login-success', credentials)

                if (redirect) {
                    router.push(redirect)
                }
            } else if (res.statusCode == 400) {
                console.log(body)
                context.error = body.message
                context.openAlert()
            } else if (res.statusCode == 401) {
                context.error = 'Credenciais informadas invalidas.'
                context.openAlert()
            } else if (res.statusCode == 500) {
                console.log(body)
                context.error = 'Ocorreu um erro inesperado, por favor entre em contato com um Administrador do Sistema'
                context.openAlert()
            } else {
                console.log(res.statusCode)
            }
        })
    },

    logout(){
        window.localStorage.setItem('user', null)
        window.localStorage.removeItem('user')
        this.user.authenticated = false
        router.push('/')
    },

    checkAuth(){
        return cfg.get('authenticated')
    }
    //
    // getAuthHeader(){
    //     return {
    //         'Authorization': 'Bearer ' + localStorage.getItem('user').username
    //     }
    // }
}