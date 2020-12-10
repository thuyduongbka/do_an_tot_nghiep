<template>
  <div style="height: 100%; min-height: 500px;">
    <h1>Compare scholarships</h1>
    <el-card class="card-compare" style="height: 100%; min-height: 500px;">
      <table style="width:100%">
        <tr class="first">
          <th></th>
          <td width="45%">
            <div>
              <el-select v-model="scholarshipId1" filterable placeholder="Select" style=" width:100%;">
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
              <el-select v-model="scholarshipId2" filterable placeholder="Select" style=" width:100%;">
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
        <tr v-if="scholarshipId1 || scholarshipId2">
          <th>Name</th>
          <td v-if="scholarship1"><span style="text-decoration: underline; cursor: pointer"
                                        @click="detail(scholarshipId1)">{{
              scholarship1.name
            }}</span></td>
          <td v-if="scholarship2"><span style="text-decoration: underline; cursor: pointer"
                                        @click="detail(scholarshipId2)">{{
              scholarship2.name
            }}</span></td>
        </tr>
        <tr v-if="scholarshipId1 || scholarshipId2">
          <th>Country</th>
          <td v-if="scholarship1">{{ scholarship1.countryEntity.name }}</td>
          <td v-if="scholarship2">{{ scholarship2.countryEntity.name }}</td>
        </tr>
        <tr v-if="scholarshipId1 || scholarshipId2">
          <th>School</th>
          <td v-if="scholarship1">{{ scholarship1.schoolEntity ? scholarship1.schoolEntity.name : "" }}</td>
          <td v-if="scholarship2">{{ scholarship2.schoolEntity ? scholarship2.schoolEntity.name : "" }}</td>
        </tr>
        <tr v-if="scholarshipId1 || scholarshipId2">
          <th>Level</th>
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
        <tr v-if="scholarshipId1 || scholarshipId2">
          <th>Major</th>
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
        <tr v-if="scholarshipId1 || scholarshipId2">
          <th>Deadline</th>
          <td v-if="scholarship1">{{ formatDate(scholarship1.time) }}</td>
          <td v-if="scholarship2">{{ formatDate(scholarship2.time) }}</td>
        </tr>
      </table>
    </el-card>
    <list-recommend-carousel :list-scholarship="Array.from(listRecommend, ([name, value]) => (value))"></list-recommend-carousel>
<!--    <list-recommend :list-scholarship="Array.from(listRecommend, ([name, value]) => (value))"></list-recommend>-->
  </div>
</template>
<script>
import Auth from "@/security/Authentication";
import ScholarshipApi from "@/api/ScholarshipApi";
import AlertService from "@/services/AlertService";
import ScholarshipInteractiveApi from "@/api/ScholarshipInteractiveApi";
import utils from "@/utils";
import Pages from "@/router/Pages";
import ListRecommend from "@/components/scholarship/ListRecommend";
import ListRecommendCarousel from "@/components/scholarship/ListRecommendCarousel";

export default {
  name: "Compare",
  components: {ListRecommend, ListRecommendCarousel},
  data() {
    return {
      scholarshipId1: null,
      scholarshipId2: null,
      scholarship1: null,
      scholarship2: null,
      allScholarship: [],
      listRecommend: new Map()
    }
  },
  created() {
    this.getListScholarship();
    this.scholarshipId1 = localStorage.getItem("scholarshipId1")
    if (this.$route.query.scholarshipId2 === undefined) return;
    this.scholarshipId2 = parseInt(this.$route.query.scholarshipId2);
    // localStorage.removeItem("scholarshipId1");
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
          for (let r of result.listRecommend){
            this.listRecommend.set(r.id,r);
          }
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
          for (let r of result.listRecommend){
            this.listRecommend.set(r.id,r);
          }
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
.card-compare {
  color: #6637EB;
}

th, td {
  border-bottom: solid .5px;
}

.first th, .first td {
  border-bottom: none;
}

th, td {
  padding: 10px;
  text-align: left;
}


/deep/
.el-select-dropdown, .el-popper {
  max-width: 500px;
}

@media only screen and (max-width: 450px) {
  .card-compare {
    width: 500px;
    overflow-x: auto;
  }
}
</style>
