<template>
    <div>
        <navbar title="Gerir Venda" previousPage="/iniciar-venda"></navbar>
        <md-layout md-flex class="padding">
            <md-table-card class="fill">
                <md-toolbar>
                    <h1 class="md-title">Nutrition</h1>
                </md-toolbar>

                <md-table md-sort="descricao">
                    <md-table-header>
                        <md-table-row>
                            <md-table-head md-sort-by="descricao">Decrição</md-table-head>
                            <md-table-head md-sort-by="quantidade" md-numeric>Quantidade</md-table-head>
                            <md-table-head md-sort-by="percentualDesconto" md-numeric>% de Desconto</md-table-head>
                        </md-table-row>
                    </md-table-header>

                    <md-table-body>
                        <md-table-row v-for="(row, rowIndex) in produtos" :key="rowIndex" :md-item="row">
                            <md-table-cell v-for="(column, columnIndex) in row" :key="columnIndex" :md-numeric="columnIndex !== 'descricao'" v-if="columnIndex === 'percentualDesconto' || columnIndex === 'descricao' || columnIndex === 'quantidade'">
                                <md-input-container v-if="columnIndex === 'quantidade'">
                                    <md-input type="number" placeholder="Quantidade" :name="'type' + columnIndex" :id="'type' + columnIndex" v-model="produtos[rowIndex].quantidade"
                                        v-if="columnIndex === 'quantidade'">
                                    </md-input>
                                </md-input-container v-if="columnIndex === 'quantidade'">

                                <md-input-container v-if="columnIndex === 'percentualDesconto'">
                                    <md-input type="number" placeholder="% Desconto" :name="'type' + columnIndex" :id="'type' + columnIndex" v-model="produtos[rowIndex].percentualDesconto"
                                        v-if="columnIndex === 'percentualDesconto'">
                                    </md-input>
                                </md-input-container v-if="columnIndex === 'percentualDesconto'">

                                <span v-if="columnIndex === 'descricao'">{{ column }}</span>
                            </md-table-cell>

                        </md-table-row>
                    </md-table-body>
                </md-table>
                <md-button class="md-raised md-primary" @click.native="concretizar()">Concretizar Venda</md-button>
            </md-table-card>
        </md-layout>
        <md-snackbar ref="snackbar" :md-duration="3000">
            <span>{{snackMessage}}</span>
        </md-snackbar>
    </div>
</template>

<script>
    import { router } from '../main'
    import Config from 'electron-config'
    const cfg = new Config()
    const url = cfg.get('apiUrl') + '/vendas/'

    export default {
        data() {
            return {
                title: 'Gerir Venda',
                produtos: null,
                snackMessage: ""
            }
        },
        methods: {
            concretizar() {
                var preVendas = new Set(this.produtos.map(prod => {
                        let tmp = prod.preVenda;
                        delete prod.preVenda;
                        delete tmp.produtos;
                        return tmp;                
                    }), (a, b) => a.id === b.id, (object) => object.id);
                
                var venda = {
                    produtos: this.produtos,
                    preVendas: Array.from(preVendas),
                    vendedor: null,
                    valorTotal: this.produtos.reduce((totalVenda, produto) => {
                        let total = produto.valor * produto.quantidade;
                        let totalComDesconto = total - (total * produto.percentualDesconto / 100);
                        return totalVenda += totalComDesconto;
                    }, 0)
                };
                debugger;
                this.$http.post(url, venda).then(
                    response => {
                        this.snackMessage = "Venda concretizada com sucesso.";
                        this.$refs.snackbar.open();
                        setTimeout(() => { router.push('/home') }, 3000)
                    },
                    response => {
                        this.snackMessage = "Erro ao concretizar venda.";
                        this.$refs.snackbar.open();
                    }
                );
            }
        },
        mounted() {
            this.produtos = this.$route.query.produtosSelecionados
                .map(produto => {
                    produto.quantidade = 0;
                    produto.percentualDesconto = 0;
                    return produto;
                });
        },
        name: 'gerir-venda'
    }

</script>

<style scoped>
    .fill {
        width: 100%;
    }
    
    .padding {
        padding: 16px;
    }
    
    input {
        text-align: right;
    }
    
    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        /* display: none; <- Crashes Chrome on hover */
        -webkit-appearance: none;
        margin: 0;
        /* <-- Apparently some margin are still there even though it's hidden */
    }
</style>