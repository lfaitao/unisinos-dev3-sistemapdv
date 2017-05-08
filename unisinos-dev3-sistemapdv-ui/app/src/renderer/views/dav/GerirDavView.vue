<template>
    <div>
        <navbar title="Gerir DAVs" previousPage="/home"></navbar>
        <!-- filtros pesquisa -->
        <md-layout md-flex class="padding">

            <md-card class="fill">
                <md-card-content>

                    <md-layout md-flex="33">
                        <md-input-container>
                            <label>Cpf</label>
                            <md-input maxlength="11" v-model="cpf"></md-input>
                        </md-input-container>
                    </md-layout>

                    <md-layout md-flex="33">
                    </md-layout>

                    <!-- ações -->
                    <md-layout class="padding">
                        <md-button class="md-raised md-primary" @click.native="buscar()">
                            <md-icon>search</md-icon>
                            Buscar
                        </md-button>

                    </md-layout>

                </md-card-content>
            </md-card>

        </md-layout>

        <!-- resultado pesquisa -->
        <md-layout class="padding" v-if="itens.length > 0">
            <md-table-card class="fill">

                <md-toolbar>
                    <h1 class="md-title">Vendas</h1>
                </md-toolbar>

                <md-table-alternate-header md-selected-label="selected">
                    <md-button class="md-icon-button" @click.native="imprimirDav()">
                        <md-icon>done</md-icon>
                    </md-button>
                </md-table-alternate-header>

                <md-table class="fill" @select="onSelect">
                    <md-table-header>
                        <md-table-row>
                            <md-table-head>Id</md-table-head>
                            <md-table-head md-numeric>Valor Total</md-table-head>
                        </md-table-row>
                    </md-table-header>

                    <md-table-body>
                        <md-table-row md-selection v-for="c in itens">
                            <md-table-cell>{{c.id}}</md-table-cell>
                            <md-table-cell md-numeric>{{c.valorTotal}}</md-table-cell>
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
    import Config from 'electron-config'
    const cfg = new Config()
    const url = cfg.get('apiUrl') + '/vendas'

    export default {
        name: 'gerir-davs',
        data() {
            return {
                title: 'Gerir DAVs',
                snackMessage: '',
                showCards: true,
                itens: [],
                currentItem: null,
                cpf: null
            }
        },
        methods: {
            buscar() {
                this.$http.get(url + "?cpfCliente=" + this.cpf, null)
                    .then(response => {
                        debugger;
                        this.itens = response.data;
                    },
                    response => {
                        debugger;
                        this.snackMessage = "Erro ao obter vendas.";
                        this.$refs.snackbar.open();
                    });
            },
            imprimirDav() {
                this.snackMessage = "Imprimindo...";
                this.$refs.snackbar.open();
            }
        },
        mounted() {
        }
    }

</script>

<style scoped>
    .fill {
        width: 100%;
    }

    .padding {
        padding: 16px;
    }

    .md-table .md-table-cell .md-button .md-icon {
        margin: auto;
        color: white;
    }
</style>