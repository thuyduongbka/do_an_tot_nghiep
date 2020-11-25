<template>
  <div class="home">
    <div class="home-title">xin chào, dưới đây là học bổng dành cho bạn...</div>
    <div class="home-scholarships">
      <div style="display:flex; flex-wrap: wrap; justify-content: center;">
        <scholarship v-for="scholarship in listScholarship" :key="scholarship.id" :scholarship="scholarship"
                     :show-image="true"></scholarship>
      </div>
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
    }
  },
  created() {
    this.getRecommend();
  },
  methods: {
    async getRecommend() {
      try {
        await RecommendApi.getRecommend().then(result => {
          this.listScholarship = result;
        })
      } catch (e) {
        AlertService.error(e)
      }
    },
  }
}
</script>
<style>
.home-scholarships{
  margin-top: 30px;
}
</style>
