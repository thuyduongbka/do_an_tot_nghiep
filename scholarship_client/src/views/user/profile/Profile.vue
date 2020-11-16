<template>
  <div>
    <el-tabs class="profile" style="min-height: 500px; height: 450px;" tab-position="left" type="card">
      <el-tab-pane>
        <span slot="label"> Thông tin cá nhân</span>
        <div class="content">
          <profile-management></profile-management>
        </div>
      </el-tab-pane>
      <el-tab-pane>
        <span slot="label"> Sở thích của tôi</span>
        <div class="content">
          <favorite></favorite>
        </div>
      </el-tab-pane>

    </el-tabs>
  </div>

</template>
<script>
import ProfileManagement from "@/views/user/profile/profile-management/ProfileManagement";
import Favorite from "@/views/user/profile/favorite-management/Favorite";

export default {
  name: "Profile",
  components: {ProfileManagement, Favorite}
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
}
</style>
