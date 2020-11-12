<template>
<div>
  aaa
</div>
</template>
<script>
  import Auth from "@/security/Authentication";
  import ScholarshipApi from "@/api/ScholarshipApi";
  import AlertService from "@/services/AlertService";

  export default {
    name: "Compare",
    data(){
      return {
        scholarship1: null,
        scholarship2: null
      }
    },
    created() {
      if (this.$route.query.id === undefined) return;
      let id = this.$route.query.scholarship1;
      this.scholarship1 = this.getData();
    },
    methods: {
      async getData(id) {
        try {
          let userId = Auth.getCurrentUser().endUserId;
          await ScholarshipApi.get(userId, id).then(result => {
            return result.scholarshipEntity;
          })
        } catch (e) {
          AlertService.error(e)
        }
      },
    }
  }
</script>
<style scoped>

</style>
