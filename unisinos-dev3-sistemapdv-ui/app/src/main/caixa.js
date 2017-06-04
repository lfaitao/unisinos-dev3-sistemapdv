/**
 * Created by lfaitao on 5/14/17.
 */

import {ipcMain} from 'electron'

import {getConfig} from './config'

ipcMain.on('caixa-setNumero', (event, arg) => {
    getConfig().set('caixaNumero', arg)
    event.returnValue = true
})

ipcMain.on('caixa-setAberto', (event, arg) => {
    getConfig().set('caixaAbertoStatus', arg)
    event.returnValue = true
})

ipcMain.on('caixa-setBloqueado', (event, arg) => {
    getConfig().set('caixaBloqueadoStatus', arg)
    event.returnValue = true
})

ipcMain.on('caixa-getNumero', (event, arg) => {
    event.returnValue = getConfig().get('caixaNumero')
})

ipcMain.on('caixa-getAberto', (event, arg) => {
    event.returnValue = getConfig().get('caixaAbertoStatus')
})

ipcMain.on('caixa-getBloqueado', (event, arg) => {
    event.returnValue = getConfig().get('caixaBloqueadoStatus')
})