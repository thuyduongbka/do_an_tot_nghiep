<template>
  <div class="search">

    <el-container>
      <el-aside>
        <el-form :model="formData" status-icon ref="formData" class="register-form">
          <el-form-item prop="listCountryId">
            <div>Quốc gia </div>
            <el-select clearable multiple v-model="formData.listCountryId" filterable placeholder="Select">
              <el-option
                v-for="item in listCountry"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="listMajorId">
            <div>Ngành học</div>
            <el-select clearable v-model="formData.majorId" filterable placeholder="Khoa học máy tính">
              <el-option
                v-for="item in listMajor"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="listSchoolId">
            <div>Trường học</div>
            <el-select clearable multiple v-model="formData.listSchoolId" filterable placeholder="Select">
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
            <el-select clearable v-model="formData.levelName" filterable placeholder="Thạc sỹ">
              <el-option
                v-for="item in listLevel"
                :key="item"
                :label="item"
                :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="dueDate">
            <div>Thời hạn</div>
            <el-date-picker
              v-model="formData.dueDate"
              type="date"
              placeholder="Pick a day"
              format="dd/MM/yyyy">
            </el-date-picker>
          </el-form-item>
          <el-button class="btn btn-blue" round @click="search()">Tìm kiếm</el-button>
        </el-form>
      </el-aside>
      <el-main style="margin-top: 0px; padding: 5px;">
        <h1>TÌM KIẾM HỌC BỔNG</h1>
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
  export default {
    name: "Search",
    components: {SearchResult},
    created() {
      this.getData();
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
<style>

</style>
