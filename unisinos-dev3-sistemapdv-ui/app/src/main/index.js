'use strict'

import { app, BrowserWindow } from 'electron'
import request from 'request'
import {getConfig} from './config'

let mainWindow
let splashScreenWindow
const winURL = process.env.NODE_ENV === 'development'
  ? `http://localhost:${require('../../../config').port}`
  : `file://${__dirname}/index.html`
const cfg = require('./config')
cfg.init()

const BACKEND_CAIXA_URL = getConfig().get('apiUrl') + '/caixa/'
const exec = require('child_process').exec
const treeKill = require('tree-kill')

let YELLOW = '\x1b[33m'
let BLUE = '\x1b[34m'
let END = '\x1b[0m'
let isBackendUp = false

require('./caixa')
require('./login')

// ======== Codigo para rodar backend (INICIO)

function format(command, data, color) {
    return color + command + END +
        '  ' + // Two space offset
        data.toString().trim().replace(/\n/g, '\n' + repeat(' ', command.length + 2)) +
        '\n'
}

function repeat(str, times) {
    return (new Array(times + 1)).join(str)
}

let children = []

function run(command, color, name) {
    let child = exec(command)

    child.stderr.on('data', data => console.error(format(name, data, color)))
    child.on('exit', code => exit(code))

    children.push(child)
}

function exit(code) {
    children.forEach(child => {
        treeKill(child.pid)
    })
}

function exit() {
    children.forEach(child => {
        treeKill(child.pid)
    })
}

// ======== Codigo para rodar backend (FIM)

const loadView = ({title,scriptUrl}) => {
  return (`
    <!DOCTYPE html>
    <html>
      <head>
        <meta charset="utf-8">
        <title>SistemaPDV</title>
        <link rel="stylesheet" href="//fonts.googleapis.com/css?family=Roboto:300,400,500,700,400italic">
        <link rel="stylesheet" href="//fonts.googleapis.com/icon?family=Material+Icons">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
      </head>
      <body>
        <div class="container">
          <div class="row">
            <div class="col-md-12" style="text-align: center; padding-top: 50px;">
              <h1>Sistema PDV</h1>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <h2>Carregando. Aguarde...</h2>
            </div>
          </div>
        </div>
      </body>
    </html>
  `)
}

var splash = 'data:text/html;charset=UTF-8,' + encodeURIComponent(loadView({
  title: "Splash"
}));

function runBackend() {
    if (!isBackendUp) {
        console.log(`${BLUE}Starting backend...\n${END}`)
        run('java -jar ./resources/unisinos-dev3-sistemapdv-api-0.1.0.jar', BLUE, 'backend')
        isBackendUp = true
    }
}

function createWindowCallback () {
  splashScreenWindow.hide()

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
      splashScreenWindow.close()

      if (getConfig().get('caixaAbertoStatus')) {
          request.get({
              url: BACKEND_CAIXA_URL + 'fechar'
          }, (err, res, body) => {
          })
      }

      exit()
  })

  // eslint-disable-next-line no-console
  console.log('mainWindow opened')
}

function createWindow () {
  splashScreenWindow = new BrowserWindow({
    height: 250, 
    width: 350,
    minHeight: 250,
    minWidth: 350,
    maxHeight: 250,
    maxWidth: 350,
    frame: false
  })
  splashScreenWindow.loadURL(splash)

  setTimeout(createWindowCallback, 30000)
}

app.on('ready', runBackend)
app.on('ready', createWindow)

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit()
  }
  exit()
})

app.on('activate', () => {
  if (mainWindow === null) {
    createWindow()
  }
})
