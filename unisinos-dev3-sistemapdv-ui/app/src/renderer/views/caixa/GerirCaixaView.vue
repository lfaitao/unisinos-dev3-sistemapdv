<template>
    <div>
        <navbar ref="navbar" title="Gerir Caixa" previousPage="/home"></navbar>
        <div class="mainMenu centered_div">
            <md-layout md-gutter md-align="center">
                <md-layout>
                    <md-card md-align="center">
                        <md-button class="md-raised md-primary" @click.native="!isCaixaBloqueado() && abrirCaixa()">Abrir Caixa</md-button>
                        <md-button class="md-raised md-primary" @click.native="!isCaixaBloqueado() && fecharCaixa()">Fechar Caixa</md-button>
                        <md-button class="md-raised md-primary" @click.native="bloquearCaixa()">Bloquear Caixa</md-button>
                        <md-button class="md-raised md-primary" @click.native="desbloquearCaixa()">Desbloquear Caixa</md-button>
                        <md-button class="md-raised md-primary" @click.native="!isCaixaBloqueado()">Realizar Sangria</md-button>
                        <md-button class="md-raised md-primary" @click.native="!isCaixaBloqueado()">Realizar Suprimento</md-button>
                        <md-button class="md-raised md-primary" @click.native="!isCaixaBloqueado()">Abrir Dia Fiscal (ECF)</md-button>
                        <md-button class="md-raised md-primary" @click.native="!isCaixaBloqueado()">Fechar Dia Fiscal (ECF)</md-button>
                    </md-card>
                </md-layout>
            </md-layout>
        </div>

        <!-- Dialog Abrir Caixa -->
        <md-dialog ref="dialog-abrirCaixa">
            <md-dialog-title>Abrir Caixa</md-dialog-title>
            <form>
                <md-dialog-content>
                    <md-input-container :class="{'md-input-invalid': errors.has('numero')}">
                        <label>Número do Caixa</label>
                        <md-input type="number" min="1" v-model="caixaNumero" data-vv-name="numero" v-validate data-vv-rules="required|min:1|max:2"></md-input>
                        <span class="md-error">{{errors.first('numero')}}</span>
                    </md-input-container>
                </md-dialog-content>

                <md-dialog-actions>
                    <md-button type="submit" class="md-raised md-primary" @click.native="abrirCaixaSave()">Abrir Caixa</md-button>
                    <md-button class="md-primary" @click.native="closeDialog('dialog-abrirCaixa')">Cancelar</md-button>
                </md-dialog-actions>
            </form>
        </md-dialog>

        <!-- Dialog Desbloquear Caixa -->
        <md-dialog ref="dialog-desbloquearCaixa">
            <md-dialog-title>Desbloquear Caixa</md-dialog-title>
            <form>
                <md-dialog-content>
                    <md-input-container :class="{'md-input-invalid': errors.has('usuario')}">
                        <label>Usuário</label>
                        <md-input type="text" v-model="credentials.username" data-vv-name="usuario" v-validate data-vv-rules="required|min:5|max:45"></md-input>
                        <span class="md-error">{{errors.first('usuario')}}</span>
                    </md-input-container>
                    <md-input-container :class="{'md-input-invalid': errors.has('senha')}">
                        <label>Senha</label>
                        <md-input type="password" v-model="credentials.password" data-vv-name="senha" v-validate data-vv-rules="required|min:5|max:45"></md-input>
                        <span class="md-error">{{errors.first('senha')}}</span>
                    </md-input-container>
                </md-dialog-content>

                <md-dialog-actions>
                    <md-button type="submit" class="md-raised md-primary" @click.native="desbloquearCaixaSave()">Desbloquear Caixa</md-button>
                    <md-button class="md-primary" @click.native="closeDialog('dialog-desbloquearCaixa')">Cancelar</md-button>
                </md-dialog-actions>
            </form>
        </md-dialog>

        <md-snackbar md-position="bottom center" ref="snackbar" md-duration="4000">
            <span>{{ error }}</span>
            <md-button class="md-accent" md-theme="light-blue" @click.native="$refs.snackbar.close()">
                OK
            </md-button>
        </md-snackbar>
    </div>
</template>

<script>
    import {router} from '../../main'
    import {ipcRenderer} from 'electron'
    import backend from './index'

    export default {
        data() {
            return {
                MOMENTO_INICIANDO: 1,
                MOMENTO_CLICANDO: 2,
                title: 'Gerir Caixa',
                credentials: {
                    username: '',
                    password: ''
                },
                caixaNumero: null,
                caixaAbertoStatus: false,
                caixaBloqueadoStatus: false,
                error: ''
            }
        },
        methods: {
            goTo(route) {
              router.push(route)
            },
            abrirCaixa() {
                if (this.caixaAbertoStatus) {
                  this.openAlert('Este caixa já está aberto!')
                } else if (!this.caixaBloqueadoStatus) {
                  this.openDialog('dialog-abrirCaixa')
                }
            },
            abrirCaixaSave() {
                this.$validator.validateAll().then(() => {
                    backend.abrirCaixa(this, this.caixaNumero)
                })
                this.closeDialog('dialog-abrirCaixa')
                this.errors.clear()
                this.$refs['navbar'].toggleCaixaAbertoIcon()
            },
            fecharCaixa() {
                if (this.caixaAbertoStatus) {
                    if (!this.caixaBloqueadoStatus) {
                        backend.fecharCaixa(this)
                        this.$refs['navbar'].toggleCaixaAbertoIcon()
                    }
                } else {
                    this.openAlert('Este caixa já está fechado!')
                }
            },
            bloquearCaixa() {
                if (this.caixaBloqueadoStatus) {
                    this.openAlert('Este caixa já está bloqueado!')
                } else {
                    backend.bloquearCaixa(this)
                    this.$refs['navbar'].toggleCaixaBloqueadoIcon()
                }
            },
            desbloquearCaixa() {
                if (this.caixaBloqueadoStatus) {
                    this.openDialog('dialog-desbloquearCaixa')
                } else {
                    this.openAlert('Este caixa já está desbloqueado!')
                }
            },
            desbloquearCaixaSave() {
                this.$validator.validateAll().then(() => {
                    backend.desbloquearCaixa(this, this.credentials)
                }).catch( bag => {
                    this.error = 'Por favor, preencha todos os campos obrigatórios!'
                    this.openAlert()
                })
            },
            isCaixaAberto() {
                backend.isCaixaAberto(this, this.caixaNumero)
            },
            isCaixaBloqueado() {
                backend.isCaixaBloqueado(this)
            },
            openDialog(dialogName) {
                this.$refs[dialogName].open();
            },
            closeDialog(dialogName) {
                this.$refs[dialogName].close();
            },
            openAlert(mensagem) {
                this.error = mensagem
                this.$refs.snackbar.open()
            }
        },
        mounted() {
            this.caixaAbertoStatus = ipcRenderer.sendSync('caixa-getAberto')
            this.caixaNumero = ipcRenderer.sendSync('caixa-getNumero')
            this.caixaBloqueadoStatus = ipcRenderer.sendSync('caixa-getBloqueado')
        },
        name: 'gerir-caixa'
    }
</script>

<style scoped>
    .mainMenu {
        background-color: white;
    }
    .centered_div {
        position: absolute;
        top:  22%;
        bottom:  50%;
        left: 50%;
        right: 50%;
    }
</style>
