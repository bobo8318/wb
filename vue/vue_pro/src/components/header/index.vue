<template>
  <header>
      <h1>{{title}}</h1>
  </header>
</template>

<script>
export default {
    name: 'vheader',
    props:{
      title:{
        type: String,
        default: 'mm电影'
      }
    }
}
</script>

<style scope>

</style>