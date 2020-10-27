<template>
  <div class="home">
    <div class="home-title">xin chào, dưới đây là học bổng dành cho bạn...</div>
    <div class="home-scholarships">
      <list-scholarship :data="listScholarship"></list-scholarship>
    </div>
  </div>
</template>
<script>
  import listScholarship from "./data"
  import ListScholarship from "@/components/scholarship/ListScholarship";

  export default {
    name : "HomeUser",
    components : {
      ListScholarship
    },
    data() {
      return {
        listScholarship : listScholarship,
      }
    }
  }
</script>
<style>

</style>
