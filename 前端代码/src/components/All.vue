<template>
  <div>
  <el-container>
    <el-header>
      全部订单
    </el-header>
    <el-main>
      <el-row style="height: 40px;">
        <el-col :span='9' style="text-align: left;">商品</el-col>
        <el-col :span='3'>单价</el-col>
        <el-col :span='3'>数量</el-col>
        <el-col :span='3'>操作</el-col>
      </el-row>
       <el-row class="order" v-for="(order,index) in orders">
         <el-col :span='2'><img :src="getUrl(order.goodsPhotosLink)" style="width: 80px; height: 80px;"></el-col>
         <el-col :span='4' :offset='1'><span>{{order.goodsDescription}}</span></el-col>
         <el-col :span='2'><span>{{order.type}}</span></el-col>
         <el-col :span='3'><span>{{order.price}}</span></el-col>
         <el-col :span='3'><span>{{order.number}}</span></el-col>
         <el-col :span='3'><el-button type="text" style="margin: 0; padding: 0;">查看</el-button></el-col>
       </el-row>
    </el-main>
  </el-container>
  </div>
</template>

<script>
  export default {
    data(){
      return{
        orders:[],
        userId:''
      }
    },
    methods:{
      getUrl(userPhotoLink){
        return "http://localhost:8088/image/" + userPhotoLink;
      },
      getNoPayOrders(){
        this.ajax.request({
          url: '/order/find/all',
          params: {
            userId: this.userId
          }
        }).then((res)=>{
            if (!res.data.code) {
                this.orders = res.data.result.orders;
            }
        })
      }
    },
    created() {
      this.userId = this.$store.state.loginAdmin.userId;
      this.getNoPayOrders();
    }
}
</script>

<style scoped>
  .order{
      border-top: 1px solid #c5c5c5;
      line-height: 20px;
      background: #f0f3ef;
      padding: 5px 0px;
      margin: 5px 0px ;
      clear: both;
  }
</style>
