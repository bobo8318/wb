<template>
  <footer>
    <ul>
        <router-link tag="li" to="/movie">电影</router-link>
        <router-link tag="li" to="/cinema">影院</router-link>
        <router-link tag="li" to="/mine">我的</router-link>
    </ul>

  </footer>
</template>

<script>


export default {
    name: 'tabbar'
}
</script>

<style scope>

</style>