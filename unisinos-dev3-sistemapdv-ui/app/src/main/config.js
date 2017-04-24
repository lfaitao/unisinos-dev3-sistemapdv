import {homedir} from 'os'
import Config from 'electron-config'
import {app, ipcMain} from 'electron'

const _cfg = new Config()

const DEFAULTS = {
    apiUrl: 'http://localhost:8080/sistemapdv/api',
    credentials: {
        username: '',
        password: ''
    },
    authenticated: false
}

const init = function () {
    _cfg.store = DEFAULTS
}

const getConfig = function () {
    return _cfg
}

exports.init = init
exports.getConfig = getConfig