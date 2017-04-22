<template>
    <div>
        <navbar title="Gerir Acessos" previousPage="/home"></navbar>

        <md-layout md-flex>
            <md-tabs>
            
            <md-tab id="usuarios" md-label="Usuários">
               <gerirUsuarios />
            </md-tab>

             <md-tab id="permissoes" md-label="Permissões">
                <gerirPermissoes />
            </md-tab>

            <md-tab id="telas" md-label="Telas">
                <gerirTelas />
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
        }
    }
</script>

<style scoped>

</style>