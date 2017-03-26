import {homedir} from 'os'
import {resolve} from 'path'
import Config from 'electron-config'
import mkdirp from 'mkdirp'
import {ipcMain} from 'electron'

const _cfg = new Config()

const DEFAULTS = {
    apiUrl: 'http://localhost:8080/',
    credentials: {
        username: '',
        password: ''
    },
    authenticated: false,
    openOnStartup: false,
    showWizardOnStartup: true
}

console.log(_cfg.path)

const init = function () {
    _cfg.store = DEFAULTS
}

const getConfig = function () {
    return _cfg
}

exports.init = init
exports.getConfig = getConfig