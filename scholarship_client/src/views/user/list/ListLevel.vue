<template>
  <div class="box">
    <SelectLevel :item-current="currentId"
                 :list="listLevel"
                 @update="select">
    </SelectLevel>
    <el-card class="box-card-search">
      <div class="list">
        <p v-if="listScholarshipCurrent.length == 0">Hiện không có học bổng nào</p>
        <scholarship v-for="scholarship in listScholarshipCurrent"
                     :key="scholarship.id"
                     :scholarship="scholarship"
                     :show-image="false" style="height: auto; width: 28%; min-width: 180px; "/>
      </div>
    </el-card>
  </div>
</template>
<script>
import LevelApi from "@/api/LevelApi";
import AlertService from "@/services/AlertService";
import SelectLevel from "@/views/user/list/component/Select";
import ScholarshipApi from "@/api/ScholarshipApi";
import Scholarship from "@/components/scholarship/Scholarship";

export default {
  name: '',
  components: {SelectLevel, Scholarship},
  data() {
    return {
      currentId: "PHD",
      listLevel: [],
      listScholarshipCurrent: []
    }
  },
  created() {
    this.getData();
    this.currentId = this.$route.query.id?this.$route.query.id:"PHD"
    this.find(this.currentId);
  },
  methods: {
    async getData() {
      try {
        await LevelApi.getAll().then(result => {
          this.listLevel = []
          for (let l of result){
            this.listLevel.push({
              id: l,
              name: l
            })
          }
        })
      } catch (e) {
        AlertService.error(e)
      }
    },
    select(id) {
      this.currentId = id;
      this.find(id);
    },
    async find(id) {
      try {
        let param = {levelName: id}
        await ScholarshipApi.findAllBy(param).then(result => {
          this.listScholarshipCurrent = result;
        })
      } catch (e) {
        AlertService.error(e)
      }
    },
  }
}
</script>
<style scoped>
.box-card-search {
  color: #513a92;
  height: 500px;
  max-height: 450px;
  overflow: auto;
  width: 100%;
}
.box  {
  display: inline-flex;
  width: 100%;
}
.list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.list > * {
  margin: 20px !important;
}

@media only screen and (max-width: 450px) {
  .box-card-search {
    border: 1px solid #513a92;
  }
  .box {
    display: flex;
    flex-direction: column;
  }
}
</style>
