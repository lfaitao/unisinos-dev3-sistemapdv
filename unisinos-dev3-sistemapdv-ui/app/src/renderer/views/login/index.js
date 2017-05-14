/**
 * Created by lfaitao on 4/19/17.
 */

import {router} from '../../main'
import request from 'request'
import Config from 'electron-config'
const cfg = new Config()

// Endpoints
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
            localStorage.setItem('user', credentials)

            if (res.body == 'true') {
                this.user.authenticated = true
                if (redirect) {
                    router.push(redirect)
                }
            } else {
                context.error = 'Credenciais informadas invalidas.'
                context.openAlert()
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