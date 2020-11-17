<template>
  <div>
    <el-tabs class="profile" style="min-height: 500px;" tab-position="left" type="card">
      <el-tab-pane>
        <span slot="label"> Thông tin cá nhân</span>
        <div class="content">
          <profile-management :form-data="formData" @reset="reset" @submitForm="submitForm"></profile-management>
          <change-password :change-password="changePassword"></change-password>
        </div>
      </el-tab-pane>
      <el-tab-pane>
        <span slot="label"> Sở thích của tôi</span>
        <div class="content">
          <favorite :favorite-list="favorite" @reset="reset"></favorite>
        </div>
      </el-tab-pane>

    </el-tabs>
  </div>

</template>
<script>
import ProfileManagement from "@/views/user/profile/profile-management/ProfileManagement";
import ChangePassword from "@/views/user/profile/profile-management/ChangePassword";
import Favorite from "@/views/user/profile/favorite-management/Favorite";
import Auth from "@/security/Authentication";
import EndUserApi from "@/api/EndUserApi";
import AlertService from "@/services/AlertService";

export default {
  name: "Profile",
  components: {ProfileManagement, Favorite, ChangePassword},
  data(){
    return {
      formData: {
        username: '',
        name: '',
        gender: null,
        phone: '',
        birthday: null,
        graduationDate: null,
        level: '',
        accountId: null,
      },
      changePassword: {
        newPassword: '',
        oldPassword: '',
        checkPassword: ''
      },
      favorite: {
        countryFavorite: [],
        schoolFavorite: [],
        majorFavorite: []
      },
    }
  },
  created() {
    this.getData();
  },
  methods: {
    async getData() {
      let userId = Auth.getCurrentUser().endUserId;
      await EndUserApi.get(userId).then(result => {
        this.changeToForm(result);
        this.changeFavorite(result);
      }).catch(e => {
        AlertService.error(e);
      })
    },
    changeToForm(result) {
      this.formData.accountId = result.accountEntity.id;
      this.formData.username = result.accountEntity.username;
      this.formData.name = result.name;
      this.formData.birthday = new Date(result.birthday);
      this.formData.level = result.level;
      this.formData.phone = result.phone;
      this.formData.graduationDate = new Date(result.graduationDate);
      this.formData.gender = result.gender;
    },
    changeFavorite(result) {
      this.favorite.countryFavorite = result.countryEntities;
      this.favorite.schoolFavorite = result.schoolEntities;
      this.favorite.majorFavorite = result.majorEntities;
    },
    async submitForm() {
      try {
        await EndUserApi.update(this.formData).then(result => {
          AlertService.success("Cập nhật thành công");
          this.changeToForm(result);
        })
      } catch (e) {
        AlertService.error("Thất bại :  " + e)
      }
    },
    reset() {
      this.getData();
    },
  }
}
</script>
<style scoped>
/deep/
.profile .el-tabs--left .el-tabs__item.is-left {
  text-align: left;
  color: #6637EB;
  font-weight: bold;
}

/deep/
.profile .el-tabs__item {
  border-radius: 5px;
  padding-right: 20px !important;
  padding-left: 20px !important;
}

/deep/
.profile .is-active {
  color: #FFFFFF;
  background-color: #6637EB;
}

/deep/
.profile .el-tabs--left.el-tabs--card .el-tabs__nav {
  border-bottom: none;
  border-radius: 0px;
}
.profile .content {
  display: flex ;
  justify-content: center;
  flex-direction: column;
}
</style>
