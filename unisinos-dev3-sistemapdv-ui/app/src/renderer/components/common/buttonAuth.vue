<template>
  <md-button class="md-raised md-primary" 
        @click.native="navigate()" 
        v-if='hasPermission'>
            {{text}}
    </md-button>
</template>

<script>
    import {router} from '../../main'

    export default {
        name: 'button-auth',
        props: ['text', 'path'],
        data() { 
           return {
               hasPermission: false
           };
        },
        methods:{
           navigate() {
               if(this.path !== "")
                router.push(this.path)
           }
        },
        mounted(){
             var permissoes = JSON.parse(window.localStorage.getItem('teste'));

                if(permissoes){
                    var i; var j; var hasPermission = false;
                    
                    for(i in permissoes){

                        for(j in permissoes[i].telas){
                            
                            if(this.path === permissoes[i].telas[j].path){
                            this.hasPermission = true;
                            break;
                            }

                        }

                        if(this.hasPermission)
                        break;
                    }
                }

        }
    }
</script>
