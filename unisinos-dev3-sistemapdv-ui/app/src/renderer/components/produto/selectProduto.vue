<template>
    <md-layout md-flex="100">
        <md-input-container>
            <label for="produto-select">Produto</label>
            <md-select id="produto" name="produto-select" placeholder="Selecione..." 
                ref="selecionado"
                :multiple="multiple"
                :value="value ? value.id : null"
                @input="selected()">
                <md-option 
                    v-for="p in produtos" 
                    v-bind:value="p.id">
                    {{p.descricao}}
                </md-option>
            </md-select>
        </md-input-container>
    </md-layout>
</template>

<script>
    import Config from 'electron-config'
    const cfg = new Config()
    const url = cfg.get('apiUrl') + '/produtos/all'
    
    export default {
        name: 'select-produto',
        props: ['value', 'multiple'],
        data() { 
            return {
                produtos: []
            }
        },
         watch: {
            value(produto) {

                var newValue = null;

                if(produto && produto.id){
                    newValue = produto.id;
                }

                this.$refs.selecionado.changeValue(newValue);
                this.$emit('input', produto);
            }
        },
        methods:{
            selected() {
                const produto = this.produtos.find(p => { 
                    return p.id ===  this.$refs.selecionado.selectedValue; 
                });

                this.$emit('input',produto);
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
