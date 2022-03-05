<template>
<el-container>
  <el-header class="me-header" height="80px">
    <el-row>
      <el-col :span="2" :offset="1" ><el-button type="primary" @click="shop(user.userId)">店铺管理</el-button></el-col>
      <el-col :span="2" :offset="1" ><el-button type="primary" @click="message(user)">消息</el-button></el-col>
      <el-col :span="2" :offset="1" ><el-button type="primary" @click="shoppingCart()">购物车</el-button></el-col>
      <el-col :span="5" :offset="1" >
         <el-autocomplete
              v-model="input"
              :fetch-suggestions="querySearch"
              placeholder="在这会找到你想要的"
              :trigger-on-focus="false"
         ></el-autocomplete></el-col>
      <el-col :span="1"><el-button icon="el-icon-search" type="primary" @click="search()"></el-button></el-col>
      <el-col :span="6" style="font-size: 16px; letter-spacing: 0px; text-align: right;">你好!  {{user.userName}}</el-col>
      <el-avatar v-if="user.userPhotoLink"
                        :src="getUrl(user.userPhotoLink)"
                        :size="60"
                        shape="circle"
                        fit="cover"
                        class="home-avatar">
                        </el-avatar>
    </el-row>
  </el-header>
  <el-main class="main">
    <el-container>
    <el-aside width="200px">
      <el-menu :router="true">
        <el-submenu index='1'>
          <template slot="title"><i class="el-icon-user-solid"></i>账户设置</template>
          <el-menu-item-group>
            <el-menu-item index="/me/info" @click="menuItem()">个人信息</el-menu-item>
            <el-menu-item index="/me/address" @click="menuItem()">收货地址</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index='2'>
          <template slot="title"><i class="el-icon-menu"></i>我的订单</template>
          <el-menu-item-group>
            <el-menu-item index="/me/all" @click="menuItem()">全部订单</el-menu-item>
            <el-menu-item index="/me/nopay" @click="menuItem()">待支付</el-menu-item>
            <el-menu-item index="/me/noreceive" @click="menuItem()">待收货</el-menu-item>
            <el-menu-item index="/me/nocomment" @click="menuItem()">待评价</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>
    <!-- 购物车part -->
    <el-main v-if="isClickShoppingCart">
      <el-container>
        <el-header class="header">
          购物车
        </el-header>
        <el-main>
          <el-row style="height: 40px; margin-bottom: 20px;">
            <el-col :span='1'><el-button size="mini" type="danger" @click="delCarts()">删除商品</el-button></el-col>
            <el-col :span='1' :offset='1'><el-button size="mini" type="success">结算商品</el-button></el-col>
          </el-row>
          <el-row style="height: 40px;">
            <el-col :span='1'><el-checkbox v-model="checked" @change="changeChecked()"></el-checkbox></el-col>
            <el-col :span='1' style="text-align: left;">全选</el-col>
            <el-col :span='8'>商品</el-col>
            <el-col :span='3'>单价</el-col>
            <el-col :span='3'>数量</el-col>
            <el-col :span='3'>操作</el-col>
          </el-row>

           <el-row class="cart" v-for="(cart,index) in carts">
             <el-col :span='1'><el-checkbox v-model="cart.checked"></el-checkbox></el-col>
             <el-col :span='2'><img :src="getUrl(cart.goodsPhotosLink)" style="width: 80px; height: 80px;"></el-col>
             <el-col :span='4' :offset='1'><span>{{cart.goodsDescription}}</span></el-col>
             <el-col :span='2'><el-row><span>{{cart.type}}</span></el-row></el-col>
             <el-col :span='3'><span>{{cart.price}}</span></el-col>
             <el-col :span='3'><span>{{cart.number}}</span></el-col>
             <el-col :span='3'><el-button @click="showGoods(cart.goodsId,user.userId)" type="text" style="margin: 0; padding: 0;">查看商品</el-button></el-col>
           </el-row>

        </el-main>
      </el-container>
    </el-main>
    <!-- 搜索商品part -->
    <el-main v-else-if="isClickSearch">
      <el-main v-if="goods">
         <el-row class="good" v-for="(good,index) in goods">
           <el-col :span='2'><img :src="getUrl(good.goodsPhotosLink)" style="width: 80px; height: 80px;"></el-col>
           <el-col :span='6' :offset='2'><span>{{good.goodsDescription}}</span></el-col>
           <el-col :span='4' :offset='2'><span>{{good.goodsName}}</span></el-col>
           <el-col :span='4' :offset='2'><el-button @click="showGoods(good.goodsId)"  type="text" style="margin: 0; padding: 0;">查看</el-button></el-col>
         </el-row>
      </el-main>
      <el-main v-if="flag">
        <el-empty description="没有找到商品 去看看其它商品吧"></el-empty>
      </el-main>
      </div>
    </el-main>
    <el-main v-else>
      <router-view></router-view>
    </el-main>
    </el-container>
  </el-main>
