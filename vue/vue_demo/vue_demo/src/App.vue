<template>
  <div id="app">
    <router-view/>
    <blogFooter/>
  </blogFooter/>
  </div>
</template>

<script>
import blogFooter from './components/OpenAdminFooter'
export default {
  components:{
    blogFooter
  },
  name: 'App'
}
</script>

<style lang="scss">
@import "./assets/css/main.css";
@import "./assets/css/color-dark.css";     /*深色主题*/
@import "~bootstrap/scss/bootstrap";
</style>
