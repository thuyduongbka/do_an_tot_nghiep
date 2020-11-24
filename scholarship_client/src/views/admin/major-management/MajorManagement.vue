<template>
  <div v-loading="loading" style="text-align: left">
    <el-transfer
      v-model="majorChild"
      :data="listAll"

      :titles="['Children', 'Group']"
      filter-placeholder="enter name"
      filterable
      @change="handleChange">
      <div slot="right-footer" class="transfer-footer" size="small">
        <el-select
          v-model="majorParent"
          filterable
          placeholder="Enter name parent"
          size="mini">
          <el-option
            v-for="item in listAll"
            :key="item.key"
            :label="item.label"
            :value="item.key">
          </el-option>
        </el-select>
        <el-button circle icon="el-icon-plus" size="mini" type="success" @click="editDialog = true"></el-button>
      </div>
    </el-transfer>
    <create-dialog :show.sync="editDialog" @create="create"></create-dialog>
  </div>
</template>

<script>
import AlertService from "@/services/AlertService";
import MajorApi from "@/api/MajorApi";
import CreateDialog from "@/views/admin/major-management/CreateDialog";

export default {
  name: "MajorManagement",
  components: {
    CreateDialog
  },
  data() {
    return {
      listAll: [],
      loading: false,
      majorChild: [],
      majorParent: null,
      editDialog: false,
    }
  },
  created() {
    this.getData();
  },
  watch: {
    "majorParent"(value) {
      if (value != null) {
        this.reset();
      }
    }
  },
  methods: {
    async getData() {
      try {
        await MajorApi.getAll().then(result => {
          this.listAll =[];
          for (let m of result) {
            this.listAll.push({
              key: m.id,
              label: m.name + (m.child != ""?" - Parent":""),
              disabled: (m.parentId != null &&  m.parentId != this.majorParent) || (m.id == this.majorParent )
            })
          }
        })
      } catch (e) {
        AlertService.error(e)
      }
    },
    async updateParent(id) {
      try {
        await MajorApi.updateParent(id, this.majorParent).then(result => {
          this.reset();
        })
      } catch (e) {
        AlertService.error(e)
        this.majorChild = []
      }
    },

    async deleteParent(id) {
      try {
        await MajorApi.deleteParent(id, this.majorParent).then(result => {
          this.reset();
        })
      } catch (e) {
        AlertService.error(e)
      }
    },
    handleChange(value, direction, movedKeys) {
      if (this.majorParent == null) {
        AlertService.info("cần chọn Ngành học cha trước");
        this.majorChild = [];
        return
      }
      if (direction == "right") {
        for (let major of movedKeys) {
          this.updateParent(major);
        }
      }
      if (direction == "left") {
        for (let major of movedKeys) {
          this.deleteParent(major);
        }
      }
    },
    async getMajorByParent() {
      try {
        await MajorApi.getMajorByParent(this.majorParent).then(result => {
          this.majorChild = []
          for (let major of result) {
            this.majorChild.push(major.id)
          }
        })
      } catch (e) {
        AlertService.error(e)
      }
    },
    async create(name) {
      try {
        await MajorApi.create(name).then(result => {
          this.reset();
          AlertService.success("Tạo thành công")
        })
      } catch (e) {
        AlertService.error(e)
      }
    },
    reset() {
      this.getData();
      this.getMajorByParent();
    }

  }
}
</script>
<style scoped>
.transfer-footer {
  padding: 15px;
  margin: auto;
}

/deep/
.el-transfer-panel {
  width: 38%;
}

/deep/
.el-transfer-panel .el-transfer-panel__footer {
  height: 60px;
}
/deep/
.el-transfer-panel__body {
  min-height: 400px;
}
/deep/
.el-transfer-panel__list.is-filterable {
  height: 450px;
}
</style>
