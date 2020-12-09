<template>
  <div v-loading="loading">
    <div style="margin-bottom: 30px; text-align: center">
      <el-input style="margin-right:20px; max-width: 200px" placeholder="enter the email to add" v-model="newData.email"></el-input>
      <el-button type="success" icon="el-icon-plus" circle @click="create"></el-button>
    </div>
    <el-table
      :data="data"
      border class="table">
      <el-table-column
        prop="email"
        label="Email"
        width="180">
        <template slot-scope="scope">
          <span>{{scope.row.accountEntity.username}}{{scope.row.password===""?"(Mới)":""}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="status"
        label="Status"
        width="100">
        <template slot-scope="scope">
          <el-switch disabled v-model="scope.row.accountEntity.active"></el-switch>
        </template>
      </el-table-column>
      <el-table-column
        prop="lastLogin"
        label="Last login">
      </el-table-column>
      <el-table-column
        prop="updatedTime"
        label="Action">
        <template slot-scope="scope">
          <el-button @click="handleEdit(scope.row)" type="text" size="small">Edit</el-button>
          <el-button @click="handleDelete(scope.row)" type="text" size="small">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <EditDialog :data="currentData" :show.sync="editDialog" @update="update"></EditDialog>
  </div>
</template>

<script>
  import AlertService from "@/services/AlertService";
  import AdminApi from "@/api/AdminApi";
  import EditDialog from "@/views/admin/admin-management/EditDialog";

  export default {
    name: "AdminManagement",
    components: {
      EditDialog
    },
    data() {
      return {
        newData : {
          email: "",
        },
        data: [],
        editDialog: false,
        currentData: null,
        loading: false,
      }
    },
    created() {
      this.getData();
    },
    methods: {
      async getData(){
        try {
          await AdminApi.getAll().then(result => {
            this.data = result;
          })
        } catch (e) {
          AlertService.error(e)
        }
      },
      async create(){
        try {
          await AdminApi.save(this.newData.email).then(result => {
            console.log(result);
            this.resetTable();
          })
        } catch (e) {
          AlertService.error(e);
        }
      },
      async update(form){
        this.loading = true;
        try {
          await AdminApi.update(form).then(result => {
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
      handleEdit(data){
        this.currentData = data;
        this.editDialog = true;
      },
      handleDelete(){

      }

    }
  }
</script>
<style scoped>
  .table {
    text-align: center;
    width: 80%;
    min-width: 670px;
    margin: auto;
  }
</style>
