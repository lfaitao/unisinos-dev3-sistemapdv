<template>
<div>
        <!-- filtros pesquisa -->
        <md-layout md-flex class="padding">

            <md-card class="fill">
                <md-card-content>

                    <md-layout md-flex="33">
                        <md-input-container>
                            <label>Nome</label>
                            <md-input maxlength="300" v-model="filtro.nome"></md-input>
                        </md-input-container>
                    </md-layout>

                    <md-layout md-flex="33">
                    </md-layout>

                    <md-layout md-flex="33">
                        <label for="path-select">Telas</label>
                        <md-select multiple name="path-select" id="path-select" v-model="filtro.idsTelas" placeholder="Selecione...">
                            <md-option v-for="p in telas" :value="p.id">{{p.nome}}</md-option>
                        </md-select>
                    </md-layout>

                    <!-- ações -->
                    <md-layout class="padding">
                        <md-button class="md-raised md-primary" @click.native="buscar()">
                            <md-icon>search</md-icon>
                            Buscar
                        </md-button>
                        <md-button class="md-raised" @click.native="limpar()">
                            <md-icon>clear</md-icon>
                            Limpar
                        </md-button>
                        <md-button id="cad" class="md-raised" @click.native="criar()">
                            <md-icon>add</md-icon>
                            Criar
                        </md-button>
                    </md-layout>

                </md-card-content>
            </md-card>

        </md-layout>
        <!-- resultado pesquisa -->
        <md-layout class="padding" v-if="itens.length > 0">
            <md-card class="fill">
                <md-card-content>
                    <md-table class="fill">
                        <md-table-header>
                            <md-table-row>
                                <md-table-head>id</md-table-head>
                                <md-table-head>Nome</md-table-head>
                                <md-table-head>Telas</md-table-head>
                                <md-table-head>Editar</md-table-head>
                                <md-table-head>Deletar</md-table-head>
                            </md-table-row>
                        </md-table-header>

                        <md-table-body>
                            <md-table-row v-for="p in itens">
                                <md-table-cell>{{p.id}}</md-table-cell>
                                <md-table-cell>{{p.nome}}</md-table-cell>
                                <md-table-cell>
                                    <span v-for="t in p.telas">{{t.nome}}<br/></span>
                                </md-table-cell>
                                <md-table-cell>
                                    <md-button class="md-icon-button md-raised md-primary" @click.native="editar(p)">
                                        <md-icon>edit</md-icon>
                                    </md-button>
                                </md-table-cell>
                                <md-table-cell>
                                    <md-button class="md-icon-button md-raised md-accent" @click.native="excluir(p)">
                                        <md-icon>delete</md-icon>
                                    </md-button>
                                </md-table-cell>
                            </md-table-row>
                        </md-table-body>
                        </md-table>

                    </md-card-content>
            </md-card>
        </md-layout>

        <!-- dialog criar / editar -->
        <md-dialog ref="dialog">
            <md-dialog-title>{{currentItem && currentItem.id === 0 ? "Novo item" : "Editar item "}}</md-dialog-title>
            <md-dialog-content>
                <form v-if="currentItem">
                    <md-input-container>
                        <label>Nome</label>
                        <md-input maxlength="300" v-model="currentItem.nome"></md-input>
                    </md-input-container>
                    <md-layout md-flex="33">
                        <label for="path-select">Telas</label>
                        <md-select multiple name="path-select" id="path-select" v-model="currentItem.telas" placeholder="Selecione...">
                            <md-option v-for="p in telas" :value="p">{{p.nome}}</md-option>
                        </md-select>
                    </md-layout>
                </form>
            </md-dialog-content>

            <md-dialog-actions>
                <md-button class="md-primary" @click.native="salvar()">Salvar</md-button>
                <md-button class="md-primary" @click.native="close()">Cancelar</md-button>
            </md-dialog-actions>
        </md-dialog>

        <md-snackbar ref="snackbar" :md-duration="3000">
            <span>{{snackMessage}}</span>
            <md-button class="md-accent" @click.native="$refs.snackbar.close()">Ok</md-button>
        </md-snackbar>    
    </div>
</template>

<script>
    import routes from '../../routes'
    import Config from 'electron-config'
    const cfg = new Config()
    const url = cfg.get('apiUrl') + '/permissoes/'
    const url_telas = cfg.get('apiUrl') + '/telas/'
    
    export default {
        name: 'gerir-permissoes',
        data() {
            return {
                title: 'Gerir Permissões',
                snackMessage: '',
                itens:[],
                currentItem:null,
                filtro:{nome:"", idsTelas:[]},
                telas:[]
            }
        },
        methods: {
            buscar(){

                var options = {
                    params: this.filtro
                };

                this.$http.get(url, options).then(
                    response => {
                        this.itens = response.data;
                    } 
                );
            },
            criar(){
                this.currentItem = {id:0, nome:"", telas: []};
                this.$refs['dialog'].open();
            },
            limpar(){
                this.filtro = {nome:""};
                this.itens = [];
                this.telas = [];
            },
            editar(usuario){
                this.currentItem = usuario;
                this.$refs['dialog'].open();
            },
            excluir(usuario){
                this.currentItem = usuario;
                this.$http.delete(url + usuario.id).then(
                    response => {
                        var indice = this.itens.indexOf(this.currentItem);
                        if(indice > -1)
                            this.itens.splice(indice, 1);
                        this.snackMessage = "Excluído com sucesso";
                        this.$refs.snackbar.open();
                    },
                    response => {
                        this.snackMessage = "Não foi possivel excluir.";
                        this.$refs.snackbar.open();
                    } 
                );
            },
            salvar() {
               var body = JSON.stringify(this.currentItem);
               if(this.currentItem.id !== 0) { // Editar
                    this.$http.put(url, body).then(
                        response => {
                            this.snackMessage = "Atualizado com sucesso";
                            this.$refs['dialog'].close();
                            this.$refs.snackbar.open();
                        },
                        response => {
                            this.snackMessage = "Erro ao atualizar.";
                            this.$refs.snackbar.open();
                        } 
                    );
                    limpar();
                }
                else // Criar
                {
                   this.$http.post(url, body).then(
                        response => {
                            this.itens.push(response.data);
                            this.snackMessage = "Salvo com sucesso";
                            this.$refs['dialog'].close();
                            this.$refs.snackbar.open();
                        },
                        response => {
                            this.snackMessage = "Erro ao salvar";
                            this.$refs.snackbar.open();
                        } 
                    ); 
                    limpar();
                }
            },
            close() {
                 this.$refs['dialog'].close();
            },
            
            loadData(){
                this.$http.get(url_telas + "all")
                .then(
                    response => {
                        this.telas = response.data;
                    } 
                );
            }
        },
        mounted() {
            this.loadData(); 
        }
    }
</script>

<style scoped>
    .fill {
        width:100%;
    }
    .padding {
        padding: 16px;
    }
    .md-table .md-table-cell .md-button .md-icon {
        margin: auto;
        color: white;
    }
</style>