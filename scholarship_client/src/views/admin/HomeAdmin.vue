<template>
  <div v-loading="loading">
    <div style="margin-bottom: 30px; text-align: center">
      <el-input style="margin-right:20px; max-width: 200px" placeholder="nhập url muốn thêm" v-model="newWeb"></el-input>
      <el-button type="success" icon="el-icon-plus" circle @click="create"></el-button>
    </div>
    <el-table
      :data="data"
      border
    class="table">
      <el-table-column
        prop="name"
        label="Tên">
        <template slot-scope="scope">
          <a :href="scope.row.url" target="_blank">{{scope.row.url}}</a>
        </template>
      </el-table-column>
      <el-table-column
        prop="status"
        label="Trạng thái"
        width="100">
        <template slot-scope="scope">
          <el-switch active-color="#13ce66" inactive-color="#ff4949" v-model="scope.row.active"></el-switch>
        </template>
      </el-table-column>
      <el-table-column
        prop="crawlPeriod"
        label="Thời gian trích rút">
        <template slot-scope="scope">
          <div style="display: flex">
            <el-input-number v-model="scope.row.time" controls-position="right":min="1"></el-input-number>
            <el-select v-model="scope.row.typeTime" placeholder="Select">
              <el-option
                v-for="time in timeTypes"
                :key="time"
                :label="time"
                :value="time">
              </el-option>
            </el-select>
          </div>
        </template>
      </el-table-column>
      <el-table-column
        prop="lastCrawled"
        label="Trích rút lần cuối">
      </el-table-column>
      <el-table-column
        prop="updatedTime"
        label="Hành động"
        v-if="checkChange"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleEdit(scope.$index)" type="text" size="small">Cập nhật</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import AlertService from "@/services/AlertService";
  import WebApi from "@/api/WebApi";

  export default {
    name: "HomeAdmin",
    data() {
      return {
        timeTypes: ['HOUR','DAY', 'MONTH'],
        data: [],
        checkChange: false,
        loading: false,
        newWeb: ""
      }
    },
    created() {
      this.getData();
    },
    watch: {
      'data': {
        handler: function(newVal, oldVal) {
          if (oldVal.length != 0){
            this.checkChange = true;
          }
        },
        deep: true
      },
    },
    methods: {
      async getData() {
        try {
          await WebApi.getAll().then(result => {
            this.data = result;
          })
        } catch (e) {
          AlertService.error(e)
        }
      },
      async handleEdit(index){
        this.loading = true;
        try {
          await WebApi.update(this.data[index]).then(result => {
            AlertService.success("Success")
            this.resetTable();
          })
        } catch (e) {
          AlertService.error(e);
        }
        this.loading = false;
      },
      resetTable(){
        this.getData();
      },
      create(){

      }
    },
    computed: {

    }
  }
</script>
<style scoped>
  .table {
    text-align: center;
    width: 80%;
    min-width: 800px;
    margin: auto;
  }
</style>
