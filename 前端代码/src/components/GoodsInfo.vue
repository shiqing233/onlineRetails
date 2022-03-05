`<template>
  <div>
    <el-container>
      <el-header>Header</el-header>

      <el-container>
        <el-aside width="50%">

          <el-carousel indicator-position="outside">
            <el-carousel-item v-for="link in photoLinks" :key="link">
              <img :src="photoLinkHeader + link" />
            </el-carousel-item>
          </el-carousel>

        </el-aside>
        <el-main>


          <h1 id="goodsName">{{goodsInfo.goodsName}}</h1>


          <el-form label-width="80px">
            <el-form-item label="价格">
              <div v-if="lowestPrice < highestPrice">¥{{lowestPrice}} ~ ¥{{highestPrice}}</div>
              <div v-else>¥{{lowestPrice}}</div>
            </el-form-item>
            <el-form-item label="地址">
              <div>{{goodsInfo.address}}</div>
            </el-form-item>
            <el-form-item label="类别">

                <el-radio-group v-model="radio" :disabled="!goodsInfo.isSale" @change="selectedChange">
                  <el-radio-button :key="item.goodsTypeId" v-for="item in goodsTypeInfos" :label="item.type" :disabled="!item.stock"/>
                </el-radio-group>

            </el-form-item>
            <el-form-item label="数量">
              <el-input-number v-model="num" :min="1" :disabled="!goodsInfo.isSale" @change="currentPriceChange"/>
            </el-form-item>
            <el-form-item label="应付">
              <div v-if="selectedGoodsType.price != null">¥{{currentPrice}}</div>
            </el-form-item>
          </el-form>


          <div id="buttons">
            <el-button type="success" @click="placeOrderDialogVisible = true" :disabled="!selectedGoodsType.goodsTypeId">下单</el-button>
            <el-button type="danger" @click="addCartDialogVisible = true" :disabled="!selectedGoodsType.goodsTypeId">加入购物车</el-button>
          </div>
        </el-main>

      </el-container>
      <el-footer>
        <el-tabs type="border-card">
          <el-tab-pane label="商品详情">{{goodsInfo.goodsDescription}}</el-tab-pane>
          <el-tab-pane label="用户评价">
            <div class="infinite-list-wrapper" style="overflow:auto">
              <ul class="list" v-infinite-scroll="loadComment" infinite-scroll-disabled="scrollDisable">
                <li v-for="comment in comments" class="infinite-list-item">
                  <el-rate
                    v-model="comment.star"
                    disabled
                    show-score
                    text-color="#ff9900"
                    score-template="{value}">
                  </el-rate>
                  <div>{{comment.userName}}</div>
                  <div>{{comment.reviewTime}}</div>
<!--                  <div>{{comment.reviewTime.date.year}}-{{comment.reviewTime.date.month}}-{{comment.reviewTime.date.day}}</div>
                  <div>{{comment.reviewTime.time.hour}}:{{comment.reviewTime.time.minute}}</div> -->
                  <div>{{comment.content}}</div>
                </li>
              </ul>
              <p v-if="commentLoading">加载中...</p>
              <p v-if="noMore">没有更多了</p>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-footer>
    </el-container>

    <el-dialog title="下单" :visible.sync="placeOrderDialogVisible">
      <el-form label-width="80px">
        <el-form-item label="商品名称">
          <div>{{goodsInfo.goodsName}}</div>
        </el-form-item>
        <el-form-item label="商品类型">
          <div>{{selectedGoodsType.type}}</div>
        </el-form-item>
        <el-form-item label="购买数量">
          <div>{{num}}</div>
        </el-form-item>
        <el-form-item label="收货地址">
          <el-select v-model="selectedAddressInfo" placeholder="请选择收货地址">
            <el-option v-for="address in addressInfos" :label="address.address" :value="address.address"/>
          </el-select>
        </el-form-item>
        <el-form-item label="价格合计">
          <div>¥{{currentPrice}}</div>
        </el-form-item>
      </el-form>


      <div slot="footer" class="dialog-footer">
        <el-button @click="placeOrderDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="placeOrder">确认下单</el-button>
      </div>
    </el-dialog>

    <el-dialog title="加入购物车" :visible.sync="addCartDialogVisible">
      <el-form label-width="80px">
        <el-form-item label="商品名称">
          <div>{{goodsInfo.goodsName}}</div>
        </el-form-item>
        <el-form-item label="商品类型">
          <div>{{selectedGoodsType.type}}</div>
        </el-form-item>
        <el-form-item label="购买数量">
          <div>{{num}}</div>
        </el-form-item>

        <el-form-item label="价格合计">
          <div>¥{{currentPrice}}</div>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="addCartDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addCart">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  export default {
    data(){
      return {
        photoLinkHeader: 'http://localhost:8088/image/',
        addCartDialogVisible: false,
        placeOrderDialogVisible: false,
        lowestPrice: null,
        highestPrice: null,
        radio: '',
        num: 1,
        currentPrice: null,
        selectedGoodsType: {
          goodsTypeId: null,
          type: null,
          stock: null,
          price: null
        },
        goodsTypeInfos: [],
        goodsInfo: {
          goodsId: null,
          goodsName: null,
          goodsDescription: null,
          goodsPhotosLink: null,
          isSale: null ,
          address: null
        },
        comments:[],
        commentPage: 1,
        commentLoading: false,
        commentTotal: null,
        selectedAddressInfo: {
          addressId: null,
          address: null
        },
        addressInfos: [],
        photoLinks: []

      }
    },
    methods: {
      placeOrder(){
        console.log('placeOrder');

        let params = {
          userId: this.$route.query.userId,
          goodsTypeId: this.selectedGoodsType.goodsTypeId,
          number: this.num
        };
        this.ajax.request({
          url: '/CreateOrder',
          params
        }).then( res => {
          if(!res.data.code){
            this.$notify({
              title: '成功',
              message: '成功下单',
              type: 'success'
            });
            this.addCartDialogVisible = false;
            this.$router.push('/Pay');
          }
        });
        // TODO
        // 跳转到支付页面
      },
      addCart(){
        console.log('addCart');
        let params = {
          userId: this.$route.query.userId,
          goodsTypeId: this.selectedGoodsType.goodsTypeId,
          number: this.num
        }
        this.ajax.request({
          url: '/AddCart',
          params
        }).then( res => {
          if(!res.data.code){
            this.$notify({
              title: '成功',
              message: '该商品成功加入购物车',
              type: 'success'
            });
            this.addCartDialogVisible = false;
          }
        });
      },
      selectedChange(){
        this.goodsTypeInfos.forEach(item => {
          if(item.type == this.radio){
            this.selectedGoodsType = item;
          }
        });
        this.currentPriceChange();
      },
      currentPriceChange(){
        this.currentPrice = (this.num * this.selectedGoodsType.price).toFixed(2);
      },
      loadComment(){
        let params = {
          goodsId: this.$route.query.goodsId,
          pageNum: this.commentPage,
          rowNum: 5
        }
        this.ajax.request({
          url: '/LoadComment',
          params
        }).then( res => {
          if(!res.data.code){
            this.commentPage++;
            this.comments = this.comments.concat(res.data.result.newComments.list);
            this.commentLoading = false;
            this.commentTotal = res.data.result.newComments.total;
            console.log(res.data.result.newComments)
          }
        });
        this.commentLoading = true;
      },
      loadGoodsInfo(){
        let params = {
          goodsId: this.$route.query.goodsId
        }
        this.ajax.request({
          url: '/GoodsInfo',
          params
        }).then( res => {
          if(!res.data.code){
            this.goodsInfo = res.data.result.goodsInfo;
            this.goodsTypeInfos = res.data.result.goodsTypeInfos;
            this.lowestPrice = res.data.result.lowestPrice;
            this.highestPrice = res.data.result.highestPrice;

          }
        });
      },
      loadAddressInfos(){

        let params = {
          userId: "123"
        }
        this.ajax.request({
          url: '/AddressInfo',
          params
        }).then( res => {
          if(!res.data.code){
            this.addressInfos = res.data.result.addressInfos;
          }
        });
      },
      loadGoodsPhotosLink(){
        let params = {
          goodsId: this.$route.query.goodsId
        }
        this.ajax.request({
          url: '/GoodsPhotosLink',
          params
        }).then(res => {
          if(!res.data.code){
            this.photoLinks = res.data.result.links;
          }
        });
      }

    },
    computed: {
      noMore(){
        return (this.commentPage - 1) * 5 > this.commentTotal;
      },
      scrollDisable(){
        return this.commentLoading || this.noMore;
      }
    },
    created(){
      this.loadGoodsInfo();
      this.loadComment();
      this.loadAddressInfos();
      this.loadGoodsPhotosLink();
    }

  }

</script>

<style scoped>
  .el-header, .el-footer {
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }

 .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
     background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
     background-color: #d3dce6;
  }

  .div-float{
    float: left;
    margin-left: 20%;
    line-height: 1.25rem;
  }

  #goodsName{
    margin: 20%;
    text-align: left;
    line-height: 1.25rem;
    margin-top: 0;
    margin-bottom: 0;
  }

  .infinite-list-wrapper{
    height: 60rem;
  }

</style>
