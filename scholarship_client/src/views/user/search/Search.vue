<template>
  <div class="search">
    <h1>TÌM KIẾM HỌC BỔNG</h1>
    <el-form :model="formData" status-icon ref="formData" class="register-form">
      <el-row :gutter="5">
        <el-col :span="6">
          <el-form-item prop="listCountryId">
            <div>đất nước bạn muốn tới là: </div>
            <el-select multiple v-model="formData.listCountryId" filterable placeholder="Select">
              <el-option
                v-for="item in listCountry"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item prop="listMajorId">
            <div>ngành bạn muốn theo đuổi là:</div>
            <el-select multiple v-model="formData.listMajorId" filterable placeholder="Khoa học máy tính">
              <el-option
                v-for="item in listMajor"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item prop="listSchoolId">
            <div>trường bạn muốn học là:</div>
            <el-select multiple v-model="formData.listSchoolId" filterable placeholder="Select">
              <el-option
                v-for="item in listSchool"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

  </div>
</template>
<script>
  import CountryApi from "@/api/CountryApi";
  import AlertService from "@/services/AlertService";
  import SchoolApi from "@/api/SchoolApi";
  import MajorApi from "@/api/MajorApi";

  export default {
    name: "Search",
    created() {
      this.getData();
    },
    data() {
      return {
        formData: {
          listCountryId: [],
          listSchoolId: [],
          listMajorId: [],
        },
        listSchool: [],
        listCountry: [],
        listMajor: [],
      }
    },
    methods: {
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      async submitForm(formName) {
        this.$emit("submit");
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
      }
    }
  }
</script>
<style>

</style>
