<template>
  <el-carousel style="margin-top: 20px;" :interval="4000" type="card" height="370px" >
    <el-carousel-item v-for="s in listScholarship" :key="s.id" >
      <scholarship :scholarship="s"
                   :show-image="true"></scholarship>
    </el-carousel-item>
  </el-carousel>
</template>
<script>
import Scholarship from "@/components/scholarship/Scholarship";

export default {
  name : "ListRecommend",
  components : {
    Scholarship
  },
  props : ['listScholarship'],
  data() {
    return {

    }
  },
  methods : {

  },
  computed: {

  }
}
</script>
<style scoped>
/*/deep/*/
/*.el-carousel__item--card {*/
/*  transform: scale(0.83) translate(-20%) !important;*/
/*}*/
/*/deep/*/
/*.el-carousel__item--card.is-in-stage {*/
/*  transform:  scale(0.83) translate(140%) !important;*/
/*}*/
/deep/
.el-carousel__item--card.is-active {
  transform: scale(0.83) translate(60%) !important;
}

</style>
