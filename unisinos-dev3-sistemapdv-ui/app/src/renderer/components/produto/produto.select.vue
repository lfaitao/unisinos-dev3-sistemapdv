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
    import Config from 'electron-config'
    const cfg = new Config()
    const url = cfg.get('apiUrl') + '/produtos/all'
    
    export default {
        name: 'produto-select',
        data() { 
            return {
                produtos: [],
                produtoId: null
            }
        },
        mounted(){
            this.$http.get(url).then(
                response => { 
                    this.produtos = response.data;
                }
            );
        }
    }
</script>

<style scoped>

</style>
