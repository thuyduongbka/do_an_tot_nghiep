<template>
    <el-card class="favorite box-card" shadow="always">
      <el-tabs>
        <el-tab-pane>
          <span slot="label"> Học bổng yêu thích</span>
          <div class="content">
            <scholarship-favorite></scholarship-favorite>
          </div>
        </el-tab-pane>
        <el-tab-pane>
          <span slot="label"> Quốc gia yêu thích</span>
          <div class="content">
            <country-favorite></country-favorite>
          </div>
        </el-tab-pane>
        <el-tab-pane>
          <span slot="label"> Trường học yêu thích</span>
          <div class="content">
            <school-favorite></school-favorite>
          </div>
        </el-tab-pane>
        <el-tab-pane>
          <span slot="label">Ngành học yêu thích</span>
          <div class="content">
            <major-favorite></major-favorite>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-card>


</template>
<script>
import ScholarshipFavorite from "@/views/user/profile/favorite-management/ScholarshipFavorite";
import CountryFavorite from "@/views/user/profile/favorite-management/CountryFavorite";
import SchoolFavorite from "@/views/user/profile/favorite-management/SchoolFavorite";
import MajorFavorite from "@/views/user/profile/favorite-management/MajorFavorite";
export default {
  name: "Favorite",
  components: {
    SchoolFavorite,
    ScholarshipFavorite,
    CountryFavorite,
    MajorFavorite
  }
}
</script>
<style scoped>
/deep/
.favorite .el-tabs--left .el-tabs__item.is-left {
  text-align: left;
  color: #6637EB;
  font-weights: bold;
}


/deep/
.favorite .is-active {
  color: #FFFFFF;
  background-color: #6637EB;
}

/deep/
.favorite .el-tabs--left.el-tabs--card .el-tabs__nav {
  border-bottom: none;
  border-radius: 0px;
}
.box-card {
  width: 90%;
}
</style>
