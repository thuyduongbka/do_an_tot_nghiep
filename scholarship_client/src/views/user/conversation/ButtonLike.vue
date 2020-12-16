<template>
<span style="float: right">
  <el-button :type="status? 'default':'success'" circle class="el-icon-check"
           @click="status=false"/>
  <el-button :type="status? 'danger':'default'" circle class="el-icon-close"
           @click="status=true"/>
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
