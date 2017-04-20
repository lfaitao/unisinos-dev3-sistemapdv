<template>
    <div >
        <navbar title="Gerir Venda" previousPage="/iniciar-venda"></navbar>
        <div  >
<md-table-card style="background-color:white">
  <md-toolbar  style="background-color:white">
    <h1 class="md-title">Produtos</h1>
<!--    <md-button class="md-icon-button">
      <md-icon>filter_list</md-icon>
    </md-button>

    <md-button class="md-icon-button">
      <md-icon>search</md-icon>
    </md-button>-->
  </md-toolbar>
<!--É exibido um formulário com uma grid de produtos, com ‘descrição’, ‘quantidade’, ‘percentual de desconto’.-->
  <md-table md-sort="dessert" md-sort-type="desc" @select="onSelect" @sort="onSort">
    <md-table-header>
      <md-table-row>
        <md-table-head md-sort-by="descricao">Descrição</md-table-head>
        <md-table-head md-sort-by="quantidade" md-numeric>Quantidade</md-table-head>
        <md-table-head md-sort-by="percentualDesconto" md-numeric>Percentual de desconto</md-table-head>
      </md-table-row>
    </md-table-header>

    <md-table-body>
      <md-table-row v-for="(row, rowIndex) in nutrition" :key="rowIndex" :md-item="row" md-auto-select md-selection>
        <md-table-cell v-for="(column, columnIndex) in row" :key="columnIndex" :md-numeric="columnIndex !== 'descricao'" v-if="columnIndex !== 'type'">
          {{ column }}
        </md-table-cell>
      </md-table-row>
    </md-table-body>
  </md-table>

  <md-table-pagination
    md-size="5"
    md-total="10"
    md-page="1"
    md-label="Rows"
    md-separator="of"
    :md-page-options="[5, 10, 25, 50]"
    @pagination="onPagination"></md-table-pagination>
</md-table-card>
<md-button class="md-raised md-primary" @click.native="goTo('/mesclar-pre-venda')">Mesclar Pré-Vendas</md-button>
        </div>
    </div>
</template>

<script>
import {router} from '../main'

  export default {
      data() {
          return {
              title: 'Gerir Venda',
              nutrition: [
      {
        descricao: 'Meia',
        quantidade: '2',
        percentualDesconto: '0',
      },
            {
        descricao: 'Prancha Funboard 7.6',
        quantidade: '1',
        percentualDesconto: '6.0',
      },
            {
        descricao: 'Parafina',
        quantidade: '5',
        percentualDesconto: '0',
      }
    ]
          }
      },
      methods: {
          goTo(route) {
              router.push(route)
          },
      },
      mounted() {
          console.log("irairiairira") ;
          console.log(this.$route.query.cpf) ;
      },
      name: 'gerir-venda'
  }
</script>

<style scoped>
    
    .centered_div {
        position: absolute;
        top:  22%;
        bottom:  50%;
        left: 28%;
        right: 50%;
        min-width:600px;
        min-height:400px;
    }
</style>
