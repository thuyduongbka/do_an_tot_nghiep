<template>
    <el-card class="box-card">
      <div class="select">
        <p v-for="item in list"
           :class="item.id == itemCurrent?'active':''"
           @click="select(item.id)"
        >{{ item.name }}</p>
      </div>
    </el-card>
</template>
<script>
export default {
  name: '',
  props: ['list', 'itemCurrent'],
  components: {},
  data() {
    return {
    }
  },
  created() {
  },
  methods: {
    select(id){
      this.$emit("update", id);
    }
  }
}
</script>
<style scoped>
.box-card {
  padding: 5px;
  background-color: #b69de5;
  max-width: 200px;
  color: white;
  text-align: left;
  max-height: 450px;
  overflow: auto;
  width: 25%;
}
.select {
  width: 90%;
  list-style-type: none;
}
.select p {
  border-bottom: 1px solid white;
  transition: all .2s ease-in-out;
}
.select p:hover {
  transform: scale(1.2);
  cursor: pointer;
}
.select p.active {
  transform: scale(1.2);
}
@media only screen and (max-width: 450px) {
  .box-card {
    width: 100%;
    max-width: 100%;
    max-height: 200px;
  }
}
</style>
