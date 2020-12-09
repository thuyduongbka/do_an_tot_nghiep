<template>
  <el-row :gutter="60" style="width: 100%">
    <el-col  :span="12" :xs="24">
      <img width="80%" src="@/assets/register-3.svg" alt="register">
    </el-col>
    <el-col  :span="12" :xs="24">
      <h4>... and your preferences</h4>
      <el-form :model="formData" status-icon ref="formData" class="register-form">
        <el-form-item prop="listCountryId">
          <div>Which country are you considering? </div>
          <el-select multiple v-model="formData.listCountryId" filterable placeholder="Select">
            <el-option
              v-for="item in listCountry"
              :key="item.id"
              :label="item[1].name"
              :value="item[1].id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="listSchoolId">
          <div>Which school are you considering? </div>
          <el-select multiple v-model="formData.listSchoolId" filterable placeholder="Select">
            <el-option
              v-for="item in listSchool"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="listMajorId">
          <div>Which major are you considering? </div>
          <el-select multiple v-model="formData.listMajorId" filterable placeholder="Select">
            <el-option
              v-for="item in listMajor"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item style="text-align: center">
          <el-button class="btn btn-white" round @click="back">Back</el-button>
          <el-button class="btn btn-blue" round @click="submitForm('formData')">Finish</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>
<script>
import AlertService from "@/services/AlertService";
import CountryApi from "@/api/CountryApi";
import SchoolApi from "@/api/SchoolApi";
import MajorApi from "@/api/MajorApi";

export default {
  name: "RegisterOne",
  props: ["formData"],
  created() {
    this.getData();
  },
  data() {
    return {
      listSchool: [],
      listCountry: new Map(),
      listMajor: [],
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
    back() {
      this.$emit("changeTab", 2);
    },
    findCountryFromSchoolId(schoolId){
      for (let school of this.listSchool) {
        if (school.id === schoolId) {
          return school.countryId;
        }
      }
    },
    async submitForm(formName) {
      this.$emit("submit");
    },
      async getData(){
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
            this.listMajor= result;
          })
        } catch (e) {
          AlertService.error(e)
        }
      }
    }
  }
</script>
<style>
  .register-form {
    text-align: left;
  }
  .el-select {
    display: flex;
  }
</style>

