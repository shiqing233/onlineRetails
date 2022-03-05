import Vue from 'vue'
import Router from 'vue-router'
import Me from '@/components/Me'
import Info from '@/components/Info'
import Address from '@/components/Address'
import All from '@/components/All'
import NoPay from '@/components/NoPay'
import NoReceive from '@/components/NoReceive'
import NoComment from '@/components/NoComment'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      component:Me,
    },
    {
      path:'/me',
      component:Me,
      children:[
        {
          path:'/me/info',
          component:Info,
        },
        {
          path:'/me/address',
          component:Address,
        },
        {
          path:'/me/all',
          component:All,
        },
        {
          path:'/me/nopay',
          component:NoPay,
        },
        {
          path:'/me/noreceive',
          component:NoReceive,
        },
        {
          path:'/me/nocomment',
          component:NoComment,
        }
      ]
    }
  ]
})
