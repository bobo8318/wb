<template>
    <div>
         <Header title="我的影院"></Header>
        我的影院
        <TabBar></TabBar>
    </div>
</template>

<script>

import Header from '@/components/header'
import TabBar from '@/components/tabbar'

export default {
    name: 'cinema',
    components: {Header, TabBar}
}
</script>

<style>

</style>