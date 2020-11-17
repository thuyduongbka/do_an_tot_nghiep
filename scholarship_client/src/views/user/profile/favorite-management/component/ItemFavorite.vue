<template>
  <el-card v-if="item" class="box-card">
    <div style="display: flex; justify-content: space-between ">
      <div style="width: 90%">
        <h4>{{ item.name }}</h4>
      </div>
      <div style="">
        <el-button circle icon="el-icon-delete"
                   style="background-color: #FF275A; color: #FFFFFF; vertical-align: middle; margin-bottom: 10px"
                   @click="remove()"></el-button>
      </div>
    </div>
  </el-card>
</template>
<script>

export default {
  name: "ItemFavorite",
  props: ["item"],
  methods: {
    remove(){
      this.$emit("remove", this.item.id);
    },
    detail(){
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
