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
                        <md-button class="md-raised md-primary" @click.native="!isCaixaBloqueado() && realizarSangria()">Realizar Sangria</md-button>
                        <md-button class="md-raised md-primary" @click.native="!isCaixaBloqueado() && realizarSuprimento()">Realizar Suprimento</md-button>
                        <md-button class="md-raised md-primary" @click.native="!isCaixaBloqueado() && abrirDiaFiscal()">Abrir Dia Fiscal (ECF)</md-button>
                        <md-button class="md-raised md-primary" @click.native="!isCaixaBloqueado() && fecharDiaFiscal()">Fechar Dia Fiscal (ECF)</md-button>
                    </md-card>
                </md-layout>
            </md-layout>
        </div>

        <!-- Dialog Abrir Caixa -->
        <md-dialog ref="dialog-abrirCaixa">
            <md-dialog-title>Abrir Caixa</md-dialog-title>
            <md-dialog-content>
                <md-input-container :class="{'md-input-invalid': errors.has('numero')}">
                    <label>Número do Caixa</label>
                    <md-input type="number" min="1 " v-model="caixaNumero" data-vv-name="numero" v-validate data-vv-rules="required|min:1|max:2"></md-input>
                    <span class="md-error">{{errors.first('numero')}}</span>
                </md-input-container>
            </md-dialog-content>

            <md-dialog-actions>
                <md-button class="md-raised md-primary" @click.native="abrirCaixaSave()">OK</md-button>
                <md-button class="md-primary" @click.native="closeDialog('dialog-abrirCaixa')">Cancelar</md-button>
            </md-dialog-actions>
        </md-dialog>

        <!-- Dialog Desbloquear Caixa -->
        <md-dialog ref="dialog-desbloquearCaixa">
            <md-dialog-title>Desbloquear Caixa</md-dialog-title>
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
                <md-button class="md-raised md-primary" @click.native="desbloquearCaixaSave()">OK</md-button>
                <md-button class="md-primary" @click.native="closeDialog('dialog-desbloquearCaixa')">Cancelar</md-button>
            </md-dialog-actions>
        </md-dialog>

        <!-- Dialog Realizar Suprimento -->
        <md-dialog ref="dialog-realizarSuprimento">
        <md-dialog-title>Realizar Suprimento</md-dialog-title>
            <md-dialog-content>
                <md-input-container :class="{'md-input-invalid': errors.has('valorSuprimento')}">
                    <label>Valor a Suprir</label>
                    <md-input type="number" min="1" v-model="valorSuprimento" data-vv-name="valorSuprimento" v-validate data-vv-rules="required|min:1|max:10"></md-input>
                    <span class="md-error">{{errors.first('valorSuprimento')}}</span>
                </md-input-container>
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
                <md-button class="md-raised md-primary" @click.native="realizarSuprimentoSave()">OK</md-button>
                <div v-if="isSuprimentoMinimo">
                    <md-button class="md-primary" @click.native="closeDialog('dialog-realizarSuprimento') & fecharCaixa()">Cancelar</md-button>
                </div>
                <div v-else>
                    <md-button class="md-primary" @click.native="closeDialog('dialog-realizarSuprimento')">Cancelar</md-button>
                </div>
            </md-dialog-actions>
        </md-dialog>

        <!-- Dialog Realizar Sangria -->
        <md-dialog ref="dialog-realizarSangria">
        <md-dialog-title>Realizar Sangria</md-dialog-title>
            <md-dialog-content>
                <md-input-container :class="{'md-input-invalid': errors.has('valorSangria')}">
                    <label>Valor a Sangrar</label>
                    <md-input type="number" min="1" v-model="valorSangria" data-vv-name="valorSangria" v-validate data-vv-rules="required|min:1|max:10"></md-input>
                    <span class="md-error">{{errors.first('valorSangria')}}</span>
                </md-input-container>
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
                <md-button class="md-raised md-primary" @click.native="realizarSangriaSave()">OK</md-button>
                <md-button class="md-primary" @click.native="closeDialog('dialog-realizarSangria')">Cancelar</md-button>
            </md-dialog-actions>
        </md-dialog>

        <!-- Dialog Abrir Dia Fiscal -->
        <md-dialog ref="dialog-abrirDiaFiscal">
        <md-dialog-title>Abrir Dia Fiscal</md-dialog-title>
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
                <md-button class="md-raised md-primary" @click.native="abrirDiaFiscalSave()">OK</md-button>
                <md-button class="md-primary" @click.native="closeDialog('dialog-abrirDiaFiscal')">Cancelar</md-button>
            </md-dialog-actions>
        </md-dialog>

        <!-- Dialog Fechar Dia Fiscal -->
        <md-dialog ref="dialog-fecharDiaFiscal">
        <md-dialog-title>Fechar Dia Fiscal</md-dialog-title>
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
                <md-button class="md-raised md-primary" @click.native="fecharDiaFiscalSave()">OK</md-button>
                <md-button class="md-primary" @click.native="closeDialog('dialog-fecharDiaFiscal')">Cancelar</md-button>
            </md-dialog-actions>
        </md-dialog>

        <!-- Snackbar -->
        <md-snackbar md-position="bottom center" ref="snackbar" md-duration="4000">
            <span>{{ error }}</span>
            <md-button class="md-accent" md-theme="light-blue" @click.native="$refs.snackbar.close()">
                OK
            </md-button>
        </md-snackbar>
    </div>
