<template>
  <div>
    <el-container>
      <el-header>
        修改信息
      </el-header>
      <el-main>
        <el-row>
        <el-col :span="4" :offset="4">昵称</el-col>
        <el-col :span="8" ><el-input v-model="user.userName" :placeholder="this.$store.state.loginAdmin.userName"></el-input></el-col>
        </el-row>
        <el-row>
        <el-col :span="4" :offset="4">手机号</el-col>
        <el-col :span="8">
          <el-input v-model="user.phone" :placeholder= "this.$store.state.loginAdmin.phone"></el-input>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="4" :offset="4">头像</el-col>
          <el-col :span="4" :offset="2">
            <el-upload
              class="avatar-uploader"
              action="#"
              :show-file-list="false"
              :auto-upload="false"
              :on-change="handleChange"
              :limit="1"
              accept="image/png,image/jpg,image/jpeg"
              >
              <img v-if="imageUrl" :src="imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-col>
        </el-row>
        <el-row>
          <el-button type="primary" @click="upImage()">提交</el-button>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  export default{
    data(){
      return{
        user:{
            userId:null,
            userName:null,
            userPassword:null,
            userPhotoLink:null,
            phone:null
         },
        imageUrl:'',
        fileList:[]
      }
    },
    methods:{
      getUrl(userPhotoLink){
        return "http://localhost:8088/image/" + userPhotoLink;
      },

      handleChange(file,fileList){
        this.fileList = fileList;
        this.imageUrl = URL.createObjectURL(fileList[0].raw);
      },

      upImage(){
          this.$confirm(`是否要修改个人信息`, '提示', {
            confirmButtonText: '确定修改',
            cancelButtonText: '我再想想',
            type: 'warning'
          }).then(()=>{
            let fd = new FormData();
                for(let prop in this.user){
                  fd.append(prop,this.user[prop]);
                }
                if(!this.fileList.length){
                  this.$message.error('头像不能为空');
                }
                fd.append('file',this.fileList[0].raw);
                this.ajax.request({
                    url:'/user/upload',
                    data:fd,
                    method:'post'
                }).then((res)=>{
                    if(!res.data.code){
                      this.$message({
                        message: '修改成功',
                        type: 'success'
                      });
                    }
                    else{
                      this.$message.error('修改失败');
                    }
                })
          }).catch(()=>{
          });
    },
    },
    created() {
      this.user.userId = this.$store.state.loginAdmin.userId;
      this.user.userName = this.$store.state.loginAdmin.userName;
      this.user.phone = this.$store.state.loginAdmin.phone;
      this.user.userPhotoLink = this.$store.state.loginAdmin.userPhotoLink;
    }
  }
</script>

<style scoped>
  .avatar-uploader .el-upload {
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
      border-color: #409EFF;
    }
    .avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 60px;
      height: 60px;
      line-height: 60px;
      text-align: center;
    }
    .avatar {
      width: 60px;
      height: 60px;
      display: block;
    }
    .el-row{
      height: 80px;
    }
</style>
