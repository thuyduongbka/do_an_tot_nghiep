<template>
  <div class="list-scholarship">
    <div style="margin: auto; font-size: xxx-large;" @click="changeView(-1)"><i class="el-icon-arrow-left"></i></div>
    <div v-for="(scholarship, index) in data">
      <scholarship v-show="checkData(index)" :scholarship="scholarship" :show-image="true"></scholarship>
    </div>
    <div  style="margin: auto; font-size: xxx-large"><i class="el-icon-arrow-right" @click="changeView(1)"></i></div>
  </div>
</template>
<script>
  import Scholarship from "@/components/scholarship/Scholarship";

  export default {
    name : "ListScholarship",
    components : {
      Scholarship
    },
    props : ['data'],
    data() {
      return {
        itemStart: 0,
      }
    },
    methods : {
      changeView(count){
        this.itemStart += count;
      },
      checkData(pos){
        return pos >= this.itemStart  && pos < this.itemStart + 3;
      }
    },
    computed: {

    }
  }
</script>
<style>
.list-scholarship {
  margin-top: 50px;
  display: flex;
  justify-content: center;
}
</style>
