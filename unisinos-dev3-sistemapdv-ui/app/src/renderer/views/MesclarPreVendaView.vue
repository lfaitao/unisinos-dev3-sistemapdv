<template>
    <div >
        <navbar title="Mesclar Pré Venda" previousPage="/iniciar-venda"></navbar>
        <div>
        <md-table-card>
  <md-toolbar>
    <h1 class="md-title">Nutrition</h1>
  </md-toolbar>

  <md-table-alternate-header md-selected-label="selected">
    <md-button class="md-icon-button" @click.native="goTo({path:'/gerir-venda', query: {preVendas:preVendas}})">
      <md-icon>done</md-icon>
    </md-button>
  </md-table-alternate-header>

  <md-table md-sort="descricao" @select="onSelect">
    <md-table-header>
      <md-table-row>
        <md-table-head md-sort-by="descricao">Decrição</md-table-head>
        <md-table-head md-sort-by="quantidade" md-numeric>Quantidade</md-table-head>
        <md-table-head md-sort-by="percentualDesconto" md-numeric>% de Desconto</md-table-head>
      </md-table-row>
    </md-table-header>

    <md-table-body>
      <md-table-row v-for="(row, rowIndex) in nutrition" :key="rowIndex" :md-item="row" md-selection>
        <md-table-cell v-for="(column, columnIndex) in row" :key="columnIndex" :md-numeric="columnIndex !== 'descricao'">
          <span>{{ column }}</span>
        </md-table-cell>
      </md-table-row>
    </md-table-body>
  </md-table>
</md-table-card>
        </div>
    </div>
</template>

<script>
  import {router} from '../main'

  export default {
      data() {
          return {
              title: 'Mesclar Pré Venda',
              preVendas: [],
              nutrition: [
      {
        descricao: 'Frozen yogurt',
        
        quantidade: '159',
        percentualDesconto: '6.0',
      },
      {
        descricao: 'Ice cream sandwich',
        
        quantidade: '237',
        percentualDesconto: '9.0',

      },
      {
        descricao: 'Eclair',
        
        quantidade: '262',
        percentualDesconto: '16.0',

      },
      {
        descricao: 'Cupcake',
        
        quantidade: '305',
        percentualDesconto: '3.7',
  
      },
      {
        descricao: 'Gingerbread',
        
        quantidade: '356',
        percentualDesconto: '16.0',

      }
    ]
          }
      },
      methods: {
          goTo(route) {
              router.push(route)
          },
          onSelect(data){
            this.preVendas = Object.values(data);
          },
          onSort(data){

          },
          onPagination(data){

          } 
      },
      mounted() {
          console.log(this.$route.query.cpf) ;
      },
      name: 'mesclar-pre-venda'
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
