import Vue from 'vue'
import Axios from 'axios'//引入axios的依赖

Axios.defaults.baseURL = 'http://localhost:8088';
Axios.defaults.timeout = 7000;
Axios.defaults.withCredentials = true;

Vue.prototype.ajax = Axios;
