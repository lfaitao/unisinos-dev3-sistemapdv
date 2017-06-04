'use strict'

import { app, BrowserWindow } from 'electron'
import request from 'request'
import {getConfig} from './config'

let mainWindow
const winURL = process.env.NODE_ENV === 'development'
  ? `http://localhost:${require('../../../config').port}`
  : `file://${__dirname}/index.html`

const cfg = require('./config')
cfg.init()

const BACKEND_CAIXA_URL = getConfig().get('apiUrl') + '/caixa/'

require('./caixa')

function createWindow () {
  /**
   * Initial window options
   */
  mainWindow = new BrowserWindow({
    height: 600,
    width: 800,
    minHeight: 600,
    minWidth: 800
  })

  mainWindow.loadURL(winURL)

  mainWindow.on('closed', () => {
      mainWindow = null

      if (getConfig().get('caixaAbertoStatus')) {
          request.get({
              url: BACKEND_CAIXA_URL + 'fechar'
          }, (err, res, body) => {
          })
      }
  })

  // eslint-disable-next-line no-console
  console.log('mainWindow opened')
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
