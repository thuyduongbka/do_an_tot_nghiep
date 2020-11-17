<template>
<div>
  <div style="margin-bottom: 30px; text-align: center">
    <el-button style="background-color: #6637EB; color: #FFFFFF" icon="el-icon-search" @click="search">Search</el-button>
  </div>
  <div style="overflow: auto; height: 500px;">
    <item-scholarship v-for="s in listScholarship" :key="s.id" :scholarship="s" @remove="remove"></item-scholarship>
  </div>
</div>
</template>
<script>
import ScholarshipInteractiveApi from "@/api/ScholarshipInteractiveApi";
import AlertService from "@/services/AlertService";
import ItemScholarship from "@/views/user/profile/favorite-management/component/ItemScholarship";
import Auth from "@/security/Authentication";
import Pages from "@/router/Pages";

export default {
  name: "ScholarshipFavorite",
  components: {ItemScholarship},
  data() {
    return {
      newScholarshipId: null,
      listScholarship: [],
    }
  },
  created() {
    this.getData();
  },
  methods: {
    create() {
      console.log("create")
    },
    async remove(scholarshipId){
      let userId = Auth.getCurrentUser().endUserId;
      await ScholarshipInteractiveApi.addFavorite(scholarshipId, userId, false).then(r => {
        this.getData();
        AlertService.success("Remove Success")
      }).catch(e => {
        AlertService.error("Can't remove");
      });

    },
    async getData() {
      await ScholarshipInteractiveApi.listAllFavorite().then(result => {
        this.listScholarship = result;
      }).catch(e => {
        AlertService.error(e);
      })
    },
    search(){
      this.$router.push(Pages.search.path);
    }
  }
}
</script>
<style scoped>

</style>
