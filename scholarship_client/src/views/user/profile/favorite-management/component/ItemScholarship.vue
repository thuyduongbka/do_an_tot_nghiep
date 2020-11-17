<template>
  <el-card v-if="scholarship" class="box-card">
    <div style="display: flex; justify-content: space-between ">
      <div style="width: 90%">
        <p>{{ scholarship.time }}</p>
        <h4>{{ scholarship.name }}</h4>
        <p><span v-if="scholarship.schoolEntity">{{ scholarship.schoolEntity.name + " - "}}</span> {{ scholarship.countryEntity.name }}</p>
      </div>
      <div style="">
        <el-button circle icon="el-icon-delete"
                   style="background-color: #FF275A; color: #FFFFFF; vertical-align: middle; margin-bottom: 10px"
                   @click="remove()"></el-button>
        <el-button circle icon="el-icon-s-promotion"
                   style="background-color: #6637EB; color: #FFFFFF; vertical-align: middle; margin-left: 0px"
                   @click="detail()"></el-button>
      </div>
    </div>
  </el-card>
</template>
<script>
import Pages from "@/router/Pages";

export default {
  name: "ItemScholarship",
  props: ["scholarship"],
  methods: {
    remove(){
      this.$emit("remove", this.scholarship.id);
    },
    detail(){
      this.$router.push({name:Pages.detailScholarship.name, query: {id : this.scholarship.id} });
    }
  }
}
</script>
<style scoped>
.box-card {
  width: 80%;
  margin: auto;
  margin-bottom: 10px;
  text-align: left;
  font-size: 14px;
  transition: width 1s;
  color: #6637EB;
}

.box-card:hover {
  width: 90%;
}
</style>
