<template>
<span style="margin-left: 20px;">
  <el-button :type="status? 'danger':'default'" circle class="el-icon-close"
             @click="status=!status"/>
</span>
</template>
<script>
export default {
  props: ['value', 'name'],
  data() {
    return {
      status: this.value,
    }
  },
  watch: {
    'value'(val) {
      this.status = val;
    },
    'status'(val) {
      this.$emit("update:value", val);
      if (this.name) this.$emit("change", this.name, val);
    }
  }
}
</script>
<style scoped>
/deep/
.el-button.is-circle {
  padding: 4px;
}

</style>
