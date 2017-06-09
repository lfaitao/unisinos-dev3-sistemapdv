/**
 * Created by lfaitao on 4/19/17.
 */

import {ipcRenderer} from 'electron'
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
                    context.openAlert("Não foi possível se conectar com o servidor " + cfg.get('apiUrl'))
                    return;
                } else {
                    console.log(err)
                    context.openAlert("Ocorreu um erro inesperado, por favor entre em contato com um Administrador do Sistema")
                    return;
                }
            }

            if (res.statusCode == 200) {
                if (res.body != null && res.body !="") {
                    this.user.authenticated = true
                    ipcRenderer.sendSync('login-setCredentials', credentials)
                    if (redirect) {
                        router.push(redirect)
                    }
                } else {
                    context.openAlert("Credenciais informadas invalidas.")
                }
            }
        })
    },

    logout(){
        this.user.authenticated = false
        window.localStorage.setItem('user', null)
        window.localStorage.removeItem('user')
        ipcRenderer.sendSync('login-cleanCredentials')
        ipcRenderer.sendSync('caixa-setBloqueado', false)
        router.push('/')
    },

    checkAuth(){
        return cfg.get('authenticated')
    }
}