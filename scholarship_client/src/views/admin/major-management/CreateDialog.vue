<template>
  <el-dialog title="Thêm Major cha" :visible.sync="dialogVisible">
    <el-form>
      <el-form-item label="Name" :label-width="formLabelWidth">
        <el-input v-model="newMajor" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">Cancel</el-button>
      <el-button type="primary" @click="create">Update</el-button>
    </span>
  </el-dialog>
</template>
<script>

export default {
  name: "CreateDialog",
  props: ["show" ],
  data(){
    return {
      dialogVisible : this.show,
      newMajor: '',
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
      this.newMajor = '';
    },
    create(){
      this.$emit("create",this.newMajor);
      this.dialogVisible = false;
    }
  }
}
</script>
