<template>
    
          <div>
            <el-menu default-active="1" class="el-menu-vertical-demo" style="min-height:900px" @select="handleSelect">
              <el-menu-item index="1"><i class="el-icon-message"></i>导航一</el-menu-item>
              <el-menu-item index="2"><i class="el-icon-menu"></i>导航二</el-menu-item>
              <el-menu-item index="3"><i class="el-icon-setting"></i>导航三</el-menu-item>
            </el-menu>
          </div>
        


      
    </div>
  </div>

</template>
<script>
export default {
    name: "sidebar",
    data(){
        return {
            msg: "welcome login",
        }
    },
    methods:{
        handleSelect(key, keyPath){
        switch(key){
          case '1':
            this.$router.push('login');
            this.$emit("change","导航一");
            break;
          case '2':
            this.$router.push('login');
             this.$emit("change","导航二");
            break;
          case '3':
            this.$router.push('login');
             this.$emit("change","导航三");
            break;
        }
      }

    }
}
</script>
<style scoped>

</style>