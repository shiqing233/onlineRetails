import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

let str = sessionStorage.getItem("loginAdmin");
let admin;
if(str){
  admin = JSON.parse(str);
}

export default new Vuex.Store({
  state:{
    loginAdmin:admin
  }
})
