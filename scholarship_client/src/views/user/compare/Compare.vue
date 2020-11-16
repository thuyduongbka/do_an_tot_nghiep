<template>
  <div>
    <h1>So sánh học bổng</h1>
    <table style="width:100%">
      <tr>
        <th></th>
        <td width="45%">
          <div>
            <el-select style=" width:100%;" v-model="scholarshipId1" filterable placeholder="Select">
              <el-option
                v-for="item in allScholarship"
                :key="item.id"
                :label="item.name"
                :value="item.id">
                <span style="float: left">{{ item.name }}</span>
              </el-option>
            </el-select>
          </div>
        </td>
        <td width="45%">
          <div>
            <el-select style=" width:100%;" v-model="scholarshipId2" filterable placeholder="Select">
              <el-option
                v-for="item in allScholarship"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </div>
        </td>
      </tr>
      <tr>
        <th>Tên</th>
        <td v-if="scholarship1"><span @click="detail(scholarshipId1)"
                                      style="text-decoration: underline; cursor: pointer">{{ scholarship1.name }}</span></td>
        <td v-if="scholarship2"><span @click="detail(scholarshipId2)"
                                      style="text-decoration: underline; cursor: pointer">{{ scholarship2.name }}</span></td>
      </tr>
      <tr>
        <th>Quốc gia</th>
        <td v-if="scholarship1">{{ scholarship1.countryEntity.name }}</td>
        <td v-if="scholarship2">{{ scholarship2.countryEntity.name }}</td>
      </tr>
      <tr>
        <th>Trường</th>
        <td v-if="scholarship1">{{ scholarship1.schoolEntity ? scholarship1.schoolEntity.name : "" }}</td>
        <td v-if="scholarship2">{{ scholarship2.schoolEntity ? scholarship2.schoolEntity.name : "" }}</td>
      </tr>
      <tr>
        <th>Cấp học</th>
        <td v-if="scholarship1">
          <ul>
            <li v-for="level in scholarship1.levelEntities" :key="level.id">{{ level.name }}</li>
          </ul>
        </td>
        <td v-if="scholarship2">
          <ul>
            <li v-for="level in scholarship2.levelEntities" :key="level.id">{{ level.name }}</li>
          </ul>
        </td>
      </tr>
      <tr>
        <th>Ngành học</th>
        <td v-if="scholarship1">
          <ul>
            <li v-for="major in scholarship1.majorEntities" :key="major.id">{{ major.name }}</li>
          </ul>
        </td>
        <td v-if="scholarship2">
          <ul>
            <li v-for="major in scholarship2.majorEntities" :key="major.id">{{ major.name }}</li>
          </ul>
        </td>
      </tr>
      <tr>
        <th>Thời hạn</th>
        <td v-if="scholarship1">{{ formatDate(scholarship1.time) }}</td>
        <td v-if="scholarship2">{{ formatDate(scholarship2.time) }}</td>
      </tr>
    </table>

  </div>
</template>
<script>
import Auth from "@/security/Authentication";
import ScholarshipApi from "@/api/ScholarshipApi";
import AlertService from "@/services/AlertService";
import ScholarshipInteractiveApi from "@/api/ScholarshipInteractiveApi";
import utils from "@/utils";
import Pages from "@/router/Pages";

export default {
  name: "Compare",
  data() {
    return {
      scholarshipId1: null,
      scholarshipId2: null,
      scholarship1: null,
      scholarship2: null,
      allScholarship: []
    }
  },
  created() {
    this.getListScholarship();
    if (this.$route.query.scholarshipId1 === undefined) return;
    this.scholarshipId1 = parseInt(this.$route.query.scholarshipId1);
    this.getData1();

  },
  watch: {
    "scholarshipId1"(val) {
      this.scholarshipId1 = val;
      this.getData1();
    },
    "scholarshipId2"(val) {
      this.scholarshipId2 = val;
      this.getData2();
    }
  },
  methods: {
    formatDate(date) {
      return utils.formatDate(date);
    },
    async getData1() {
      try {
        let userId = Auth.getCurrentUser().endUserId;
        await ScholarshipApi.get(userId, this.scholarshipId1).then(result => {
          this.scholarship1 = result.scholarshipEntity;
          this.countCompare(this.scholarshipId1);
        })
      } catch (e) {
        AlertService.error(e)
      }
    },
    async getData2() {
      try {
        let userId = Auth.getCurrentUser().endUserId;
        await ScholarshipApi.get(userId, this.scholarshipId2).then(result => {
          this.scholarship2 = result.scholarshipEntity;
          this.countCompare(this.scholarshipId2);
        })
      } catch (e) {
        AlertService.error(e)
      }
    },
    async getListScholarship() {
      try {
        await ScholarshipApi.getAllName().then(result => {
          this.allScholarship = result;
        })
      } catch (e) {
        AlertService.error(e)
      }
    },
    async countCompare(id) {
      let userId = Auth.getCurrentUser().endUserId;
      await ScholarshipInteractiveApi.countCompare(id, userId);
    },
    detail(id) {
      this.$router.push({name: Pages.detailScholarship.name, query: {id: id}});
    }
  }
}
</script>
<style scoped>
th, td {
  border-bottom: solid 1px;
}

th, td {
  padding: 10px;
  text-align: left;
}


/deep/
.el-select-dropdown, .el-popper {
  max-width: 500px;
}
</style>
