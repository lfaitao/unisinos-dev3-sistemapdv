/**
 * Created by lfaitao on 4/19/17.
 */

import {router} from '../../main'
import request from 'request'
import Config from 'electron-config'

const cfg = new Config()
const LOGIN_URL = cfg.get('apiUrl') + '/autenticar'

export default {
    user: {
        authenticated: false
    },
    login(context, credentials, redirect) {
        request.post({
            url: LOGIN_URL,
            form: {
                login: credentials.username,
                senha: credentials.password
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
                localStorage.setItem('teste',res.body)

                if (res.body != null && res.body !="") {
                    this.user.authenticated = true
                    if (redirect) {
                        router.push(redirect)
                    }
                } else {
                    context.error = 'Credenciais informadas invalidas.'
                    context.openAlert()
                }
           
        }
        })
    },

    logout(){
        this.user.authenticated = false
        window.localStorage.setItem('user', null)
        window.localStorage.removeItem('user')
        this.user.authenticated = false
        router.push('/')
    },

    checkAuth(){
        return cfg.get('authenticated')
    }
}