<template>
    <div>
    <!-- 左侧导航栏 -->
    <el-row>
        <v-topbar></v-topbar>
    </el-row>

     <el-row>
         <el-col :xs="4" :sm="4" :md="4" :lg="4">
            <v-sidebar @change="sideChange"></v-sidebar>
         </el-col>

         <el-col :xs="20" :sm="20" :md="20" :lg="20">
          <div>
            <div style="border: 1px solid #A6A6A6; border-radius:6px; padding:5px; margin:2px; background-color: white">
              <el-breadcrumb separator="/">
                <el-breadcrumb-item v-for="item in breadcrumbItems" key="index">{{item}}</el-breadcrumb-item>
              </el-breadcrumb>
            </div>
          </div>

          <div style="margin-top:10px">
            <router-view></router-view>
          </div>
        </el-col>
         
         </el-row>

     <el-row>
         <v-footbar></v-footbar>
    </el-row>

    </div>
</template>
<script>

import sidebar from '@/components/sidebar'
import topbar from '@/components/topbar'
import footbar from '@/components/footbar'

export default {
    components:{
        'v-topbar':topbar,
        'v-footbar':footbar,
        'v-sidebar':sidebar
    },
    name: "vmain",
    data(){
        return {
            breadcrumbItems: ['导航一']
        }
    },
    created(){
        this.showMain();
    },
    methods:{
        showMain(){
            this.$router.push("/main/login");
        },
        sideChange(bar){
            this.breadcrumbItems = [bar];
        }
    }
}
</script>
<style scoped>

</style>