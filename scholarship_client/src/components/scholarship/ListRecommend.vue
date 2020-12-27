<template>
  <div v-if="listScholarship" style="display:flex; flex-wrap: wrap; justify-content: center; margin-top: 20px">
    <scholarship v-for="scholarship in listScholarship" :key="scholarship.id"
                 :scholarship="scholarship"
                 :show-image="true" :show-interactive="false" style="width: 70%; "
    ></scholarship>
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

</style>
