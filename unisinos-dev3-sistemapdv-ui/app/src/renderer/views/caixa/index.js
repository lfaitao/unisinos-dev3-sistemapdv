/**
 * Created by lfaitao on 5/14/17.
 */

import {ipcRenderer} from 'electron'
import request from 'request'
import Config from 'electron-config'

const cfg = new Config()
const BACKEND_URL = cfg.get('apiUrl') + '/caixa/'

export default {

    /*
     * Abertura e fechamento do caixa
     */

    abrirCaixa(context, caixaNumero) {
        if (caixaNumero === null) {
            context.openAlert('Número do caixa é necessário!')
        } else {
            request.get({
                url: BACKEND_URL + 'abrir/' + caixaNumero
            }, (err, res, body) => {
                let response = JSON.parse(res.body)

                // Seta numero do caixa
                if (response.status) {
                    ipcRenderer.sendSync('caixa-setNumero', caixaNumero)
                } else {
                    ipcRenderer.sendSync('caixa-setNumero', null)
                    context.caixaNumero = null
                }

                // Seta se o caixa está aberto
                context.caixaAbertoStatus = response.status
                ipcRenderer.sendSync('caixa-setAberto', response.status)
                context.openAlert(response.message)

                // Callback
                context.abrirCaixaCallback(response)
            })
        }
    },
    fecharCaixa(context) {
        request.get({
            url: BACKEND_URL + 'fechar'
        }, (err, res, body) => {
            let response = JSON.parse(res.body)

            // Seta numero do caixa
            if (response.status) {
                ipcRenderer.sendSync('caixa-setNumero', null)
                context.caixaNumero = null
                context.caixaAbertoStatus = false
                ipcRenderer.sendSync('caixa-setAberto', false)
            }

            context.openAlert(response.message)
        })
    },
    isCaixaAberto(context, caixaNumero) {
        if (caixaNumero === null) {
            context.caixaAbertoStatus = false
        } else {
            request.get({
                url: BACKEND_URL + 'isAberto/' + caixaNumero
            }, (err, res, body) => {
                if (res.body == 'true') {
                    context.caixaAbertoStatus = true
                } else {
                    context.caixaAbertoStatus = false
                }
            })
        }
    },

    /*
     * Bloqueio e desbloqueio do caixa
     */

    bloquearCaixa(context) {
        ipcRenderer.sendSync('caixa-setBloqueado', true)
        context.caixaBloqueadoStatus = true
        context.openAlert("Caixa bloqueado com sucesso!")
    },
    desbloquearCaixa(context, credentials) {
        let currentCredentials = ipcRenderer.sendSync('login-getCredentials')
        if (currentCredentials.username === credentials.username && currentCredentials.password === credentials.password) {
            ipcRenderer.sendSync('caixa-setBloqueado', false)
            context.caixaBloqueadoStatus = false
            context.credentials.username = ''
            context.credentials.password = ''
            context.openAlert("Caixa desbloqueado com sucesso!")
            context.errors.clear()
        } else {
            context.openAlert("As credenciais inseridas não são as do usuário que bloqueou o caixa! Por favor, tente novamente.")
        }
    },
    isCaixaBloqueado(context) {
        context.caixaBloqueadoStatus = ipcRenderer.sendSync('caixa-getBloqueado');
        if (context.caixaBloqueadoStatus) {
            context.openAlert("O caixa está bloqueado para operações!")
        }
    },

    /*
     * Suprimento e sangria do caixa
     */

    realizarSuprimento(context, valorSuprimento){
        if (valorSuprimento === null) {
            context.openAlert('O valor do suprimento deve ser maior que zero!')
        } else {
            request.get({
                url: BACKEND_URL + 'suprir/' + valorSuprimento
            }, (err, res, body) => {
                let response = JSON.parse(res.body)
                context.openAlert(response.message)
            })
        }
    }

}