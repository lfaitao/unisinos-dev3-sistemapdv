<template>
    <div>
        <div class="mainMenu centered">
            <md-layout md-gutter md-align="center">
                <md-layout>
                    <md-card md-align="center background" style="width: 300px;background-color: rgba(200, 200, 200, 0.35)">
                        <form novalidate @submit.prevent.stop="submit">
                            <md-card-header style="text-align: center;">
                                <md-card-header-text>
                                    <h2 class="md-title">Sistema PDV</h2>
                                </md-card-header-text>
                            </md-card-header>
                            <md-card-content>
                                <md-input-container>
                                    <label>Usuário</label>
                                    <md-input type="text" v-model="credentials.username" maxlength="45"></md-input>
                                </md-input-container>
                                <md-input-container>
                                    <label>Senha</label>
                                    <md-input type="password" v-model="credentials.password"></md-input>
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
        <!--<md-layout md-gutter md-align="center" style="height: 100%">-->
            <!--<md-layout md-column md-align="center" style="height: 100%; align-items: center">-->

                <!--<md-card style="width: 33%" md-align="center">-->
                    <!--<form novalidate @submit.prevent.stop="submit">-->
                        <!--<md-card-header style="text-align: center;">-->
                            <!--<md-card-header-text>-->
                                <!--<h2 class="md-title">Bem-vindo</h2>-->
                            <!--</md-card-header-text>-->
                        <!--</md-card-header>-->
                        <!--<md-card-content>-->

                            <!--<md-input-container>-->
                                <!--<label>Usuário</label>-->
                                <!--<md-input type="text" v-model="credentials.username" maxlength="45"></md-input>-->
                            <!--</md-input-container>-->
                            <!--<md-input-container>-->
                                <!--<label>Senha</label>-->
                                <!--<md-input type="password" v-model="credentials.password"></md-input>-->
                            <!--</md-input-container>-->

                        <!--</md-card-content>-->

                        <!--<md-card-actions>-->
                            <!--<md-button type="button" @click.native="register">Cadastrar</md-button>-->
                            <!--<md-button type="submit" class="md-raised md-primary">Entrar</md-button>-->
                        <!--</md-card-actions>-->
                    <!--</form>-->
                <!--</md-card>-->
            <!--</md-layout>-->
        <!--</md-layout>-->
        <!--<md-snackbar md-position="bottom center" ref="snackbar" md-duration="4000">-->
            <!--<span>{{ error }}</span>-->
            <!--<md-button class="md-accent" md-theme="light-blue" @click.native="$refs.snackbar.close()">Ok-->
            <!--</md-button>-->
        <!--</md-snackbar>-->
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

    import auth from './login/index'
    import {router} from '../main'

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
                auth.login(this, this.credentials, 'home')
            }
        },
        name: 'login-page'
    }
</script>