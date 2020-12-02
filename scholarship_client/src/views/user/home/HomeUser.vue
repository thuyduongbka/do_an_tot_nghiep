<template>
  <div class="home">
    <div class="home-title">xin chào, dưới đây là học bổng dành cho bạn...</div>
    <div class="home-scholarships"v-loading="loading">
      <div style="display:flex; flex-wrap: wrap; justify-content: center;"  v-if="listScholarship" >
        <scholarship  v-for="scholarship in listScholarship.slice(0,6*numberMore)" :key="scholarship.id" :scholarship="scholarship"
                     :show-image="true" :show-interactive="true"></scholarship>
      </div>
      <el-button style="background-color: #6637EB; color: #FFFFFF" icon="el-icon-arrow-down"
                 @click="numberMore+=1">Xem thêm</el-button>
    </div>
  </div>
</template>
<script>
import Scholarship from "@/components/scholarship/Scholarship";
import AlertService from "@/services/AlertService";
import RecommendApi from "@/api/RecommendApi";

export default {
  name: "HomeUser",
  components: {
    Scholarship
  },
  data() {
    return {
      listScholarship: null,
      loading: false,
      numberMore: 1,
    }
  },
  created() {
    this.getRecommend();
  },
  methods: {
    async getRecommend() {
      this.loading = true;
      try {
        await RecommendApi.getRecommend().then(result => {
          this.listScholarship = result;
        })
      } catch (e) {
        AlertService.error(e)
      }
      this.loading = false;
    },
  },
}
</script>
<style>
.home-scholarships{
  margin-top: 30px;
}
</style>
