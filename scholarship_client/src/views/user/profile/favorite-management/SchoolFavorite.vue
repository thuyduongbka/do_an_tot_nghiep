<template>
  <div>
    <div style="margin-bottom: 30px; margin-top: 30px; text-align: center">
      <el-select style="margin-right:20px; min-width: 400px;" clearable v-model="newItemId" filterable placeholder="Select">
        <el-option
          v-for="item in listSchool"
          :key="item.id"
          :label="item.name"
          :value="item.id">
        </el-option>
      </el-select>
      <el-button circle icon="el-icon-plus" style="background-color: #6637EB; color: #FFFFFF" @click="create"></el-button>
    </div>
    <div style="overflow: auto; height: 500px;">
      <item-favorite v-for="item in listFavorite" :key="item.id" :item="item" @remove="remove"></item-favorite>
    </div>
  </div>
</template>
<script>
import ItemFavorite from "@/views/user/profile/favorite-management/component/ItemFavorite";
import CountryApi from "@/api/CountryApi";
import AlertService from "@/services/AlertService";
import EndUserApi from "@/api/EndUserApi";
import SchoolApi from "@/api/SchoolApi";

export default {
  name : "SchoolFavorite",
  components: {ItemFavorite},
  props: ["listFavorite"],
  data() {
    return {
      newItemId: null,
      listSchool: [],
    }
  },
  created() {
    this.getData();
  },
  methods: {
    async create() {
      try {
        await EndUserApi.addSchoolFavorite(this.newItemId).then( result => {
          AlertService.success("Thêm thành công");
          this.reset();
        })
      } catch (e) {
        AlertService.error(e)
      }
    },
    async remove(id){
      try {
        await EndUserApi.deleteSchoolFavorite(id).then( result => {
          AlertService.success("Xóa thành công");
          this.reset();
        })
      } catch (e) {
        AlertService.error(e)
      }
    },
    async getData(){
      try {
        await SchoolApi.getAll().then(result => {
          this.listSchool = result;
        })
      } catch (e) {
        AlertService.error(e)
      }
    },
    reset(){
      this.$emit("reset");
    }
  }
}
</script>
<style scoped>

</style>
