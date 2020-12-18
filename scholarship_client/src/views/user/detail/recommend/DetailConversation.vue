<template>
  <div>
    <div v-if="scholarship.countryEntity" class="content-field">
      <i class="icon el-icon-location"></i>
      <span class="label">Country: </span>
      <span class="value">
        <router-link :to="'/country?id='+scholarship.countryEntity.id">
          {{ scholarship.countryEntity.name }}
        </router-link>
      </span>
      <button-dislike :value.sync="feedback.country.dislike"></button-dislike>
      <span v-if="feedback.country.dislike" class="feature-item">Replace with:
        <el-select v-model="feedback.country.like" filterable placeholder="Select" size="mini">
          <el-option
            v-for="item in listCountry"
            :key="item[1].id"
            :label="item[1].name"
            :value="item[1].id">
          </el-option>
        </el-select>
      </span>
      <div v-if="feedback.country.dislike" style="margin-top: 5px;">
        <span v-if="scholarship.schoolEntity" class="feature-item"> School: {{ scholarship.schoolEntity.name }}
         <span class="feature-item">Replace with:
          <el-select v-model="feedback.school.like" filterable placeholder="Select" size="mini">
            <el-option
              v-for="item in listSchool"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </span>
        </span>

      </div>
    </div>
    <div class="content-field">
      <i class="icon el-icon-school"></i>
      <span class="label">Major: </span>
      <ul>
        <li v-for="major in scholarship.majorEntities" :key="major.id">
          <router-link :to="'/major?id='+major.id">
            {{ major.name }}
          </router-link>
          <button-dislike :name="major.id" :value="feedback.major.dislike.get(major.id)"
                          @change="changeMajor"></button-dislike>

        </li>
      </ul>
    </div>
    <div class="content-field">
      <i class="icon el-icon-trophy"></i>
      <span class="label">Level: </span>
      <ul>
        <li v-for="level in scholarship.levelEntities" :key="level.id">
          <router-link :to="'/level?id='+level.name">
            {{ level.name }}
          </router-link>
          <button-dislike :name="level.name" :value="feedback.level.dislike.get(level.name)"
                          @change="changeLevel"></button-dislike>

        </li>
      </ul>
    </div>
    <div class="content-field">
      <i class="icon el-icon-time"></i>
      <span class="label">Deadline: </span>
      <span class="value">{{ scholarship.time }}
        <button-dislike :value.sync="feedback.deadline.dislike"></button-dislike>
        <span v-if="feedback.deadline.dislike" class="feature-item">
          Appropriate deadline:
          <el-input-number v-model="feedback.deadline.like" :max="10" :min="-10" size="mini"></el-input-number>
          Month
        </span>
      </span>
    </div>
    <div v-if="scholarship.requirementEntities.length > 0" class="content-field">
      <i class="icon el-icon-collection-tag"></i>
      <span class="label">Yêu cầu: </span>
      <ul>
        <li v-for="requirement in scholarship.requirementEntities" :key="requirement.id">{{ requirement }}</li>
      </ul>
    </div>
    <div v-if="feedback.deadline.dislike || feedback.country.dislike || checkChange"
         style="text-decoration: underline; text-align: end; padding: 5px; cursor: pointer "
         @click="handleSubmit()">See more based on feedback
    </div>
    <recommend-dialog :list-recommend="listRecommend" :show.sync="showRecommend"/>
  </div>

</template>
<script>
import ButtonDislike from "@/views/user/detail/recommend/ButtonDislike";
import utils from "@/utils";
import CountryApi from "@/api/CountryApi";
import AlertService from "@/services/AlertService";
import SchoolApi from "@/api/SchoolApi";
import MajorApi from "@/api/MajorApi";
import RecommendApi from "@/api/RecommendApi";
import RecommendDialog from "@/views/user/detail/recommend/RecommendDialog";

export default {
  props: ['show', 'scholarship'],
  components: {ButtonDislike, RecommendDialog},
  data() {
    return {
      newLevel: null,
      newMajor: null,
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
      checkChange: 0
    };
  },
  created() {
    this.reset();
    this.getData();
  },
  watch: {
    'feedback.country.dislike'(val) {
      if (val) {
        this.feedback.school.dislike = true;
      }
    }
  },
  methods: {
    reset() {
      this.feedback = {
        deadline: {
          dislike: false,
          like: null,
        },
        country: {
          dislike: false,
          like: null,
        },
        school: {
          dislike: false,
          like: null,
        },
        level: {
          dislike: new Map(),
          like: [],
        },
        major: {
          dislike: new Map(),
          like: [],
        },
      }
      if (!this.scholarship) return;
      for (let level of this.scholarship.levelEntities) {
        this.feedback.level.dislike.set(level.name, false);
      }
      for (let major of this.scholarship.majorEntities) {
        this.feedback.major.dislike.set(major.id, false);
      }
    },
    async handleSubmit() {
      let listLevelDislike = [];
      this.feedback.level.dislike.forEach((value, key) => {
        if (value) listLevelDislike.push(key);
      })
      let listMajorDislike = [];
      this.feedback.major.dislike.forEach((value, key) => {
        if (value) listMajorDislike.push(key);
      })
      let deadline = new Date(this.scholarship.time)
      let data = {
        scholarshipId: this.scholarship.id,
        countryDislike: this.feedback.country.dislike,
        countryLikeId: this.feedback.country.like,
        schoolDislike: this.feedback.school.dislike,
        schoolLikeId: this.feedback.school.like,
        appropriateTime: new Date(deadline.setMonth(deadline.getMonth() + this.feedback.deadline.like)),
        listLevelLike: this.feedback.level.like,
        listLevelDislike: listLevelDislike,
        listMajorLike: this.feedback.major.like,
        listMajorDislike: listMajorDislike
      }
      try {
        await RecommendApi.getRecommendConversation(data).then(result => {
          this.listRecommend = result;
          this.showRecommend = true;
        })
      } catch (e) {
      }
    },
    formatDate(date) {
      return utils.formatDate(date);
    },
    changeLevel(name, val) {
      this.feedback.level.dislike.set(name, val);
      if (val) this.checkChange += 1
      else this.checkChange -= 1;
    },
    changeMajor(name, val) {
      this.feedback.major.dislike.set(name, val);
      if (val) this.checkChange += 1
      else this.checkChange -= 1;
    },
    async getData() {
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
        this.listSchool = [];
        AlertService.error(e)
      }
      try {
        await MajorApi.getAll().then(result => {
          for (let r of result) {
            this.listMajor.set(r.id, r)
          }
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

</style>
