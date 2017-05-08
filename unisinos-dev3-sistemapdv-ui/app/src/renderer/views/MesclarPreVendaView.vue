<template>
    <div>
        <navbar title="Mesclar Pré Venda" previousPage="/iniciar-venda"></navbar>

        <md-layout md-flex class="padding">
            <md-table-card class="fill">
                <md-toolbar>
                    <h1 class="md-title">Pré-Vendas {{cliente.nome}}</h1>
                </md-toolbar>

                <md-table-alternate-header md-selected-label="selected">
                    <md-button class="md-icon-button" @click.native="goTo({path:'/gerir-venda', query: {produtosSelecionados:produtosSelecionados}})">
                        <md-icon>done</md-icon>
                    </md-button>
                </md-table-alternate-header>

                <md-table md-sort="descricao" @select="onSelect">
                    <md-table-header>
                        <md-table-row>
                            <md-table-head md-sort-by="descricao">Decrição</md-table-head>
                            <md-table-head md-sort-by="valor" md-numeric>Valor</md-table-head>
                        </md-table-row>
                    </md-table-header>

                    <md-table-body>
                        <md-table-row v-for="(row, rowIndex) in produtos" :key="rowIndex" :md-item="row" md-selection>
                            <md-table-cell v-for="(column, columnIndex) in row" :key="columnIndex" :md-numeric="columnIndex !== 'descricao'" v-if="columnIndex=='descricao' || columnIndex == 'valor'">
                                <span>{{ column }}</span>
                            </md-table-cell>
                        </md-table-row>
                    </md-table-body>
                </md-table>
            </md-table-card>
        </md-layout>
        <md-snackbar ref="snackbar" :md-duration="3000">
            <span>{{snackMessage}}</span>
            <md-button class="md-accent" @click.native="$refs.snackbar.close()">Ok</md-button>
        </md-snackbar>
    </div>
</template>

<script>
    import { router } from '../main'
    import Config from 'electron-config'
    const cfg = new Config()
    const url = cfg.get('apiUrl') + '/prevendas'
    export default {
        data() {
            return {
                title: 'Mesclar Pré Venda',
                produtosSelecionados: [],
                cliente: { nome: "" },
                snackMessage: '',
                produtos: []
            }
        },
        methods: {
            goTo(route) {
                router.push(route)
            },
            onSelect(data) {
                this.produtosSelecionados = Object.values(data);
            }
        },
        mounted() {
            this.cliente = this.$route.query.cliente;
            
            var options = {
                params: { produtoId: 0, clienteId: this.cliente.id }
            };

            this.$http.get(url, options).then(
                response => {
                    this.produtos = response.data.reduce((produtos, preVenda) => 
                        produtos.concat(preVenda.produtos.map(prod => 
                        {
                            prod.preVenda = preVenda;
                            return prod;
                        })), []);
                },
                response => {
                    this.snackMessage = "Erro ao obter pre-vendas.";
                    this.$refs.snackbar.open();
                }
            );
        },
        name: 'mesclar-pre-venda'
    }

</script>

<style scoped>
    .fill {
        width: 100%;
    }
    
    .padding {
        padding: 16px;
    }
    
    .centered_div {
        position: absolute;
        top: 22%;
        bottom: 50%;
        left: 28%;
        right: 50%;
        min-width: 600px;
        min-height: 400px;
    }
</style>