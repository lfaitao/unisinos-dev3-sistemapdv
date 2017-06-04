<template>
    <div>
        <div class="mainMenu centered">
            <md-layout md-gutter md-align="center">
                <md-layout>
                    <md-card md-align="center background" style="width: 300px;">
                        <form novalidate @submit.prevent.stop="submit">
                            <md-card-header style="text-align: center;">
                                <md-card-header-text>
                                    <h2 class="md-title">Sistema PDV</h2>
                                </md-card-header-text>
                            </md-card-header>
                            <md-card-content>
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
                            </md-card-content>

                            <md-card-actions>
                                <md-button type="submit" class="md-raised md-primary">Entrar</md-button>
                            </md-card-actions>
                        </form>
                    </md-card>
                </md-layout>
            </md-layout>
        </div>
        <md-snackbar md-position="bottom center" ref="snackbar" md-duration="4000">
            <span>{{ error }}</span>
            <md-button class="md-accent" md-theme="light-blue" @click.native="$refs.snackbar.close()">
                OK
            </md-button>
        </md-snackbar>
    </div>
</template>

<style scoped>
    .centered {
        position: absolute;
        top:  22%;
        bottom:  50%;
        left: 50%;
        right: 50%;
    }
</style>

<script>

    import auth from './index'
    import {router} from '../../main'

    export default {
        data() {
            return {
                credentials: {
                    username: '',
                    password: ''
                },
                user: {},
                error: ''
            }
        },
        methods: {
            openAlert() {
                this.$refs.snackbar.open()
            },
            submit(){
                this.$validator.validateAll().then(() => {
                    auth.login(this, this.credentials, 'home')
                }).catch( bag => {
                    this.error = 'Por favor, preencha todos os campos obrigatórios!'
                    this.openAlert()
                })
            }
        },
        name: 'login-page'
    }
</script>