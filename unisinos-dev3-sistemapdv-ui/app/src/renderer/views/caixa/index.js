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
     * Verifica se o caixa atingiu limite
     */

    isLimiteMaximo(context){

        let caixaNumero = ipcRenderer.sendSync("caixa-getNumero");

        request.get({url: BACKEND_URL + caixaNumero + "/limiteMaximo"}, (err, res, body) => {
            let response = JSON.parse(res.body)
            
            if(response.status == false){
                context.openAlert(response.message + " Caixa será bloqueado.");
               
               setTimeout(() => {this.bloquearCaixa(context)}, 3000);
            }

        })
    },

    /*
     * Busca caixa pelo numero
     */

    getCaixa(context, caixaNumero){
        if (caixaNumero === null) {
            context.openAlert('Número do caixa é necessário!')
        } else {
            request.get({
                url: BACKEND_URL + caixaNumero
            }, (err, res, body) => {
                let response = JSON.parse(res.body)
                context.getCaixaCallback(response)
            })
        }
    },

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
            context.closeDialog('dialog-desbloquearCaixa')
            context.$refs['navbar'].toggleCaixaBloqueadoIcon()
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
                url: BACKEND_URL + 'suprir/' + valorSuprimento + '/credenciais/' + context.credentials.username + '/' + context.credentials.password
            }, (err, res, body) => {
                let response = JSON.parse(res.body)

                if (response.status === true) {
                    context.closeDialog('dialog-realizarSuprimento')
                    context.isSuprimentoMinimo = false
                    context.valorSuprimento = 0
                    context.credentials = {
                        username: '',
                        password: ''
                    }
                    context.errors.clear()
                }

                context.sync()
                context.openAlert(response.message)
            })
        }
    },
    realizarSangria(context, valorSangria){
        if (valorSangria === null) {
            context.openAlert('O valor da sangria deve ser maior que zero!')
        } else {
            request.get({
                url: BACKEND_URL + 'sangrar/' + valorSangria + '/credenciais/' + context.credentials.username + '/' + context.credentials.password
            }, (err, res, body) => {
                let response = JSON.parse(res.body)

                if (response.status === true) {
                    console.log("Ué")
                    context.closeDialog('dialog-realizarSangria')
                    context.valorSangria = 0
                    context.credentials = {
                        username: '',
                        password: ''
                    }
                    context.errors.clear()
                }

                context.sync()
                context.openAlert(response.message)
            })
        }
    }

}