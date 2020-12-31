<template>
  <div>
    <div v-if="scholarship.countryEntity" class="content-field">
      <span class="label">
        <i class="icon el-icon-location"></i>
        Country:
      </span>
      <el-select v-model="feedback.listCountryLike"
                 class="value" filterable multiple placeholder="Select"
                 size="mini">
        <el-option
          v-for="item in listCountry"
          :key="item.id"
          :label="item.name"
          :value="item.id">
        </el-option>
      </el-select>
    </div>
    <div v-if="scholarship.schoolEntity" class="content-field">
      <span class="label">
        <i class="icon el-icon-school"></i>
        School:
      </span>
      <el-select v-model="feedback.listSchoolLike"
                 class="value" filterable multiple placeholder="Select"
                 size="mini">
        <el-option
          v-for="item in listSchool"
          :key="item.id"
          :label="item.name"
          :value="item.id">
        </el-option>
      </el-select>
    </div>
    <div class="content-field">
      <span class="label"><i class="icon el-icon-notebook-1"></i>Major:</span>
      <el-select v-model="feedback.listMajorLike" class="value" filterable multiple placeholder="Select" size="mini">
        <el-option
          v-for="item in listMajor"
          :key="item.id"
          :label="item.name"
          :value="item.id">
        </el-option>
      </el-select>
    </div>
    <div class="content-field">
      <span class="label"><i class="icon el-icon-trophy"></i>Level: </span>
      <el-select v-model="feedback.listLevelLike" class="value" filterable multiple placeholder="Select" size="mini">
        <el-option
          v-for="item in optionsLevel"
          :key="item"
          :label="item"
          :value="item">
        </el-option>
      </el-select>
    </div>
    <div class="content-field">
      <span class="label"> <i class="icon el-icon-time"></i>Deadline: </span>
      <el-date-picker v-model="feedback.appropriateTime"
                      class="value"
                      placeholder="Pick a day"
                      type="date" size="mini">
      </el-date-picker>
    </div>
    <div v-if="checkChange.time || feedback.countryDislike || checkChange.country > 0 || feedback.schoolDislike || checkChange.school > 0 || this.checkChange.major || this.checkChange.level"
         style="text-decoration: underline; text-align: end; padding: 5px; cursor: pointer "
         ><span @click="handleSubmit()">See more based on feedback</span>
    </div>
    <recommend-dialog :list-recommend="listRecommend" :show.sync="showRecommend"/>
  </div>

</template>
<script>
import ButtonDislike from "@/views/user/detail/recommend/ButtonDislike";
import CountryApi from "@/api/CountryApi";
import AlertService from "@/services/AlertService";
import SchoolApi from "@/api/SchoolApi";
import MajorApi from "@/api/MajorApi";
import RecommendApi from "@/api/RecommendApi";
import RecommendDialog from "@/views/user/detail/recommend/RecommendDialog";

let checker = (arr, target) => target.every(v => arr.includes(v.id));
let checkerName = (arr, target) => target.every(v => arr.includes(v.name));
export default {
  props: ['show', 'scholarship'],
  components: {ButtonDislike, RecommendDialog},
  data() {
    return {
      showRecommend: false,
      optionsLevel: ["Bachelor",
        "Fellowship",
        "Master",
        "PHD",
        "Postdoctoral",
        "Postgraduate",
        "Research"],
      listCountry: new Map(),
      listSchool: [],
      listMajor: new Map(),
      feedback: null,
      listRecommend: [],
      checkChange: {
        country: 0,
        school:0,
        level: 0,
        major:0,
        time: 0
      }
    };
  },
  created() {
    this.reset();
    this.getData();
  },
  watch: {
    'feedback.listCountryLike'(val){
      if (val.includes(this.scholarship.countryEntity.id)){
        this.feedback.countryDislike = false;
        this.checkChange.country = val.length-1;
      } else {
        this.feedback.countryDislike = true;
        this.checkChange.country = val.length;
      }
    },
    'feedback.listSchoolLike'(val){
      if (val.includes(this.scholarship.schoolEntity.id)){
        this.feedback.schoolDislike = false;
        this.checkChange.school = val.length-1;
      } else {
        this.feedback.schoolDislike = true;
        this.checkChange.school = val.length;
      }
    },
    'feedback.listMajorLike'(val){
      if (checker(val, this.scholarship.majorEntities) && val.length == this.scholarship.majorEntities.length ){
        this.checkChange.major = false;
      } else {
        this.checkChange.major = true;
      }
    },
    'feedback.listLevelLike'(val){
      if (checkerName(val, this.scholarship.levelEntities) && val.length == this.scholarship.levelEntities.length ){
        this.checkChange.level = false;
      } else {
        this.checkChange.level = true;
      }
    },
    'feedback.appropriateTime'(val){
      let d1= new Date(val);
      let d2 = new Date(this.scholarship.time);
      if (d1.getFullYear() == d2.getFullYear() && d1.getMonth() == d2.getMonth() && d1.getDate() == d2.getDate()){
        this.checkChange.time = false;
      } else {
        this.checkChange.time = true;
      }
    }
  },
  methods: {
    reset() {
      this.feedback = {
        scholarshipId: this.scholarship.id,
        appropriateTime: new Date(this.scholarship.time),
        countryDislike: false,
        schoolDislike: false,
        listLevelDislike: [],
        listMajorDislike: [],

        listCountryLike: [],
        listSchoolLike: [],
        listLevelLike: [],
        listMajorLike: [],
      }
      if (!this.scholarship) return;
      this.feedback.listCountryLike.push(this.scholarship.countryEntity.id)
      this.feedback.listSchoolLike.push(this.scholarship.schoolEntity.id)
      for (let level of this.scholarship.levelEntities) {
        this.feedback.listLevelLike.push(level.name)
      }
      for (let major of this.scholarship.majorEntities) {
        this.feedback.listMajorLike.push(major.id)
      }
    },
    async handleSubmit() {
      this.feedback.listLevelDislike = []
      for (let level of this.scholarship.levelEntities) {
        if (!this.feedback.listLevelLike.includes(level.name)) {
          this.feedback.listLevelDislike.push(level.name);
        }
      }
      this.feedback.listMajorDislike = []
      for (let major of this.scholarship.majorEntities) {
        if (!this.feedback.listMajorLike.includes(major.id)) {
          this.feedback.listMajorDislike.push(major.id);
        }
      }
      try {
        await RecommendApi.getRecommendConversation(this.feedback).then(result => {
          this.listRecommend = result;
          this.showRecommend = true;
        })
      } catch (e) {
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
        this.listSchool = [];
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
<style scoped>
.content-field {
  margin: 10px;
  padding: 5px;
  display: flex;
}

.content-field:hover {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.content-field > * {
  margin-right: 10px;
  margin-left: 10px;
}

.content-field .label {
  font-weight: bold;
  width: 30%;
  max-width: 150px;
}

.content-field .value {
  width: 70%;
  /*min-width: 500px;*/
}

.content-field .icon {
  font-weight: bold;
  font-size: x-large;
}

/deep/
.el-button.is-circle {
  padding: 4px;
}

.feature-item {
  margin-left: 20px;
}

.label i {
  margin-right: 10px;
}

/deep/
.el-tag.el-tag--info {
  background-color: #f4f4f5;
  border-color: #6637eb;
  color: #6637eb;
}

/deep/
.el-tag.el-tag--info .el-tag__close {
  color: #e4e4e4;
}

/deep/
.el-select .el-tag__close.el-icon-close {
  background-color: #6637eb;
}
</style>
