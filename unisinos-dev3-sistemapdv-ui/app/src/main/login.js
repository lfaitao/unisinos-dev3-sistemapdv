/**
 * Created by lfaitao on 6/4/17.
 */

import {ipcMain} from 'electron'
import {getConfig} from './config'

ipcMain.on('login-setCredentials', (event, arg) => {
    getConfig().set('credentials', arg)
    event.returnValue = true
})

ipcMain.on('login-getCredentials', (event) => {
    event.returnValue = getConfig().get('credentials')
})

ipcMain.on('login-cleanCredentials', (event) => {
    getConfig().set('credentials.username', '')
    getConfig().set('credentials.password', '')
    event.returnValue = true
})