import Vue from 'vue'
import Router from 'vue-router'
//import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: () => import("@/components/HelloWorld")
    },
    {
      path: '/index',
      name: 'login',
      component: () => import("@/components/login.vue")
    },
    {
      path: '/main',
      name: 'main',
      component: () => import("@/components/main.vue"),
      children: [
        {
          path: 'login',
          name: 'login',
          component: () => import("@/components/login.vue")
        }
      ]
        
        
      },
    /*{
      path: '/*',
      redirect:'/',
    }*/
  ]
})
