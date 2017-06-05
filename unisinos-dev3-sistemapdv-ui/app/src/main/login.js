/**
 * Created by lfaitao on 6/4/17.
 */

import {ipcMain} from 'electron'
import {getConfig} from './config'

ipcMain.on('login-setCredentials', (event, arg) => {
    getConfig().set('credentials', arg)
    event.returnValue = true
})

ipcMain.on('login-getCredentials', (event, arg) => {
    event.returnValue = getConfig().get('credentials')
})