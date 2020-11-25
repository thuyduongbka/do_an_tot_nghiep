<template>
  <el-card v-if="formData" class="box-card" shadow="always">
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
          type="datetime">
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
          type="datetime">
        </el-date-picker>
      </el-form-item>

      <el-form-item style="text-align: center; margin-top: 60px;">
        <el-button class="btn btn-white" round @click="reset">Reset</el-button>
        <el-button class="btn btn-blue" round @click="submitForm()">Sửa đổi</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>

export default {
  name: "ProfileManagement",
  props: ["formData"],
  data() {
    return {
      optionsGender: ["Nam", "Nữ", "Khác"],
      optionsLevel: ["THCS","THPT","Cử nhân", "Thạc sỹ", "Tiến sỹ"],
      rules: {}
    }
  },
  methods: {
    reset(){
      this.$emit("reset");
    },
    async submitForm(){
      this.$emit("submitForm");
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
  margin: auto;
  margin-bottom: 20px;
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
@media only screen and (max-width: 450px) {
  .box-card {
    width: 90%;
  }
}
</style>
