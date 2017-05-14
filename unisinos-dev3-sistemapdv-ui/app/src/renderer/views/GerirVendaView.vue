<template>
    <div>
        <navbar title="Gerir Venda" previousPage="/iniciar-venda"></navbar>
        <md-layout md-flex class="padding">
            <md-table-card class="fill">
                <md-toolbar>
                    <h1 class="md-title">Itens</h1>
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
                        <md-table-row v-for="p in produtos">
                            <md-table-cell>{{p.descricao}}</md-table-cell>
                            <md-table-cell md-numeric>
                                <md-input-container>
                                    <md-input type="number" placeholder="Quantidade" v-model="p.quantidade" :disabled="p.quantidade == 0">
                                    </md-input>
                                </md-input-container>
                            </md-table-cell>
                            <md-table-cell md-numeric>
                                <md-input-container>
                                    <md-input type="number" placeholder="% Desconto" v-model="p.percentualDesconto">
                                    </md-input>
                                </md-input-container>
                            </md-table-cell>
                        </md-table-row>
                    </md-table-body>
                </md-table>
                <md-button class="md-raised md-primary" @click.native="concretizar()">Concretizar Venda</md-button>
            </md-table-card>
        </md-layout>
        <md-dialog ref="dialog3">
            <md-dialog-content>
                <span v-html="alert.content"></span>
            </md-dialog-content>
            <md-dialog-actions>
                <md-button class="md-primary" @click.native="$refs['dialog3'].close()">{{alert.ok}}</md-button>
            </md-dialog-actions>
        </md-dialog>
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
                alert: { content: "", ok: "Close" },
                title: 'Gerir Venda',
                produtos: null,
                snackMessage: ""
            }
        },
        methods: {
            concretizar() {

                let percentuaisValidos = this.produtos.reduce((valido, produto) => valido && (produto.percentualDesconto <= 100 && produto.percentualDesconto >= 0), true);

                if (!percentuaisValidos) {
                    this.alert.content = "O percentual de desconto deve ser um inteiro entre 0 e 100";
                    this.$refs["dialog3"].open();
                    return;
                }

                this.$http.get(cfg.get('apiUrl') + "/produtos/all").then(
                    response => {

                        this.alert.content = "";
                        this.produtos.forEach(product => {
                            let productInStock = response.data.filter(item => item.id == product.id)[0];

                            if (productInStock != undefined) {
                                if (productInStock.estoque - product.quantidade < 0)
                                    this.alert.content += "Só existem " + productInStock.estoque + " unidades do produto " + productInStock.descricao + " em estoque<br/>";

                                if (productInStock.estoque == 0)
                                    product.quantidade = 0;
                            }
                        });


                        if (this.alert.content != "") {
                            this.$refs["dialog3"].open();
                            return;
                        }

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
                    },
                    response => {
                        this.snackMessage = "Erro ao obter estoque.";
                        this.$refs.snackbar.open();
                    }
                );
            }
        },
        mounted() {
            // TODO: Agrupar produtos semelhantes e ajustar as quantidades
            this.produtos = this.$route.query.produtosSelecionados
                .map(produto => {
                    produto.quantidade = 1;
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