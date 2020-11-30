<template>
  <div class="list">
    <scholarship v-for="scholarship in listScholarship" :key="scholarship.id" :scholarship="scholarship"
                 :show-image="true"></scholarship>
  </div>
</template>
<script>
import Scholarship from "@/components/scholarship/Scholarship";

export default {
  name : "ListScholarship",
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
<style>
.list {
  margin-top: 50px;
  display: flex;
  flex-wrap: wrap;
}
</style>
