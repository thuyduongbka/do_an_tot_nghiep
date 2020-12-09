import Vue from 'vue'
import App from './App'
import router from './router'
import "@/security/index"
import ElementUI from 'element-ui';
import HighChartsVue from 'highcharts-vue';
import VueMoment from 'vue-moment';
import Auth from "@/security/Authentication";
import locale from 'element-ui/lib/locale/lang/en'
import AOS from "aos"
import 'aos/dist/aos.css'

import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI, {locale})
Vue.use(HighChartsVue);
Vue.use(VueMoment);

Vue.config.productionTip = false
Vue.prototype.$auth = Auth;

let vm = new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>',
  created() {
    AOS.init()
  }
})

export default vm;
