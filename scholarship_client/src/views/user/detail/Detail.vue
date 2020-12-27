<template>
  <el-container v-if="scholarship != null" class="container" style="margin: 50px">
    <el-container v-loading="loading">
      <el-header class="header">
        <h2> {{ scholarship.name }} </h2>
        <p v-if="scholarship.schoolEntity">{{ scholarship.schoolEntity.name }}</p>
      </el-header>
      <el-main style="padding: 0px;">
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
            <detail-conversation :scholarship="scholarship"></detail-conversation>
          </div>

        </div>
        <div v-if="listRecommend.length>0" class="content recommend">
          <list-recommend-carousel :list-scholarship="listRecommend"></list-recommend-carousel>
        </div>
        <div class="content" v-html="scholarship.content"></div>
      </el-main>
    </el-container>
    <el-aside class="aside" style="width: 300px">
      <div class="interactive">
        <h2> {{ scholarship.moneyEntities[0].value }}</h2>
        <button v-if="!interactive || !interactive.isInListFavorite" class="btn btn-pink" @click="addFavorite()">
          <span class="icon">&#10084;</span>
          <span>Add To Favorites List</span>
        </button>
        <button v-if="interactive && interactive.isInListFavorite" class="btn btn-white-pink">
          <span class="icon">&#10084;</span>
          <span>Added To Favorites List</span>
        </button>
        <button class="btn btn-pink" @click="countClickContact()">
          &#9742;
          <span>Apply</span>
        </button>
        <button v-if="addToCompare" class="btn btn-white-pink">
          &#8651;
          <span @click="removeToCompare()">Added To Compare</span>
        </button>
        <button v-else class="btn btn-pink" >
          &#8651;
          <span @click="countCompare()">Compare</span>
          <span v-if="getIdToCompare">
            with <a :href="'/detail?id='+ getIdToCompare" target="_blank">...</a>
          </span>
        </button>
        <div style="text-align: left; margin: 20px;">
          <div class="react">
            <span class="icon">&#128065;</span>
            <span class="value">{{ scholarship ? scholarship.numberSeen : 0 }} views</span>
          </div>
          <div class="react">
            <span class="icon">&#9734;</span>
            <span
              class="value">{{ scholarship ? scholarship.rating + "/" + scholarship.numberRating : 0 }} rating</span>
          </div>
          <div class="react">
            <span class="icon"><i class="el-icon-share"></i></span>
            <span class="value">{{ scholarship ? scholarship.numberShare : 0 }} shares</span>
          </div>
          <div class="react">
            <span class="icon"><i class="el-icon-chat-square"></i> </span>
            <span class="value">{{ scholarship ? scholarship.numberComment : 0 }} commends</span>
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
      <list-recommend :list-scholarship="listRecommend"></list-recommend>
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
import ListRecommend from "@/components/scholarship/ListRecommend";
import Scholarship from "@/components/scholarship/Scholarship";
import ListRecommendCarousel from "@/components/scholarship/ListRecommendCarousel";
import DetailConversation from "@/views/user/detail/recommend/DetailConversation";

export default {
  name: "Detail",
  components: {ListRecommend, Scholarship, ListRecommendCarousel, DetailConversation},
  data() {
    return {
      scholarship: null,
      rating: 0,
      newComment: "",
      interactive: null,
      listRecommend: [],
      loading: false,
      addToCompare: false
    }
  },
  async created() {
    this.loading = true;
    this.getData();
    this.countView();
    this.loading = false;
  },
  watch: {
    'rating'(value) {
      if (value > 0)
        this.changeRating();
    }
  },
  computed: {
    getIdToCompare(){
      return localStorage.getItem('scholarshipId1');
    }
  },
  methods: {
    async getData() {
      try {
        let id = this.$route.query.id;
        let userId = Auth.getCurrentUser().endUserId;
        await ScholarshipApi.get(userId, id).then(result => {
          this.scholarship = result.scholarshipEntity;
          this.interactive = result.interactiveEntity;
          this.rating = this.interactive ? this.interactive.rating : 0
          this.addToCompare = localStorage.getItem("scholarshipId1") == result.scholarshipEntity.id;
          })
        } catch (e) {
          AlertService.error(e)
        }
        await ScholarshipApi.getRecommendSame(id).then(result => {
          this.listRecommend = result;
        })
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
        window.open(this.scholarship.applyLink, '_blank');
        try {
          let id = this.$route.query.id;
          let userId = Auth.getCurrentUser().endUserId;
          await ScholarshipInteractiveApi.countClickContact(id, userId);
        } catch (e) {
          AlertService.error(e)
        }
      },
      async createComment(){
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
          await ScholarshipInteractiveApi.addFavorite(id, true).then(result=>{
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
          await ScholarshipInteractiveApi.rating(id, userId, this.rating).then(result => {
            this.getData();
          });
        } catch (e) {
          AlertService.error(e)
        }
      },
    countCompare() {
      if (localStorage.getItem("scholarshipId1") != undefined) {
        this.$router.push({path: Pages.compare.path, query: {scholarshipId2: this.scholarship.id}});
      } else {
        localStorage.setItem("scholarshipId1", this.scholarship.id);
        this.addToCompare = true;
      }
    },
    removeToCompare(){
      localStorage.removeItem("scholarshipId1");
      this.addToCompare = false;
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
    margin-bottom: 30px;
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
  .aside {
    width: 300px;
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


  @media only screen and (max-width: 450px) {
    .container{
      display: contents;
    }
    .content {
      margin-right: 0px;
    }
    .aside {
      width: auto !important;
    }
    .recommend {
      display: none;
    }
  }
</style>
