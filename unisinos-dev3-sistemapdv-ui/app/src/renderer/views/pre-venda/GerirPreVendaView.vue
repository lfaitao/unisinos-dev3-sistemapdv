<template>
    <div>
        <navbar title="Gerir Pré-venda" previousPage="/home"></navbar>
        
        <!-- filtros pesquisa -->
        <md-layout md-flex class="padding">

            <md-card class="fill">
                <md-card-content>

                    <md-layout md-flex="33">
                        <select-cliente v-model="filtro.cliente" />
                    </md-layout>

                    <md-layout md-flex="33">
                        <select-produto v-model="filtro.produto" ref="selectProduto" />
                    </md-layout>

                    <md-layout md-flex="33">
                    </md-layout>

                    <!-- ações -->
                    <md-layout class="padding">
                        <md-button class="md-raised md-primary" @click.native="buscar()">
                            <md-icon>search</md-icon>
                            Buscar
                        </md-button>
                        <md-button id="cad" class="md-raised" @click.native="limpar()">
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
                                <md-table-head>Cliente</md-table-head>
                                <md-table-head>Produto - Quantidade</md-table-head>
                                <md-table-head>Desconto</md-table-head>
                                <md-table-head>Sub-total</md-table-head>
                                <md-table-head>Editar</md-table-head>
                                <md-table-head>Excluir</md-table-head>
                            </md-table-row>
                        </md-table-header>

                        <md-table-body>
                            <md-table-row v-for="p in itens">
                                <md-table-cell>{{p.id}}</md-table-cell>
                                <md-table-cell>{{p.cliente.nome}}</md-table-cell>
                                <md-table-cell>
                                    <span v-for="prod in p.preVendaProdutos">{{prod.produto.descricao + " - " + prod.quantidade}}<br/></span>
                                </md-table-cell>
                                <md-table-cell>{{p.percentualDesconto}}%</md-table-cell>
                                <md-table-cell>{{p.subTotal}}</md-table-cell>
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
            <md-dialog-title>Pré-Venda</md-dialog-title>
            <md-dialog-content>
                <form v-if="currentItem">
                
                    <select-cliente v-model="currentItem.cliente" />

                    <h3>Produtos</h3>
                    
                    <md-card v-for="pvp in currentItem.preVendaProdutos">
                        <md-card-content>
                            <select-produto v-model="pvp.produto" />
                            <md-input-container>
                                <label>Quantidade</label>
                                <md-input type="number" placeholder="Quantidade" v-model="pvp.quantidade"></md-input>
                            </md-input-container>
                        </md-card-content>
                            <md-card-actions>
                            <md-button @click.native="removerProdutoPrevenda(pvp)">
                                Excluir
                            </md-button>
                        </md-card-actions>
                    </md-card>

                    <md-button id="adicionarProduto" class="md-icon-button md-raised md-primary" @click.native="adicionarProdutoPrevenda()">
                        <md-icon>add</md-icon>
                    </md-button>
                </form>
            </md-dialog-content>

            <md-dialog-actions v-if="currentItem">
                <md-layout md-align="start">
                    <md-input-container>
                        <label>Percentual Desconto</label>
                        <md-input type="number" placeholder="Percentual Desconto" v-model="currentItem.percentualDesconto"></md-input>
                     </md-input-container>
                </md-layout>
            </md-dialog-actions>
                                        
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
    import selectProduto from '../../components/produto/selectProduto'
    import selectCliente from '../../components/cliente/selectCliente'

    import Config from 'electron-config'
    const cfg = new Config()
    const url = cfg.get('apiUrl') + '/prevendas/'
    const urlProdutos = cfg.get('apiUrl') + '/produtos/all';

    export default {
        name: 'gerir-pre-venda',
        components:{
            selectProduto,
            selectCliente
        },
        data() {
            return {
                title: 'Gerir Pré-Venda',
                snackMessage: '',
                filtro:{},
                produtos:[],
                itens: [],
                currentItem : null
            }
        },
        methods: {
            buscar(){

                this.itens = [];
                
                var options = {
                    params: { 
                       clienteId : this.filtro.cliente ? this.filtro.cliente.id : null,
                       produtoId : this.filtro.produto ? this.filtro.produto.id : null
                    }
                };

                this.$http.get(url, options).then(response => {
                        this.itens = response.data;
                    } 
                );
            },
            limpar(){
                this.itens = [];
                this.filtro.cliente = null;
                this.filtro.produto = null;
            },            
             criar(){
                this.currentItem = {id:0, cliente:{}, percentualDesconto:0, preVendaProdutos:[]};
                this.$refs['dialog'].open();
            },
            editar(item){
                this.currentItem = item;
                this.$refs['dialog'].open();
            },
            excluir(item){
                this.currentItem = item;
                this.$http.delete(url + item.id).then(
                    response => {
                        var indice = this.itens.indexOf(this.currentItem);
                        if(indice > -1)
                            this.itens.splice(indice, 1);
                        this.snackMessage = "Excluído com sucesso";
                        this.$refs.snackbar.open();
                    },
                    response => {
                        this.snackMessage = "Erro excluir.";
                        this.$refs.snackbar.open();
                    } 
                );
            },
            removerProdutoPrevenda(pvp){
                const i = this.currentItem.preVendaProdutos.indexOf(pvp);

                if(i >= 0)
                    this.currentItem.preVendaProdutos.splice(i,1);
            },
            adicionarProdutoPrevenda(pvp){
                this.currentItem.preVendaProdutos.push({produto:{}, quantidade:0});
            },
            salvar() {

               if(this.currentItem.id !== 0) { // Editar
                    this.$http.put(url, this.currentItem).then(
                        response => {
                            this.snackMessage = "Atualizado com sucesso";
                            this.$refs.dialog.close();
                            this.$refs.snackbar.open();
                        },
                        response => {
                            this.snackMessage = "Erro ao atualizar.";
                            this.$refs.snackbar.open();
                        } 
                    );
                }
                else // Criar
                {
                   this.$http.post(url, this.currentItem).then(
                        response => {
                            this.itens.push(response.data);
                            this.snackMessage = "Salvo com sucesso";
                            this.$refs.dialog.close();
                            this.$refs.snackbar.open();
                        },
                        response => {
                            this.snackMessage = "Erro ao salvar";
                            this.$refs.snackbar.open();
                        } 
                    ); 
                }
            },
            close() {
                 this.$refs['dialog'].close();
            }
        },
        mounted(){
            this.$http.get(urlProdutos).then(response => { 
                    this.produtos = response.data;
                }
            );
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

    .md-theme-default.md-card, #adicionarProduto {
        margin-top: 15px;
    }
</style>
