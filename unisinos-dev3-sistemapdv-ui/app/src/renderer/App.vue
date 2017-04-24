<template>
    <div id="#app">
        <router-view></router-view>
    </div>
</template>

<script>
    import auth from './views/login/index'

    export default {}
</script>

<style>
    @import url(https://fonts.googleapis.com/css?family=Lato:300);

    body {
        background-color: #EEE !important;
    }

    h1 {
        font-family: Lato, Helvetica, sans-serif;
    }
</style>
