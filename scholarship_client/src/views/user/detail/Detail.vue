<template>
  <el-container style="margin: 50px">
    <el-container>
      <el-header class="header">
        <h2> {{scholarship.name}} </h2>
        <p>{{scholarship.schoolEntity.name}}</p>
      </el-header>
      <el-main class="content">
        <div class="rating"></div>
        <div class="content-field">
          <i class="icon el-icon-location"></i>
          <span class="label">Quốc gia: </span>
          <span class="value">{{scholarship.schoolEntity.name}}</span>
        </div>
        <div class="content-field">
          <i class="icon el-icon-school"></i>
          <span class="label">Ngành học: </span>
          <ul>
            <li v-for="major in scholarship.majorEntities" :key="major.id">{{major.name}}</li>
          </ul>
        </div>
        <div class="content-field">
          <i class="icon el-icon-school"></i>
          <span class="label">Bằng cấp: </span>
          <ul>
            <li v-for="level in scholarship.levelEntities" :key="level.id">{{level.name}}</li>
          </ul>
        </div>
        <div class="content-field">
          <i class="icon el-icon-location"></i>
          <span class="label">Thời hạn: </span>
          <span class="value">{{scholarship.time}}</span>
        </div>
        <div class="content-field">
          <i class="icon el-icon-school"></i>
          <span class="label">Yêu cầu: </span>
          <ul>
            <li v-for="requirement in scholarship.requirementEntities" :key="requirement.id">{{requirement.name}}</li>
          </ul>
        </div>

      </el-main>
    </el-container>
    <el-aside class="interactive" width="300px">

      <h2> 100% </h2>
      <button class="btn btn-pink">
        <span style="font-size: large; vertical-align: middle">&#10084;</span>
        <span>Thêm vào yêu thích</span>
      </button>
      <button class="btn btn-pink">
        &#9742;
        <span>Liên hệ</span>
      </button>
      <button class="btn btn-pink">
        &#8651;
        <span>So sánh</span>
      </button>
      <div style="text-align: left; margin: 20px; font-size: large">
        <div class="react">
          <span class="icon">&#128065;</span>
          <span class="value">12 Lượt xem</span>
        </div>
        <div class="react">
          <span class="icon">&#10084;</span>
          <span class="value">12 Lượt yêu thích</span>
        </div>
        <div class="react">
          <span class="icon"><i class="el-icon-share"></i></span>
          <span class="value">12 Lượt chia sẻ</span>
        </div>
        <div class="react">
          <span class="icon"><i class="el-icon-chat-square"></i> </span>
          <span class="value">12 Lượt bình luận</span>
        </div>
      </div>
    </el-aside>
  </el-container>
</template>
<script>


  import ScholarshipApi from "@/api/ScholarshipApi";
  import AlertService from "@/services/AlertService";

  export default {
    name: "Detail",
    components: {},
    data() {
      return {
        scholarship: null,
      }
    },
    mounted() {
      this.getData();
    },
    methods: {
      async getData() {
        try {
          let id = this.$route.params.id;
          await ScholarshipApi.get(id).then(result => {
            this.scholarship = result;
          })
        } catch (e) {
          AlertService.error(e)
        }
      }
    }
  }
</script>
<style scoped>
  .header {
    margin-bottom: 30px;
    text-align: left;
    min-height: 70px;
  }

  .content {
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
    margin-right: 20px;
    vertical-align: middle;
  }

  .react .icon {
    font-size: x-large;
  }

  .react .value {
    text-decoration: underline;
  }
</style>
