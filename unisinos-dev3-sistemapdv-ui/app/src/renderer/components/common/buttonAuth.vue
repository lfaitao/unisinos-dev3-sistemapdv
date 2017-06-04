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
        props: ['text', 'path', 'canNavigateIf'],
        data() {
            return {
                hasPermission: false
            };
        },
        methods: {
            navigate() {
                if (this.path !== "") {
                    if (this.canNavigateIf) {
                        router.push(this.path)
                    }
                }
            }
        },
        mounted(){
            let permissoes = JSON.parse(window.localStorage.getItem('teste'));

            if (permissoes) {
                let i;
                let j;
                let hasPermission = false;

                for (i in permissoes) {
                    for (j in permissoes[i].telas) {

                        if (this.path === permissoes[i].telas[j].path) {
                            this.hasPermission = true;
                            break;
                        }
                    }
                    if (this.hasPermission) {
                        break;
                    }
                }
            }
        }
    }
</script>
