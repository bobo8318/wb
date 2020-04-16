<template>
    <div>
        <vheader title="我的电影"></vheader>
            <div id="content">
                <div class="nav">
                    <router-link tag="div" to="/movie/city">城市选择</router-link>
                    <router-link tag="div" to="/movie/commingsoon">正在热映</router-link>
                    <router-link tag="div" to="/movie/search">搜索</router-link>
                </div>
                <keep-alive>
                    <router-view></router-view>
                </keep-alive>
            </div>
        <vtabBar></vtabBar>
    </div>
</template>

<script>

import vheader from '@/components/header'
import vtabBar from '@/components/tabbar'

export default {
    name: 'movie',
    components: {vheader, vtabBar}
}
</script>

<style>

</style>