</template>

<script>
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
                diaFiscalAbertoStatus: false,
                caixaBloqueadoStatus: false,
                valorSuprimento: 0,
                valorSangria: 0,
                qtDinheiro: 0,
                qtDinheiroMinimo: 0,
                qtDinheiroMaximo: 0,
                isSuprimentoMinimo: false,
                error: ''
            }
        },
        methods: {
            abrirCaixa() {
                if (this.caixaAbertoStatus) {
                  this.openAlert('Este caixa já está aberto!')
                } else if (!this.caixaBloqueadoStatus) {
                    this.errors.clear()
                    this.openDialog('dialog-abrirCaixa')
                }
            },
            abrirCaixaSave() {
                this.$validator.validateAll({
                    numero: this.caixaNumero
                }).then(() => {
                    backend.abrirCaixa(this, this.caixaNumero)
                    this.closeDialog('dialog-abrirCaixa')
                    this.errors.clear()
                }).catch( bag => {
                    this.openAlert("Por favor, preencha as informações corretamente!")
                })
            },
            abrirCaixaCallback(response) {
                if (response.status === true) {
                    this.$refs['navbar'].toggleCaixaAbertoIcon()

                    // Verificando quantidade de dinheiro no caixa
                    let caixa = response.object
                    if(caixa.qtDinheiro === 0) {
                        this.openAlert("A quantidade de dinheiro no caixa está zerada. Por favor, insira no mínimo R$ " + caixa.qtDinheiroMinimo + ".")
                        this.qtDinheiro = caixa.qtDinheiro
                        this.qtDinheiroMinimo = caixa.qtDinheiroMinimo
                        this.qtDinheiroMaximo = caixa.qtDinheiroMaximo
                        this.isSuprimentoMinimo = true
                        this.realizarSuprimento()
                    }
                }
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
                if (this.caixaAbertoStatus) {
                    if (this.caixaBloqueadoStatus) {
                        this.openAlert('Este caixa já está bloqueado!')
                    } else {
                        backend.bloquearCaixa(this)
                        this.$refs['navbar'].toggleCaixaBloqueadoIcon()
                    }
                } else {
                    this.openAlert('Esta operação requer que o caixa esteja aberto!')
                }
            },
            desbloquearCaixa() {
                if (this.caixaAbertoStatus) {
                    if (this.caixaBloqueadoStatus) {
                        this.openDialog('dialog-desbloquearCaixa')
                    } else {
                        this.openAlert('Este caixa já está desbloqueado!')
                    }
                } else {
                    this.openAlert('Esta operação requer que o caixa esteja aberto!')
                }
            },
            desbloquearCaixaSave() {
                this.$validator.validateAll({
                    usuario: this.credentials.username,
                    senha: this.credentials.password
                }).then(() => {
                    backend.desbloquearCaixa(this, this.credentials)
                }).catch( bag => {
                    this.openAlert("Por favor, preencha todos os campos obrigatórios!")
                })
            },
            realizarSuprimento() {
                if (this.caixaAbertoStatus) {
                    if (!this.isSuprimentoMinimo) {
                        backend.getCaixa(this, this.caixaNumero)
                    }

                    // Valida se a quantidade maxima de dinheiro estabelecida para o caixa já foi atingida
                    if(this.qtDinheiro === this.qtDinheiroMaximo) {
                        this.openAlert("O valor máximo de dinheiro no caixa já foi atingido (R$ " + this.qtDinheiroMaximo + ").")
                    } else {
                        this.openDialog('dialog-realizarSuprimento')
                    }
                } else {
                    this.openAlert('Esta operação requer que o caixa esteja aberto!')
                }
            },
            realizarSuprimentoSave() {
                this.$validator.validateAll({
                    valorSuprimento: this.valorSuprimento,
                    usuario: this.credentials.username,
                    senha: this.credentials.password
                }).then(() => {
                    // Valida se é suprimento mínimo e se é necessário suprir valor minimo ao caixa
                    if(this.isSuprimentoMinimo && (this.valorSuprimento < this.qtDinheiroMinimo)) {
                        this.openAlert("O valor mínimo do suprimento deve ser R$ " + this.qtDinheiroMinimo + ".")
                        return
                    }

                    // Valida se o suprimento ultrapassa a quantidade maxima de dinheiro estabelecida para o caixa
                    let totalFinal = +this.valorSuprimento + +this.qtDinheiro
                    if(totalFinal > this.qtDinheiroMaximo) {
                        let suprimentoMaximoAtual = this.qtDinheiroMaximo - this.qtDinheiro
                        this.openAlert("O valor máximo de dinheiro no caixa é R$ " + this.qtDinheiroMaximo + "." +
                            "\nEntão o valor máximo do suprimento deve ser R$ " + suprimentoMaximoAtual + ".")
                        return
                    }

                    backend.realizarSuprimento(this, this.valorSuprimento)
                }).catch( bag => {
                    this.openAlert("Por favor, preencha as informações corretamente!")
                })
            },
            realizarSangria() {
                if (this.caixaAbertoStatus) {
                    // Valida se a quantidade minima de dinheiro estabelecida para o caixa já foi atingida
                    backend.getCaixa(this, this.caixaNumero)
                    if(this.qtDinheiro === this.qtDinheiroMinimo) {
                        this.openAlert("O valor minimo de dinheiro no caixa já foi atingido (R$ " + this.qtDinheiroMinimo + ").")
                    } else {
                        this.openDialog('dialog-realizarSangria')
                    }
                } else if (!this.caixaBloqueadoStatus) {
                    this.openAlert('Esta operação requer que o caixa esteja aberto!')
                }
            },
            realizarSangriaSave() {
                this.$validator.validateAll({
                    valorSangria: this.valorSangria,
                    usuario: this.credentials.username,
                    senha: this.credentials.password
                }).then(() => {
                    // Valida se a sangria ultrapassa a quantidade minima de dinheiro estabelecida para o caixa
                    let totalFinal = +this.qtDinheiro - +this.valorSangria
                    console.log(totalFinal)
                    if(totalFinal < this.qtDinheiroMinimo) {
                        let sangriaMaximaAtual = this.qtDinheiro - this.qtDinheiroMinimo
                        this.openAlert("O valor minimo de dinheiro no caixa é R$ " + this.qtDinheiroMinimo + "." +
                            "\nEntão o valor máximo da sangria deve ser R$ " + sangriaMaximaAtual + ".")
                        return
                    }

                    backend.realizarSangria(this, this.valorSangria)
                }).catch( bag => {
                    this.openAlert("Por favor, preencha as informações corretamente!")
                })
            },
            abrirDiaFiscal() {
                if (this.caixaAbertoStatus) {
                    if(!this.diaFiscalAbertoStatus) {
                        this.openDialog('dialog-abrirDiaFiscal')
                    } else {
                        this.openAlert('O dia fiscal para este caixa já foi aberto!')
                    }
                } else {
                    this.openAlert('Esta operação requer que o caixa esteja aberto!')
                }
            },
            abrirDiaFiscalSave() {
                this.$validator.validateAll({
                    usuario: this.credentials.username,
                    senha: this.credentials.password
                }).then(() => {
                    backend.abrirDiaFiscal(this);
                }).catch( bag => {
                    this.openAlert("Por favor, preencha as informações corretamente!")
                })
            },
            fecharDiaFiscal() {
                if (this.caixaAbertoStatus) {
                    if(this.diaFiscalAbertoStatus) {
                        this.openDialog('dialog-fecharDiaFiscal')
                    } else {
                        this.openAlert('O dia fiscal para este caixa já foi fechado!')
                    }
                } else {
                    this.openAlert('Esta operação requer que o caixa esteja aberto!')
                }
            },
            fecharDiaFiscalSave() {
                this.$validator.validateAll({
                    usuario: this.credentials.username,
                    senha: this.credentials.password
                }).then(() => {
                    backend.fecharDiaFiscal(this);
                }).catch( bag => {
                    this.openAlert("Por favor, preencha as informações corretamente!")
                })
            },
            getCaixaCallback(caixa) {
                this.qtDinheiro = caixa.qtDinheiro
                this.qtDinheiroMinimo = caixa.qtDinheiroMinimo
                this.qtDinheiroMaximo = caixa.qtDinheiroMaximo
            },
            isCaixaAberto() {
                backend.isCaixaAberto(this, this.caixaNumero)
            },
            isCaixaBloqueado() {
                return backend.isCaixaBloqueado(this)
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
            },
            sync() {
                backend.getCaixa(this, this.caixaNumero)
            }
        },
        mounted() {
            this.caixaNumero = ipcRenderer.sendSync('caixa-getNumero')
            this.caixaAbertoStatus = ipcRenderer.sendSync('caixa-getAberto')
            this.caixaBloqueadoStatus = ipcRenderer.sendSync('caixa-getBloqueado')
            this.diaFiscalAbertoStatus = ipcRenderer.sendSync('caixa-getDiaFiscalAberto')
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
