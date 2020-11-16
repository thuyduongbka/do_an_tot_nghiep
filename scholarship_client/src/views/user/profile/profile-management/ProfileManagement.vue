<template>
  <el-card class="box-card" shadow="always">
    <el-form ref="formData" :model="formData" :rules="rules" class="form" status-icon>
      <el-form-item prop="email">
        <div>Địa chỉ email</div>
        <el-input v-model="formData.username"></el-input>
      </el-form-item>

      <el-form-item prop="name">
        <div>Họ và tên</div>
        <el-input v-model="formData.name"></el-input>
      </el-form-item>

      <el-form-item prop="gender">
        <div>Giới tính</div>
        <el-select v-model="formData.gender" placeholder="Select">
          <el-option
            v-for="item in optionsGender"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item prop="birthday">
        <div>Ngày sinh</div>
        <el-date-picker
          v-model="formData.birthday"
          format="dd/MM/yyyy"
          placeholder="Pick a day"
          type="date">
        </el-date-picker>
      </el-form-item>

      <el-form-item prop="phone">
        <div>Số điện thoại</div>
        <el-input v-model="formData.phone"></el-input>
      </el-form-item>

      <el-form-item prop="level">
        <div>Cấp bậc</div>
        <el-select v-model="formData.level" placeholder="Select">
          <el-option
            v-for="item in optionsLevel"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item prop="graduationDate">
        <div>Ngày tốt nghiệp</div>
        <el-date-picker
          v-model="formData.graduationDate"
          format="dd/MM/yyyy"
          placeholder="Pick a day"
          type="date">
        </el-date-picker>
      </el-form-item>

      <el-form-item style="text-align: center; margin-top: 60px;">
        <el-button class="btn btn-white" round @click="reset">Reset</el-button>
        <el-button class="btn btn-blue" round @click="submitForm('formData')">Sửa đổi</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>
import Auth from "@/security/Authentication";
import EndUserApi from "@/api/EndUserApi";
import AlertService from "@/services/AlertService";
import router from "@/router";
import Pages from "@/router/Pages";

export default {
  name: "ProfileManagement",
  data() {
    return {
      formData: {
        username: '',
        name: '',
        gender: null,
        phone: '',
        birthday: null,
        graduationDate: null,
        level: '',
      },
      changePassword: {
        password: '',
        checkPassword: '',
      },
      optionsGender: ["Nam", "Nữ", "Khác"],
      optionsLevel: ["THCS","THPT","Cử nhân", "Thạc sỹ", "Tiến sỹ"],
      rules: {}
    }
  },
  created() {
    this.getData();
  },
  methods: {
    async getData(){
      let userId = Auth.getCurrentUser().endUserId;
      await EndUserApi.get(userId).then(result => {
        this.changeToForm(result);
      }).catch(e => {
        AlertService.error(e);
      })
    },
    reset(){
      this.getData();
    },
    changeToForm(result){
      this.formData.accountId = result.accountEntity.id;
      this.formData.username = result.accountEntity.username;
      this.formData.name = result.name;
      this.formData.birthday = result.birthday;
      this.formData.level = result.level;
      this.formData.phone = result.phone;
      this.formData.graduationDate = result.graduationDate;
      this.formData.gender = result.gender;
    },
    async submitForm(){
      try {
        await EndUserApi.update(this.formData).then(result => {
          AlertService.success("Cập nhật thành công");
          this.changeToForm(result);
        })
      } catch (e) {
        AlertService.error("Thất bại :  " + e)
      }
    },

  }

}
</script>
<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card {
  width: 90%;
  display: flex;
  justify-content: center;
}

.form {
  text-align: left;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  color: #6637EB;
  font-weight: bold;
  text-decoration: underline;
}
.form > * {
  margin: 15px;
}
.btn {
  padding: 8px 20px 8px 20px;
}

</style>
