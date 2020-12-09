<template>
  <div class="home">
    <el-card style="">
      <div style="font-weight: bold; color: #6637EB">Xin chào, dưới đây là học bổng dành cho bạn...</div>
      <div v-loading="loading" class="home-scholarships" >
        <div v-if="listScholarship" style="display:flex; flex-wrap: wrap; justify-content: center;">
          <scholarship data-aos="fade-up" v-for="scholarship in listScholarship.slice(0,6*numberMore)" :key="scholarship.id"
                       :scholarship="scholarship"
                       :show-image="true" :show-interactive="true"
          ></scholarship>
        </div>
        <el-button icon="el-icon-arrow-down" style="background-color: #6637EB; color: #FFFFFF"
                   @click="numberMore+=1">Xem thêm
        </el-button>
      </div>
    </el-card>
    <el-card style="margin-top: 10px; " data-aos="fade-up">
      <p style="font-weight: bold; color: #6637EB">Danh sách Học bổng được nhiều người xem</p>
      <list-recommend-carousel :list-scholarship="listMostViews"></list-recommend-carousel>
    </el-card>

  </div>
</template>
<script>
import Scholarship from "@/components/scholarship/Scholarship";
import AlertService from "@/services/AlertService";
import RecommendApi from "@/api/RecommendApi";
import ScholarshipApi from "@/api/ScholarshipApi";
import ListRecommendCarousel from "@/components/scholarship/ListRecommendCarousel";

export default {
  name: "HomeUser",
  components: {
    Scholarship,
    ListRecommendCarousel
  },
  data() {
    return {
      listScholarship: [],
      loading: false,
      numberMore: 1,
      listMostViews: []
    }
  },
  created() {
    this.getRecommend();
    this.getMostViews();
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
    async getMostViews() {
      this.loading = true;
      try {
        await ScholarshipApi.findMostViews().then(result => {
          this.listMostViews = result;
        })
      } catch (e) {
        AlertService.error(e)
      }
      this.loading = false;
    },
  },
}
</script>
<style scoped >

.home-scholarships{
  margin-top: 30px;
}
</style>
