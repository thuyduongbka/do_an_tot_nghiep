<template>
  <el-dialog title="Change Infor" :visible.sync="dialogVisible">
    <el-form v-if="data!= null" :model="form">
      <el-form-item label="Username" :label-width="formLabelWidth">
        <el-input v-model="form.username" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="Password" :label-width="formLabelWidth">
        <el-input v-model="form.password" type="password" ></el-input>
      </el-form-item>
      <el-form-item label="Active" :label-width="formLabelWidth">
        <el-switch v-model="form.active"></el-switch>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">Cancel</el-button>
      <el-button type="primary" @click="update">Submit</el-button>
    </span>
  </el-dialog>
</template>
<script>

  export default {
    name: "EditDialog",
    props: ["data", "show" ],
    data(){
      return {
        dialogVisible : this.show,
        form: {
          username: "",
          password: "",
          id : null,
          active: null
        },
        formLabelWidth: '120px'
      }
    },
    watch: {
      dialogVisible(val) {
        if (val){
          this.reloadForm();
        }
        this.$emit("update:show", val);
      },
      show(val) {
        this.dialogVisible = val;
      },
    },
    created() {
        this.reloadForm();
    },
    methods: {
      reloadForm(){
        if (this.data != null) {
          this.form.username = this.data.accountEntity.username;
          this.form.id = this.data.accountEntity.id;
          this.form.active = this.data.accountEntity.active;
        }
      },
      update(){
        this.form.id = this.data.accountEntity.id;
        this.$emit("update",this.form);
        this.dialogVisible = false;
      }
    }
  }
</script>
