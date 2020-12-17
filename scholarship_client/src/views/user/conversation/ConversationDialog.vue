<template>
  <el-dialog
    :visible.sync="dialogVisible"
    class="dialog" :width="confirm?'80%':'30%'"
    top="20px">
    <div slot="title" style="border-bottom: 1px solid #6637EB; width: 70%; margin: auto">
      <div v-if="!confirm">
        <h3>Why don't you like this scholarship?</h3>
        <span style="color: #fc3737">Press (X) if you don't like anything</span>
      </div>
      <p v-else>Maybe you'll like these scholarships</p>
    </div>
    <div v-if="!confirm" class="feature">
      <p>Deadline:</p>
      <div class="feature-item">
        {{ formatDate(scholarship.time) }}
        <button-like :value.sync="feedback.deadline.dislike"></button-like>
      </div>
      <div class="feature-item" v-if="feedback.deadline.dislike">
        Appropriate deadline:
        <el-input-number size="mini" v-model="feedback.deadline.like" :min="-10" :max="10"></el-input-number>
        Month
      </div>


      <p>Country:</p>
      <div class="feature-item">
        {{ scholarship.countryEntity.name }}
        <button-like :value.sync="feedback.country.dislike"></button-like>
      </div>
      <div v-if="feedback.country.dislike" class="feature-item">Replace with:
        <el-select v-model="feedback.country.like" filterable placeholder="Select" size="mini">
          <el-option
            v-for="item in listCountry"
            :key="item[1].id"
            :label="item[1].name"
            :value="item[1].id">
          </el-option>
        </el-select>
      </div>

      <p v-if="scholarship.schoolEntity">School: </p>
      <div v-if="scholarship.schoolEntity" class="feature-item">{{ scholarship.schoolEntity.name }}
        <button-like :value.sync="feedback.school.dislike"></button-like>
      </div>
      <div v-if="feedback.school.dislike" class="feature-item">Replace with:
        <el-select v-model="feedback.school.like" filterable placeholder="Select" size="mini">
          <el-option
            v-for="item in listSchool"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
      </div>

      <p>Level:</p>
      <div class="feature-item" v-for="(level, index) in scholarship.levelEntities" :key="level.id">
        {{ level.name }}
        <button-like :name="level.name" :value="feedback.level.dislike.get(level.name)"
                     @change="changeLevel"></button-like>
      </div>
      <div class="feature-item" v-for="(level, index) in feedback.level.like" :key="level">
        {{ level }}
      </div>
      <div class="feature-item">Add new options:
        <el-select v-model="newLevel" placeholder="Select" size="mini">
          <el-option
            v-for="item in optionsLevel"
            :key="item"
            :disabled="feedback.level.dislike.has(item) || feedback.level.like.includes(item)"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
        <el-button type="primary" circle class="el-icon-plus" style="float: right"
                   @click="feedback.level.like.push(newLevel); newLevel=null;"/>
      </div>

      <p>Major:</p>
      <div v-for="(major, index) in scholarship.majorEntities" :key="major.id" class="feature-item">

        {{ major.name }}
        <button-like :name="major.id" :value="feedback.major.dislike.get(major.id)"
                     @change="changeMajor"></button-like>
      </div>
      <div v-for="(major, index) in feedback.major.like" :key="major" class="feature-item">
        {{ listMajor.get(major).name }}
      </div>
      <div class="feature-item">Add new options:
        <el-select v-model="newMajor" filterable placeholder="Select" size="mini">
          <el-option
            v-for="item in listMajor"
            :key="item[1].id"
            :label="item[1].name"
            :value="item[1].id"
            :disabled="feedback.major.dislike.has(item[1].id)">
          </el-option>
        </el-select>
        <el-button type="primary" circle class="el-icon-plus" style="float: right"
                   @click="feedback.major.like.push(newMajor); newMajor=null;"/>
      </div>
    </div>
    <div v-else>
      <list-recommend :list-scholarship="listRecommend"></list-recommend>
    </div>
    <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">Cancel</el-button>
      <el-button v-if="!confirm" style="background-color: #6637EB; color: #FFFFFF" @click="handleSubmit()">Confirm</el-button>
    </span>
  </el-dialog>
</template>
<script>
import utils from "@/utils";
import ButtonLike from "@/views/user/conversation/ButtonLike";
import CountryApi from "@/api/CountryApi";
import AlertService from "@/services/AlertService";
import SchoolApi from "@/api/SchoolApi";
import MajorApi from "@/api/MajorApi";
import RecommendApi from "@/api/RecommendApi";
import ListRecommend from "@/components/scholarship/ListRecommendCarousel";
import Auth from "@/security/Authentication";
import ScholarshipInteractiveApi from "@/api/ScholarshipInteractiveApi";

export default {
  name: "ConversationDialog",
  props: ['show', 'scholarship'],
  components: {ButtonLike, ListRecommend},
  data() {
    return {
      newLevel: null,
      newMajor: null,
      dialogVisible: this.show,
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
      confirm: false,
    };
  },
  created() {
    this.reset();
    this.getData();
  },
  watch: {
    scholarship(){
      this.reset();
      this.countView();
    },
    dialogVisible(val) {
      this.$emit("update:show", val);
    },
    show(val) {
      this.dialogVisible = val;
    },
  },
  methods: {
    reset(){
      this.confirm = false;
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
    formatDate(date) {
      return utils.formatDate(date);
    },
    async handleSubmit(){
      let listLevelDislike = [];
      this.feedback.level.dislike.forEach((value,key)=>{
        if (value) listLevelDislike.push(key);
      })
      let listMajorDislike = [];
      this.feedback.major.dislike.forEach((value,key)=>{
        if (value) listMajorDislike.push(key);
      })
      let deadline = new Date(this.scholarship.time)
      let data = {
        scholarshipId : this.scholarship.id,
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
          this.confirm = true;

        })
      } catch (e){
        this.dialogVisible = false;
      }

    },
    changeLevel(name, val) {
      this.feedback.level.dislike.set(name, val);
    },
    changeMajor(name, val) {
      this.feedback.major.dislike.set(name, val);
    },
    async countView() {
      try {
        let id = this.scholarship.id;
        let userId = Auth.getCurrentUser().endUserId;
        await ScholarshipInteractiveApi.countView(id, userId);
      } catch (e) {
        console.log(e);
      }
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

.feature {
  text-align: left;
  padding: 10px;
  cursor: pointer;
  font-size: 12px;
}

.feature .feature-item {
  padding: 12px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  transition: all .2s ease-in-out;;
}

.feature .feature-item:hover {
  box-shadow: 0 2px 12px 0 #b69de5;
  transform: scale(1.02);
}

/deep/
.el-button.is-circle {
  padding: 4px;
}

/deep/
.el-dialog__body {
  padding-top: 0px;
}
/deep/
.el-select {
  width: 50%;
}
/deep/
.el-input-number {
  max-width: 100px;
}
/deep/
.el-dialog {
  min-width: 375px;
}
</style>
