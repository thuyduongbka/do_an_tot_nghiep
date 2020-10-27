import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import HighChartsVue from 'highcharts-vue';
import VueMoment from 'vue-moment';
import Auth from "@/security/Authentication";

import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);
Vue.use(HighChartsVue);
Vue.use(VueMoment);

Vue.config.productionTip = false


Vue.prototype.$auth = Auth;
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
