<template>
  <div>
    <el-container>
      <el-header>
        地址信息
      </el-header>
      <el-main>
        <el-row style="height: auto;">
          <el-col :span="1"><el-button size="mini" type="success" @click="getPos()">添加地址</el-button></el-col>
        </el-row>
       <el-table :data="adds" stripe>
          <el-table-column prop="id" label="ID" width="88">
          </el-table-column>
          <el-table-column prop="province" label="省" width="150">
          </el-table-column>
          <el-table-column prop="city" label="市" width="150">
          </el-table-column>
          <el-table-column prop="district" label="区" width="150">
          </el-table-column>
          <el-table-column prop="street" label="街道" width="150">
          </el-table-column>
          <el-table-column prop="concrete" label="详细" width="150">
          </el-table-column>
          <el-table-column prop="flag" label="默认" :formatter="formatBoolean" width="100">
          </el-table-column>
          <el-table-column label="操作" width="100">
            <template slot-scope="scope">
              <el-button @click="delAdds(scope.row)" type="text" size="small" icon="el-icon-delete-solid"></el-button>
              <el-button @click="setDefault(scope.row)" type="text" size="small" icon="el-icon-edit"></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
    <el-dialog title="添加地址" :visible.sync="addAddsFormDialogVisible" ref="addAddsForm" :model="addAddsForm">
      <el-form label-width="80px">
        <el-row type="flex" justify="space-around">
          <el-col :span="10">
            <el-form-item label="省">
              <el-select placeholder="请选择" v-model="addAddsForm.province"  @input="getPos(addAddsForm.province,1)" style="width: 100%;">
                <el-option v-for="item in positions.a" :value="item.name" :label="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="市">
              <el-select placeholder="请选择" no-data-text="请先选择上一级地址" v-model="addAddsForm.city" @input="getPos(addAddsForm.city,2)" style="width: 100%;">
                <el-option v-for="item in positions.b" :value="item.name" :label="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" justify="space-around">
          <el-col :span="10">
            <el-form-item label="区">
              <el-select placeholder="请选择" no-data-text="请先选择上一级地址" v-model="addAddsForm.district"  @input="getPos(addAddsForm.district,3)" style="width: 100%;">
                <el-option v-for="item in positions.c" :value="item.name" :label="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="街道">
              <el-select placeholder="请选择" no-data-text="请先选择上一级地址" v-model="addAddsForm.street" style="width: 100%;">
                <el-option v-for="item in positions.d" :value="item.name" :label="item.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" justify="space-around">
          <el-col :span="22">
            <el-form-item label="详细" style="width: 100%;">
              <el-input v-model="addAddsForm.concrete" placeholder="请填写具体地址"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addAddsFormDialogVisible=false">取 消</el-button>
        <el-button type="primary" @click="doAddAdds()">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  export default{
    data(){
      return{
        adds:[],
        userId:'',
        addId:'',
        addAddsFormDialogVisible: false,
        addAddsForm:{
          province:'',
          city:'',
          district:'',
          street:'',
          concrete:''
        },
        positions:{a:[],
                   b:[],
                   c:[],
                   d:[]},
      }
    },
    methods:{

      formatBoolean(row, column, value) {
          var ret = ''
          if (value) {
              ret = "是"
          } else {
              ret = "否"
          }
          return ret;
      },

      doAddAdds(){
        this.$confirm(`是否要添加地址`, '提示', {
          confirmButtonText: '确定添加',
          cancelButtonText: '犹豫一下',
          type: 'warning'
        }).then(() => {
          if(   !this.addAddsForm.province
             || !this.addAddsForm.city
             || !this.addAddsForm.district
             || !this.addAddsForm.street)
          {
            this.$message({
              message: '省、市、区、街道均不能为空',
              type: 'error'
            });
            return;
          }
          this.ajax.request({
            url: '/adds/add',
            params: {
              userId: this.userId,
              adds: this.addAddsForm.province + '-' +
                    this.addAddsForm.city     + '-' +
                    this.addAddsForm.district + '-' +
                    this.addAddsForm.street   + '-' +
                    this.addAddsForm.concrete
            },
            method:'post'
          }).then((res) => {
            if (!res.data.code) {
              this.$message({
                message: '添加成功',
                type: 'success'
              });
              this.addAddsFormDialogVisible = false;
              for(let prop in this.addAddsForm){
                this.addAddsForm[prop] = null;
              }
              for(let prop in this.positions){
                this.positions[prop] = [];
              }
              this.getAdds();
            } else {
              this.$message({
                message: '添加失败',
                type: 'error'
              });
            }
          })
        }).catch(() => {
        });
      },

      // doModifyAdds(){
      //   this.$confirm(`是否要修改地址`, '提示', {
      //     confirmButtonText: '确定修改',
      //     cancelButtonText: '考虑一下',
      //     type: 'warning'
      //   }).then(() => {
      //       this.ajax.request({
      //         url: '/adds/update',
      //         params: {
      //           addressId: this.addId,
      //           add:  this.addAddsForm.province + '-' +
      //                 this.addAddsForm.city     + '-' +
      //                 this.addAddsForm.district + '-' +
      //                 this.addAddsForm.street   + '-' +
      //                 this.addAddsForm.concrete
      //         }
      //         }).then((res)=>{
      //           if(!res.data.code) {
      //             this.$message({
      //               message: '修改成功',
      //               type: 'success'
      //             });
      //             this.getAdds();
      //           } else {
      //             this.$message({
      //               message: '修改失败',
      //               type: 'error'
      //             });
      //           }
      //       })
      //   }).catch(() => {
      //   });
      // },

      setDefault(add){
        this.$confirm(`是否要设置地址 ${add.id}-${add.province}-${add.city}-${add.district}-${add.street}-${add.concrete} 为默认地址`, '提示', {
          confirmButtonText: '确定设置',
          cancelButtonText: '思考一下',
          type: 'warning'
        }).then(() => {
          //确定
          this.ajax.request({
            url: '/adds/update',
            params: {
              addressId: add.id
            }
          }).then((res) => {
            if (!res.data.code) {
              this.$message({
                message: '设置成功',
                type: 'success'
              });
              this.getAdds();
            } else {
              this.$message({
                message: '设置失败',
                type: 'error'
              });
            }
          })
        }).catch(() => {
        });
      },

      delAdds(add){
          this.$confirm(`是否要删除地址：${add.id}-${add.province}-${add.city}-${add.district}-${add.street}-${add.concrete}`, '提示', {
            confirmButtonText: '确定删除',
            cancelButtonText: '思考一下',
            type: 'warning'
          }).then(() => {
            //确定
            this.ajax.request({
              url: '/adds/del',
              params: {
                addressId: add.id
              }
            }).then((res) => {
              if (!res.data.code) {
                this.$message({
                  message: '删除成功',
                  type: 'success'
                });
                this.getAdds();
              } else {
                this.$message({
                  message: '删除失败',
                  type: 'error'
                });
              }
            })
          }).catch(() => {
          });
      },

      // modifyAdds(add){
      //     for(let prop in this.addAddsForm){
      //       this.addAddsForm[prop] = add[prop];
      //     }
      //     this.addId = add.id;
      //     this.$confirm(`是否要修改地址：${add.id}-${add.province}-${add.city}-${add.district}-${add.street}-${add.concrete}`, '提示', {
      //       confirmButtonText: '确定修改',
      //       cancelButtonText: '考虑一下',
      //       type: 'warning'
      //     }).then(() => {
      //       //确定
      //       console.log(this.addAddsForm);
      //       this.getPos();//写参数调用不了？？？
      //     }).catch(() => {
      //     });
      // },
      // flag: true  添加
      //       undifined 修改
      getPos(pos="中国", index=0){
        this.addAddsFormDialogVisible = true;
        this.ajax.request({
          url: 'https://restapi.amap.com/v3/config/district',
          withCredentials: false,
          params: {
            key: '4637f9a3d452860fb60dc4f5e36625f1',
            keywords: pos,
            subdistrict: '1',
          },
          method:'GET'
        }).then((res)=>{
            if (res.data.status) {
                switch(index){
                  case 0:
                  this.positions.a = res.data.districts[0].districts;
                  break;
                  case 1:
                  this.positions.b = res.data.districts[0].districts;
                  break;
                  case 2:
                  this.positions.c = res.data.districts[0].districts;
                  break;
                  case 3:
                  this.positions.d = res.data.districts[0].districts;
                  break;
                }
            }
        })
      },
      getAdds(){
        this.ajax.request({
          url: '/adds/find',
          params: {
            userId: this.userId
          },
          method:'Get'
        }).then((res)=>{
          while(this.adds.pop());
          if(!res.data.code){
            let temp = new Array();
            for(let i=0;i<res.data.result.result.length;i++){
              temp = res.data.result.result[i].address.split('-');
              let that = {};
              that.id = res.data.result.result[i].addressId;
              that.province = temp[0];
              that.city = temp[1];
              that.district = temp[2];
              that.street = temp[3];
              that.concrete = temp[4];
              that.flag = res.data.result.result[i].flag;
              this.adds.push(that);
            }
          }
        })
      }

    },
    created() {
      this.userId = this.$store.state.loginAdmin.userId;
      this.getAdds();
    }
  }
</script>

<style>
</style>
