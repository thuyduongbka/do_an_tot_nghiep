<template>
<el-card class="box-card-search">
  <div v-if="!result">Chưa có học bổng nào</div>
  <div v-else>
    <div class="list">
      <scholarship v-for="scholarship in result.content" :key="scholarship.id" :scholarship="scholarship"
                   :show-image="false" style="width: 25%; min-width: 180px"></scholarship>
    </div>
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="pageParam.page"
        :page-sizes="[10, 20, 30]"
        :page-size.sync="pageParam.pageSize"
        layout="total, sizes, prev, pager, next"
        :total="result.totalElements">
      </el-pagination>
    </div>
  </div>
</el-card>
</template>
<script>
import Scholarship from "@/components/scholarship/Scholarship";

export default {
  name: 'SearchResult',
  components: {Scholarship},
  props: ["result", "pageParam"],
  data() {
    return {}
  },
  methods: {
    handleSizeChange() {
      this.$emit("search");
      },
      handleCurrentChange(){
        this.$emit("search");
      },
    }
  }
</script>
<style scoped>
.box-card-search {
  color: #513a92;
  height: 100%;
  max-height: 500px;
  overflow: auto;
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
}
</style>
