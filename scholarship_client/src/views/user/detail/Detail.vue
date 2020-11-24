<template>
  <el-container v-if="scholarship != null" style="margin: 50px">
    <el-container>
      <el-header class="header">
        <h2> {{ scholarship.name }} </h2>
        <p v-if="scholarship.schoolEntity">{{ scholarship.schoolEntity.name }}</p>
      </el-header>
      <el-main>
        <div class="content">
          <div>
            <div class="rating">
              <div class="block">
                <el-rate
                  v-model="rating"
                  :allow-half="true"
                  :colors="['#6637EB','#F7BA2A','#FF275A']">
                </el-rate>
              </div>
            </div>
            <div v-if="scholarship.countryEntity" class="content-field">
              <i class="icon el-icon-location"></i>
              <span class="label">Quốc gia: </span>
              <span class="value">{{ scholarship.countryEntity.name }}</span>
            </div>
            <div class="content-field">
              <i class="icon el-icon-school"></i>
              <span class="label">Ngành học: </span>
              <ul>
                <li v-for="major in scholarship.majorEntities" :key="major.id">{{ major.name }}</li>
              </ul>
            </div>
            <div class="content-field">
              <i class="icon el-icon-trophy"></i>
              <span class="label">Bằng cấp: </span>
              <ul>
                <li v-for="level in scholarship.levelEntities" :key="level.id">{{ level.name }}</li>
              </ul>
            </div>
            <div class="content-field">
              <i class="icon el-icon-time"></i>
              <span class="label">Thời hạn: </span>
              <span class="value">{{ scholarship.time }}</span>
            </div>
            <div v-if="scholarship.requirementEntities.length > 0" class="content-field">
              <i class="icon el-icon-collection-tag"></i>
              <span class="label">Yêu cầu: </span>
              <ul>
                <li v-for="requirement in scholarship.requirementEntities" :key="requirement.id">{{ requirement }}</li>
              </ul>
            </div>
          </div>

        </div>

        <div class="content" v-html="scholarship.content"></div>
      </el-main>
    </el-container>
    <el-aside width="300px">
      <div class="interactive">
        <h2> {{ scholarship.moneyEntities[0].value }}</h2>
        <button v-if="!interactive || !interactive.isInListFavorite" class="btn btn-pink" @click="addFavorite()">
          <span class="icon">&#10084;</span>
          <span>Thêm vào danh sách</span>
        </button>
        <button v-if="interactive && interactive.isInListFavorite" class="btn btn-white-pink">
          <span class="icon">&#10084;</span>
          <span>Đã thêm vào danh sách</span>
        </button>
        <button class="btn btn-pink" @click="countClickContact()">
          &#9742;
          <span>Liên hệ</span>
        </button>
        <button class="btn btn-pink" @click="countCompare()">
          &#8651;
          <span>So sánh</span>
        </button>
        <div style="text-align: left; margin: 20px;">
          <div class="react">
            <span class="icon">&#128065;</span>
            <span class="value">{{ scholarship ? scholarship.numberSeen : 0 }} Lượt xem</span>
          </div>
          <div class="react">
            <span class="icon">&#9734;</span>
            <span
              class="value">{{ scholarship ? scholarship.rating + "/" + scholarship.numberRating : 0 }} Đánh giá</span>
          </div>
          <div class="react">
            <span class="icon"><i class="el-icon-share"></i></span>
            <span class="value">{{ scholarship ? scholarship.numberShare : 0 }} Lượt chia sẻ</span>
          </div>
          <div class="react">
            <span class="icon"><i class="el-icon-chat-square"></i> </span>
            <span class="value">{{ scholarship ? scholarship.numberComment : 0 }} Lượt bình luận</span>
          </div>
          <div class="content-field">
            <div v-for="mess in scholarship.commentEntities" :key="mess.id" style="margin: 10px">
              <div v-if="mess.userEntity" style="font-size: 14px; font-weight: normal; "><i
                class="el-icon-chat-square"/> {{ mess.userEntity.name }}
              </div>
              <div style="font-size: 14px; font-weight: normal; color: #6637EB;">{{ mess.message }}</div>
            </div>
            <el-input v-model="newComment" placeholder="câu hỏi ..." size="small"
                      @keyup.enter.native="createComment"></el-input>
          </div>
        </div>
      </div>

    </el-aside>
  </el-container>
