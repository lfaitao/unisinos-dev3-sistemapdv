<template>
    <div >
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
      <md-table-row v-for="(row, rowIndex) in produtos" :key="rowIndex" :md-item="row" >
        <md-table-cell v-for="(column, columnIndex) in row" :key="columnIndex" :md-numeric="columnIndex !== 'descricao'">
            <md-input-container v-if="columnIndex === 'quantidade'">
                            <md-input
                          type="number"                        
                  placeholder="Quantidade"
                  :name="'type' + columnIndex"
                  :id="'type' + columnIndex"
                  v-model="produtos[rowIndex].quantidade"
                  v-if="columnIndex === 'quantidade'">
                </md-input>
          </md-input-container v-if="columnIndex === 'quantidade'">

                      <md-input-container v-if="columnIndex === 'percentualDesconto'">
                            <md-input
                          type="number"
                  placeholder="% Desconto"
                  :name="'type' + columnIndex"
                  :id="'type' + columnIndex"
                  v-model="produtos[rowIndex].percentualDesconto"
                  v-if="columnIndex === 'percentualDesconto'">
                </md-input>
          </md-input-container v-if="columnIndex === 'percentualDesconto'">

          <span v-if="columnIndex === 'descricao'">{{ column }}</span>
        </md-table-cell>
    
        </md-table-row>
    </md-table-body>
  </md-table>
  <md-button class="md-raised md-primary" @click.native="goTo('home-page')">Concretizar Venda</md-button>
</md-table-card>
         </md-layout>
    </div>
</template>

<script>
import {router} from '../main'

  export default {
      data() {
          return {
              title: 'Gerir Venda',
              produtos: null
          }
      },
      methods: {
          goTo(route) {
              router.push(route)
          },
          onSort(data){

          },
          onPagination(data){

          }          
      },
      mounted() {
          debugger;
          this.produtos=this.$route.query.produtosSelecionados
            .map(produto => { return { descricao: produto.descricao, quantidade: 0, percentualDesconto: 0 } });
          console.log(this.produtos) ;
      },
      name: 'gerir-venda'
  }
</script>

<style scoped>
    .fill {
        width:100%;
    }
    .padding {
        padding: 16px;
    }
input{
  text-align:right;
}

    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
    /* display: none; <- Crashes Chrome on hover */
    -webkit-appearance: none;
    margin: 0; /* <-- Apparently some margin are still there even though it's hidden */
}

</style>
