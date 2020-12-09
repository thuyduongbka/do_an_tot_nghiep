<template>
  <div>

    <el-container class="container">
      <el-aside class="search" style="width: 300px">
        <el-card class="box-card">
          <el-form ref="formData" :model="formData" class="form" status-icon>
            <el-form-item prop="listCountryId">
              <div>Country</div>
              <el-select v-model="formData.listCountryId" clearable filterable multiple placeholder="Select">
                <el-option
                  v-for="item in listCountry"
                  :key="item.id"
                  :label="item[1].name"
                  :value="item[1].id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item v-if="isUser" prop="listMajorId">
              <div>Major</div>
              <el-select v-model="formData.majorId" clearable filterable placeholder="Khoa học máy tính">
                <el-option
                  v-for="item in listMajor"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item v-if="isUser" prop="listSchoolId">
              <div>School</div>
              <el-select v-model="formData.listSchoolId" clearable filterable multiple placeholder="Select">
                <el-option
                  v-for="item in listSchool"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item prop="listMajorId">
              <div>Level</div>
              <el-select v-model="formData.levelName" clearable filterable placeholder="Thạc sỹ">
                <el-option
                  v-for="item in listLevel"
                  :key="item"
                  :label="item"
                  :value="item">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item v-if="isUser" prop="dueDate">
              <div>Deadline</div>
              <el-date-picker
                v-model="formData.dueDate"
                format="dd/MM/yyyy"
                placeholder="Pick a day"
                type="date">
              </el-date-picker>
            </el-form-item>
            <el-button class="btn btn-blue" round @click="search()">Find All</el-button>
          </el-form>
        </el-card>
      </el-aside>
      <el-main style="margin-top: 0px; padding: 0px;">
        <search-result :result="result" @search="search()" :pageParam="pageParam" v-loading="loading"></search-result>
      </el-main>
    </el-container>

  </div>
</template>
<script>
import CountryApi from "@/api/CountryApi";
import AlertService from "@/services/AlertService";
import SchoolApi from "@/api/SchoolApi";
import MajorApi from "@/api/MajorApi";
import LevelApi from "@/api/LevelApi";
import ScholarshipApi from "@/api/ScholarshipApi";
import SearchResult from "@/views/user/search/SearchResult";
import Auth from "@/security/Authentication";
import Roles from "@/security/Roles";

export default {
  name: "Search",
  components: {SearchResult},
  created() {
    this.getData();
    this.search();
  },
  data() {
    return {
      formData: {
        listCountryId: [],
        listSchoolId: [],
        majorId: null,
        levelName: null,
        dueDate: null
      },
      listSchool: [],
      listCountry: new Map(),
      listMajor: [],
      listLevel: [],
      pageParam: {
        page: 1,
        pageSize: 10,
      },
      result: null,
      isUser: Auth.getCurrentRole() === Roles.ROLE_END_USER,
      loading: false
    }
  },
  watch: {
    'formData.listCountryId'(val) {
      if (val.length != 0) {
        this.listSchool = [];
        let listSchoolId = []
        for (let countryId of val) {
          this.listSchool = this.listSchool.concat(this.listCountry.get(countryId).schoolEntities);
          this.formData.listSchoolId.forEach(id => {
            if (this.findCountryFromSchoolId(id) === countryId){
              listSchoolId.push(id);
            }
          });
        }
        this.formData.listSchoolId = listSchoolId;
      } else {
        this.getData();
      }
    },
    'formData.listSchoolId'(val) {
      if (val.length != 0) {
        if (this.formData.listCountryId.length == 0) {
          for (let schoolId of val) {
            for (let school of this.listSchool) {
              if (school.id === schoolId) {
                this.formData.listCountryId.push(school.countryId);
              }
            }
          }
        }
      }
    }
  },
  methods: {
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    async search() {
      this.loading = true;
      try {
        await ScholarshipApi.getAll(this.pageParam, this.formData).then(result => {
          this.result = result;
        })
      } catch (e) {
          AlertService.error(e)
        }
        this.loading = false;
      },
      async getData() {
        try {
          await CountryApi.getAll().then(result => {
            for (let r of result) {
              this.listCountry.set(r.id, r)
            }
          })
        } catch (e) {
          AlertService.error(e)
        }
        try {
          await SchoolApi.getAll().then(result => {
            this.listSchool = result;
          })
        } catch (e) {
          AlertService.error(e)
        }
        try {
          await MajorApi.getAll().then(result => {
            this.listMajor = result;
          })
        } catch (e) {
          AlertService.error(e)
        }
        try {
          await LevelApi.getAll().then(result => {
            this.listLevel = result;
          })
        } catch (e) {
          AlertService.error(e)
        }
      }
    }
  }
</script>
<style scoped>
.box-card {
  width: 90%;
  background-color: #b69de5;
}
.form div {
 color: #FFFFFF;
  font-weight: bold;
}
.form > * {
  margin-bottom: 0px;
}
.btn {
  margin-top: 10px;
}
/deep/
.el-date-editor.el-input, .el-date-editor.el-input__inner {
  width: auto;
}
.search {
  width: 230px;
}
@media only screen and (max-width: 450px) {
  .container {
    display: flex;
    flex-wrap: wrap;
    justify-content: left;
  }
  .search {
    width: 100% !important;
  }
  .box-card {
    width: 100%;
  }
}
</style>