</template>
<script>


import ScholarshipApi from "@/api/ScholarshipApi";
import AlertService from "@/services/AlertService";
import Auth from "@/security/Authentication";
import ScholarshipInteractiveApi from "@/api/ScholarshipInteractiveApi";
import CommentApi from "@/api/CommentApi";
import Pages from "@/router/Pages";

export default {
  name: "Detail",
  components: {},
  data() {
    return {
      scholarship: null,
      rating: 0,
      newComment: "",
      interactive: null,
    }
  },
  created() {
    this.getData();
    this.countView();
  },
  watch: {
    'rating'(value) {
      if (value > 0)
        this.changeRating();
    }
  },
    methods: {
      async getData() {
        try {
          let id = this.$route.query.id;
          let userId = Auth.getCurrentUser().endUserId;
          await ScholarshipApi.get(userId,id).then(result => {
            this.scholarship = result.scholarshipEntity;
            this.interactive = result.interactiveEntity;
            this.rating = this.interactive ? this.interactive.rating :0 ;
          })
        } catch (e) {
          AlertService.error(e)
        }
      },
      async countView() {
        try {
          let id = this.$route.query.id;
          let userId = Auth.getCurrentUser().endUserId;
          await ScholarshipInteractiveApi.countView(id, userId);
        } catch (e) {
          AlertService.error(e)
        }
      },
      async countClickContact(){
        window.location.href = this.scholarship.url;
        try {
          let id = this.$route.query.id;
          let userId = Auth.getCurrentUser().endUserId;
          await ScholarshipInteractiveApi.countClickContact(id, userId);
        } catch (e) {
          AlertService.error(e)
        }
      },
      async createComment(){
        console.log("comment")
        if (this.newComment != ""){
          try {
            let id = this.$route.query.id;
            let userId = Auth.getCurrentUser().endUserId;
            await CommentApi.create(this.newComment,id, userId).then(result => {
              this.newComment = "";
                this.getData();
            });
          } catch (e) {
            AlertService.error(e)
          }
        }
      },
      async addFavorite(){
        try {
          let id = this.$route.query.id;
          let userId = Auth.getCurrentUser().endUserId;
          await ScholarshipInteractiveApi.addFavorite(id, userId, true).then(result=>{
            this.getData();
          });
        } catch (e) {
          AlertService.error(e)
        }
      },
      async changeRating(){
        if (this.rating == null) return ;
        try {
          let id = this.$route.query.id;
          let userId = Auth.getCurrentUser().endUserId;
          await ScholarshipInteractiveApi.rating(id, userId, this.rating).then(result=>{
            this.getData();
          });
        } catch (e) {
          AlertService.error(e)
        }
      },
      countCompare(){
        this.$router.push({path: Pages.compare.path, query: {scholarshipId1: this.scholarship.id}});
      }
    }
  }
</script>
<style scoped>
  .header {
    text-align: left;
    height: auto !important;
  }
  .rating {
    text-align: right ;
  }
  /deep/
  .el-rate__icon {
    font-size: 24px;
  }
  .content {
    margin-top: 30px;
    padding: 10px;
    border-radius: 15px;
    border: 1px solid var(--primary-color);
    text-align: left;
    margin-right: 30px;
    box-shadow: 5px 10px 8px rgba(148, 149, 174, 0.2);
  }
  .content-field {
    margin: 10px;
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

  .btn {
    margin: 5px;
    width: 90%;
  }

  .interactive {
    box-shadow: 5px 10px 8px rgba(148, 149, 174, 0.2);
    font-weight: bold;
    color: #FF275A;
    padding: 5px;
    border-radius: 15px;
    border: 1px solid #FF275A;
  }

  .react > * {

    margin-left: 10px;
    margin-right: 5px;
    vertical-align: middle;
  }

  .react .icon {
    font-size: x-large;
  }

  .react .value {
    text-decoration: underline;
  }
</style>
