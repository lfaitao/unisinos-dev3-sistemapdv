<template>
    <div >
        <navbar title="Gerir Venda" previousPage="/iniciar-venda"></navbar>
        <div  >
<md-table-card>
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
      <md-table-row v-for="(row, rowIndex) in nutrition" :key="rowIndex" :md-item="row" >
        <md-table-cell v-for="(column, columnIndex) in row" :key="columnIndex" :md-numeric="columnIndex !== 'descricao'">
            <md-input-container v-if="columnIndex === 'quantidade'">
                            <md-input
                          type="number"                        
                  placeholder="Quantidade"
                  :name="'type' + columnIndex"
                  :id="'type' + columnIndex"
                  v-model="nutrition[rowIndex].quantidade"
                  v-if="columnIndex === 'quantidade'">
                </md-input>
          </md-input-container v-if="columnIndex === 'quantidade'">

                      <md-input-container v-if="columnIndex === 'percentualDesconto'">
                            <md-input
                          type="number"
                  placeholder="% Desconto"
                  :name="'type' + columnIndex"
                  :id="'type' + columnIndex"
                  v-model="nutrition[rowIndex].percentualDesconto"
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
        </div>
    </div>
</template>

<script>
import {router} from '../main'

  export default {
      data() {
          return {
              title: 'Gerir Venda',
              nutrition: null
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
          
          console.log(this.$route.query.cpf) ;
          console.log(this.$route.query.preVendas) ;

          this.nutrition=this.$route.query.preVendas;
          console.log(this.nutrition) ;
      },
      name: 'gerir-venda'
  }
</script>

<style scoped>
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
