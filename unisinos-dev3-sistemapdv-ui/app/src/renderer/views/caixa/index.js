/**
 * Created by lfaitao on 5/14/17.
 */

import {ipcRenderer} from 'electron'
import request from 'request'
import Config from 'electron-config'

const cfg = new Config()
const BACKEND_URL = cfg.get('apiUrl') + '/caixa/'

export default {
    abrirCaixa(context, caixaNumero) {
        if (caixaNumero === null) {
            context.error = 'Número do caixa é necessário!'
            context.openAlert()
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
                context.caixaAberto = response.status
                ipcRenderer.sendSync('caixa-setAberto', response.status)
                context.error = response.message
                context.openAlert()
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
                context.caixaAberto = false
                ipcRenderer.sendSync('caixa-setAberto', false)
            }

            context.error = response.message
            context.openAlert()
        })
    },
    bloquearCaixa(context) {
        ipcRenderer.sendSync('caixa-setBloqueado', true)
        context.caixaBloqueado = true
        context.error = "Caixa bloqueado com sucesso!"
        context.openAlert()
    },
    isCaixaAberto(context, caixaNumero) {
        if (caixaNumero === null) {
            context.caixaAberto = false
        } else {
            request.get({
                url: BACKEND_URL + 'isAberto/' + caixaNumero
            }, (err, res, body) => {
                if (res.body == 'true') {
                    context.caixaAberto = true
                } else {
                    context.caixaAberto = false
                }
            })
        }
    }
}