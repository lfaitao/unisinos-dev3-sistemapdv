<template>
    <md-layout md-flex="100">
        <label for="produto-select">Produto</label>
        <md-select id="produto" name="produto-select" placeholder="Selecione..." v-model="produtoId">
            <md-option 
                v-for="p in produtos" 
                v-bind:value="p.id">
                {{p.descricao}}
            </md-option>
        </md-select>
    </md-layout>
</template>

<script>
    export default {
        name: 'produto-select',
        data() { 
            return {
                produtos: [],
                produtoId: null
            }
        },
        mounted(){
            this.$http.get('http://localhost:8080/produtos/all' ).then(
                response => { 
                    this.produtos = response.data;
                }
            );
        }
    }
</script>

<style scoped>

</style>
