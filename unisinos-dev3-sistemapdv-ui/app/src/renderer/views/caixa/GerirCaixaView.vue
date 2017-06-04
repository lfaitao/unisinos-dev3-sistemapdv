<template>
    <div>
        <navbar ref="navbar" title="Gerir Caixa" previousPage="/home"></navbar>
        <div class="mainMenu centered_div">
            <md-layout md-gutter md-align="center">
                <md-layout>
                    <md-card md-align="center">
                        <md-button class="md-raised md-primary" @click.native="abrirCaixa()">Abrir Caixa</md-button>
                        <md-button class="md-raised md-primary" @click.native="fecharCaixa()">Fechar Caixa</md-button>
                        <md-button class="md-raised md-primary" @click.native="">Bloquear Caixa</md-button>
                        <md-button class="md-raised md-primary" @click.native="">Desbloquear Caixa</md-button>
                        <md-button class="md-raised md-primary" @click.native="">Realizar Sangria</md-button>
                        <md-button class="md-raised md-primary" @click.native="">Realizar Suprimento</md-button>
                        <md-button class="md-raised md-primary" @click.native="">Abrir Dia Fiscal (ECF)</md-button>
                        <md-button class="md-raised md-primary" @click.native="">Fechar Dia Fiscal (ECF)</md-button>
                    </md-card>
                </md-layout>
            </md-layout>
        </div>

        <!-- Dialog Abrir Caixa -->
        <md-dialog ref="dialog-abrirCaixa">
            <md-dialog-title>Abrir Caixa</md-dialog-title>
            <md-dialog-content>
                <form>
                    <md-input-container :class="{'md-input-invalid': errors.has('numero')}">
                        <label>Número do Caixa</label>
                        <md-input type="number" min="1" v-model="caixaNumero" data-vv-name="numero" v-validate data-vv-rules="required|min:1|max:2"></md-input>
                        <span class="md-error">{{errors.first('numero')}}</span>
                    </md-input-container>
                </form>
            </md-dialog-content>

            <md-dialog-actions>
                <md-button class="md-primary" @click.native="abrirCaixaSave()">Abrir Caixa</md-button>
                <md-button class="md-primary" @click.native="closeDialog('dialog-abrirCaixa')">Cancelar</md-button>
            </md-dialog-actions>
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
                title: 'Gerir Caixa',
                caixaNumero: null,
                caixaAberto: false,
                error: ''
            }
        },
        methods: {
            goTo(route) {
              router.push(route)
            },
            abrirCaixa() {
                if (this.caixaAberto) {
                  this.error = 'Este caixa já está aberto!'
                  this.openAlert()
                } else {
                  this.openDialog('dialog-abrirCaixa')
                }
            },
            abrirCaixaSave() {
                this.$validator.validateAll().then(() => {
                    backend.abrirCaixa(this, this.caixaNumero)
                })
                this.closeDialog('dialog-abrirCaixa')
                this.errors.clear()
                this.$refs['navbar'].toggleIcon()
            },
            fecharCaixa() {
                if (this.caixaAberto) {
                    backend.fecharCaixa(this)
                    this.$refs['navbar'].toggleIcon()
                } else {
                    this.error = 'Este caixa já está fechado!'
                    this.openAlert()
                }
            },
            isCaixaAberto() {
                backend.isCaixaAberto(this, this.caixaNumero)
            },
            openDialog(dialogName) {
                this.$refs[dialogName].open();
            },
            closeDialog(dialogName) {
                this.$refs[dialogName].close();
            },
            openAlert() {
                this.$refs.snackbar.open()
            }
        },
        mounted() {
            this.caixaAberto = ipcRenderer.sendSync('caixa-getAberto')
            this.caixaNumero = ipcRenderer.sendSync('caixa-getNumero')
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
