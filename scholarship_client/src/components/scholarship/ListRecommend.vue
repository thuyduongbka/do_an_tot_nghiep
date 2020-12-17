<template>
  <div v-if="listScholarship" style="display:flex; flex-wrap: wrap; justify-content: center;">
    <scholarship v-for="scholarship in listScholarship" :key="scholarship.id"
                 :scholarship="scholarship"
                 :show-image="true" :show-interactive="false" style="transform: scale(0.8)"
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
