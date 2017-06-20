<template>
    <md-layout md-flex="100">
        <md-input-container>
            <label for="cliente-select">Cliente</label>
            <md-select name="cliente-select" placeholder="Selecione..." 
                ref="selecionado"
                :value="value ? value.id : null"
                @input="selected()">
                <md-option 
                    v-for="c in clientes" 
                    v-bind:value="c.id">
                        {{c.nome + ' - ' + c.cpf}}
                </md-option>
            </md-select>
        </md-input-container>
    </md-layout>
</template>

<script>
    import Config from 'electron-config'
    const cfg = new Config()
    const url = cfg.get('apiUrl') + '/clientes/all'
    
    export default {
        name: 'select-cliente',
        props: ['value'],
        data() { 
            return {
                clientes: []
            }
        },
         watch: {
            value(cliente) {

                var newValue = null;

                if(cliente && cliente.id){
                    newValue = cliente.id;
                }

                this.$refs.selecionado.changeValue(newValue);
                this.$emit('input', cliente);
            }
        },
        methods:{
            selected() {
                const cliente = this.clientes.find(c => { 
                    return c.id ===  this.$refs.selecionado.selectedValue; 
                });

                this.$emit('input', cliente);
            }
        },
        mounted(){
            this.$http.get(url).then(response => { 
                this.clientes = response.data;
            });
        }
    }
</script>

<style scoped>

</style>