</el-container>

</template>

<style scoped>

  .cart,.good{
      border-top: 1px solid #c5c5c5;
      line-height: 20px;
      background: #f0f3ef;
      padding: 5px 0px;
      margin: 5px 0px ;
      clear: both;
  }
  .el-aside {
    color: #333;
  }
  .me-header {
    background: #409EFF;
    color: #fff;
    font-size: 30px;
    line-height: 70px;
    letter-spacing: 10px;
  }
  .el-container,.el-menu {
    height: 100%;
  }
  .home-avatar{
    position: absolute;
    margin: auto;
    right: 30px;
    top: 0;
    bottom: 0;
  }
  .main{
    padding-left: 100px;
    padding-right: 100px;
    background-color: #f0f3ef;
  }
  body{
    margin: 0px;
    padding: 0px;
  }

</style>

<script>
  export default {
    data(){
      return{
        user:{
            userId:null,
            userName:null,
            userPassword:null,
            userPhotoLink:null,
            phone:null
         },
        flag:false,
        input:'',
        goodNames:[],
        goods:[],
        isClickShoppingCart: false,
        isClickSearch:false,
        checked:false,
        carts:[],
        modiCarts:[]
      }
    },
    methods:{
      getUrl(userPhotoLink){
        return "http://localhost:8088/image/" + userPhotoLink;
      },
      shoppingCart(){
        this.isClickShoppingCart = true;
        this.isClickSearch = false;
      },
      menuItem(){
        this.isClickShoppingCart = false;
        this.isClickSearch = false;
      },
      search(){
        this.isClickSearch = true;
        this.isClickShoppingCart = false;
        this.ajax.request({
          url: '/goods/search',
          params: {
            input: this.input
          }
        }).then((res)=>{
            if (!res.data.code) {
                this.goods = res.data.result.goods;
                if(res.data.result.goods.length==0)
                  this.flag = true;
                this.input = '';
            }
        })
      },

      shop(){

      },

      getCarts(){
        this.ajax.request({
          url: '/carts/find',
          params: {
            userId: this.user.userId
          }
        }).then((res)=>{
            if (!res.data.code) {
                this.carts = res.data.result.carts;
            }
        })
      },
      //全选时
      changeChecked(){
        for(let index in this.carts)
          this.carts[index].checked = this.checked;
      },
      delCarts(){
        this.$confirm(`是否要删除选中商品`, '提示', {
          confirmButtonText: '确定删除',
          cancelButtonText: '我再想想',
          type: 'warning'
        }).then(() => {
            let fd = new FormData();
            for(let index in this.carts){
              if(this.carts[index].checked)
                fd.append("cartIds",this.carts[index].cartId);
            }
            this.ajax.request({
                url: '/carts/del',
                data: fd,
                method: "post"
              }).then((res)=>{
                  if (!res.data.code) {
                      for(let index in this.carts){
                        this.carts[index]=null;
                      }
                      this.getCarts();
                  }
              })
          }).catch(()=>{

          })
      },

      querySearch(queryString, cb) {
        var goodNames = this.goodNames;
        var results = queryString ? goodNames.filter(this.createFilter(queryString)) : goodNames;
        console.log(results);
        // 调用 callback 返回建议列表的数据
        cb(results);
      },
      createFilter(queryString) {
        return (goodName) => {
          return (goodName.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
      getGoodsName(){
        this.ajax.request({
            url: '/goods/name',
            method: "get"
          }).then((res)=>{
              if (!res.data.code) {
                for(let index in res.data.result.name){
                  let that = {};
                  that.value = res.data.result.name[index];
                  this.goodNames[index] = that;
                }
              }
          })
      },
      showGoods(id1,id2){
        this.$router.push({path:'/GoodsInfo',query:{goodsId: id1,userId:id2}});
      },
      shop(id){
        this.$router.push({path:'/ShopApply',query:{userId: id}});
      },
      message(tempUser){
        this.$router.push({path:'/ChatView',query:{user: tempUser}});
      }

    },
    created() {
      this.user.userId = "1627003783617";

      this.ajax.request({
        url: '/user/find.action',
        params: {
          id: this.user.userId
        }
      }).then((res)=>{
          if (!res.code) {
              this.user = res.data.result.ui;
              sessionStorage.setItem("loginAdmin",JSON.stringify(res.data.result.ui))
              this.$store.state.loginAdmin = res.data.result.ui;
              this.getCarts();
              this.getGoodsName();
          }
      })
    }
  }
</script>
