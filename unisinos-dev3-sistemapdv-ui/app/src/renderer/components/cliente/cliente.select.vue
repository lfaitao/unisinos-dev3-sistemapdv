<template>
    <md-layout md-flex="100">
        <label for="cliente-select">Cliente</label>
        <md-select id="produto" name="cliente-select" placeholder="Selecione..." v-model="clienteId">
            <md-option 
                v-for="c in clientes" 
                v-bind:value="c.id">
                {{c.nome + ' - ' + c.cpf}}
            </md-option>
        </md-select>
    </md-layout>
</template>

<script>

    import Config from 'electron-config'
    const cfg = new Config()
    const url = cfg.get('apiUrl') + '/clientes/all'
    
    export default {
        name: 'cliente-select',
        data() { 
            return {
                clientes: [],
                clienteId: null
            }
        },
        mounted(){
            this.$http.get(url).then(
                response => { 
                    this.clientes = response.data;
                }
            );
        }
    }
</script>

<style scoped>

</style>
