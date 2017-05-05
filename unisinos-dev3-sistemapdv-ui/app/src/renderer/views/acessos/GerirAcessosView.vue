<template>
    <div>
        <navbar title="Gerir Acessos" previousPage="/home"></navbar>

        <md-layout md-flex>
            <md-tabs @change="tabChange">
            
            <md-tab id="usuarios" md-label="Usuários">
               <gerirUsuarios ref="usuarios" />
            </md-tab>

             <md-tab id="permissoes" md-label="Permissões">
                <gerirPermissoes ref="permissoes" />
            </md-tab>

            <md-tab id="telas" md-label="Telas">
                <gerirTelas ref="telas" />
            </md-tab>
            </md-tabs>
        </md-layout>
    </div>
</template>

<script>
    import gerirUsuarios from './GerirUsuariosView'
    import gerirPermissoes from './GerirPermissoesView'
    import gerirTelas from './GerirTelasView'
    export default {
        name: 'gerir-acessos',
        components:{
            gerirUsuarios,
            gerirPermissoes,
            gerirTelas
        },
        methods: {
            tabChange(valor){
                switch(valor){

                    case 0:
                        this.$refs.usuarios.loadData();
                    break;

                    case 1:
                        this.$refs.permissoes.loadData();
                    break;

                    case 2:
                        this.$refs.telas.loadData();
                    break;

                }
                
            }
        }
    }
</script>

<style scoped>

</style>