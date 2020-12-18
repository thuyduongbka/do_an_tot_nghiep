<template>
  <el-dialog
    :visible.sync="dialogVisible"
    class="dialog" :width="'80%'"
    top="20px">
    <div slot="title" style="border-bottom: 1px solid #6637EB; width: 70%; margin: auto; text-align: center">
      <h4>Maybe you'll like these scholarships</h4>
    </div>
    <div>
      <list-recommend :list-scholarship="listRecommend"></list-recommend>
    </div>
    <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">Cancel</el-button>
    </span>
  </el-dialog>
</template>

<script>

import ListRecommend from "@/components/scholarship/ListRecommendCarousel";
export default {
  props: ['listRecommend', 'show'],
  data() {
    return {
      dialogVisible: this.show,
    }
  },
  components: {ListRecommend},
  watch: {
    dialogVisible(val) {
      this.$emit("update:show", val);
    },
    show(val) {
      this.dialogVisible = val;
    },
  },
}
</script>
