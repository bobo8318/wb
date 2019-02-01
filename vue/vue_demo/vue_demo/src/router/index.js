import Vue from 'vue'
import Router from 'vue-router'

//import loginPage from '@/components/login.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      redirect:'dashboard'
    },
    {
      path: '/login',
      name: 'loginPage',
      component: resolve => require(['../components/login.vue'], resolve),
       pathToRegexpOptions:{strict:true} // 编译正则的选项
    },
    {
      path: '/',
      component: resolve => require(['../components/common/Home.vue'], resolve),
      children:[
        {
            path: '/dashboard',
            component: resolve => require(['../components/dashboard.vue'], resolve),
            meta: { title: '系统首页' }
        },
        {
            path: '/usermanage',
            component: resolve => require(['../components/usermanage.vue'], resolve),
            meta: { title: '用户管理' }
        }
      ]
    }
  ]
})
