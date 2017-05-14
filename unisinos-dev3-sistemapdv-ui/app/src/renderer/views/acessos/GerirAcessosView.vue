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
            <md-icon>{{response === 0 ? "sync":"sync_problem"}}</md-icon>
        </md-layout>
    </div>
</template>

<script>
    import Config from 'electron-config'
    const cfg = new Config()
    import gerirUsuarios from './GerirUsuariosView'
    import gerirPermissoes from './GerirPermissoesView'
    import gerirTelas from './GerirTelasView'
    export default {
        data() {
            return {
            response: 2
            }
        },
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
                
            },

            sync(){
                this.$http.get(cfg.get('apiUrl') + '/sync').then(response => {
                        console.log("sync");
                        this.response = response.data;              
                    })
            }
        },
          mounted() {
                var  self = this;

                var interval = setInterval(function () { 
                    self.sync();
                }, 6000);
        }

    }
</script>

<style scoped>

</style>