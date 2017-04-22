<template>
    <div>
        <navbar title="Gerir Acessos" previousPage="/"></navbar>

        <md-layout md-flex>
            <md-tabs>
            
            <md-tab id="usuarios" md-label="Usuarios">
               <gerirUsuarios />
            </md-tab>

             <md-tab id="credenciais" md-label="Credenciais">
                <gerirCredenciais />
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
    import gerirCredenciais from './GerirCredenciaisView'
    import gerirTelas from './GerirTelasView'
    export default {
        name: 'gerir-acessos',
        components:{
            gerirUsuarios,
            gerirCredenciais,
            gerirTelas
        }
    }
</script>

<style scoped>

</style>