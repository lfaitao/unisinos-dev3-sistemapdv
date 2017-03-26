<template>
    <div style="height: 100%;">
        <router-view></router-view>
    </div>
</template>

<script>

    import auth from './auth/index'

    export default {
        data () {
            return {
                user: auth.user
            }
        }
    }
</script>

