'use strict'

import {app, BrowserWindow, ipcMain} from 'electron'

import log from 'electron-log'

log.transports.file.appName = 'sistemapdv';
log.transports.console.level = 'info';
log.transports.file.level = 'info';

let mainWindow

const cfg = require('./config')

cfg.init()

log.info('Listener :: Login success started')
ipcMain.on('login-success', (event, arg) => {
    cfg.getConfig().set('credentials', arg)
    cfg.getConfig().set('authenticated', true)
    event.returnValue = true
})

ipcMain.on('logout', (event, arg) => {
    cfg.getConfig().set('credentials', {})
    cfg.getConfig().set('authenticated', false)
    event.returnValue = true
})

const winURL = process.env.NODE_ENV === 'development'
    ? `http://localhost:${require('../../../config').port}`
    : `file://${__dirname}/index.html`

function createWindow() {
    /**
     * Initial window options
     */
    mainWindow = new BrowserWindow({
        height: 780,
        width: 1024
    })

    mainWindow.loadURL(winURL)

    mainWindow.on('closed', () => {
        mainWindow = null
    })

}

app.on('ready', createWindow)

app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        app.quit()
    }
})

app.on('activate', () => {
    if (mainWindow === null) {
        createWindow()
    }
})
