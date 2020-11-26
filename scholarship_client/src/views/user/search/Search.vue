<template>
  <div>

    <el-container class="container">
      <el-aside class="search" style="width: 300px">
        <el-card class="box-card">
          <el-form ref="formData" :model="formData" class="form" status-icon>
            <el-form-item prop="listCountryId">
              <div>Quốc gia</div>
              <el-select v-model="formData.listCountryId" clearable filterable multiple placeholder="Select">
                <el-option
                  v-for="item in listCountry"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item v-if="isUser" prop="listMajorId">
              <div>Ngành học</div>
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
              <div>Trường học</div>
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
              <div>Bậc học</div>
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
              <div>Thời hạn</div>
              <el-date-picker
                v-model="formData.dueDate"
                format="dd/MM/yyyy"
                placeholder="Pick a day"
                type="date">
              </el-date-picker>
            </el-form-item>
            <el-button class="btn btn-blue" round @click="search()">Tìm kiếm</el-button>
          </el-form>
        </el-card>
      </el-aside>
      <el-main style="margin-top: 0px; padding: 0px;">
        <search-result :result="result" @search="search()" :pageParam="pageParam"></search-result>
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
      listCountry: [],
      listMajor: [],
      listLevel: [],
      pageParam: {
        page: 1,
        pageSize: 10,
      },
      result: null,
      isUser: Auth.getCurrentRole() === Roles.ROLE_END_USER,
    }
    },
    methods: {
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      async search() {
        try {
          await ScholarshipApi.getAll(this.pageParam, this.formData).then(result => {
            this.result = result;
          })
        } catch (e) {
          AlertService.error(e)
        }
      },
      async getData() {
        try {
          await CountryApi.getAll().then(result => {
            this.listCountry = result;
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
