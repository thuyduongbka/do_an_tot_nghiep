<template>
  <div class="card" :style="showInteractive?'':'margin: auto; margin-bottom: 20px;'">
    <div class="card-image" v-if="showImage">
      <img :src="scholarship.urlImage"/>
      <img v-if="!scholarship.urlImage" src="@/assets/example.png"/>
    </div>
    <div class="card-content">
      <span>{{formatDate(scholarship.time)}}</span>
      <p style="font-weight: bold;"><a :href="'/detail?id=' + scholarship.id">{{scholarship.name}}</a></p>
      <span>{{scholarship.schoolEntity? scholarship.schoolEntity.name : ""}}</span>
      <p>Quốc gia: {{scholarship.countryEntity.name}}</p>
    </div>
    <div class="card-footer">
      <div class="card-react" v-if="showInteractive">
        <div class="card-react--item">
          <el-button circle icon="el-icon-close"></el-button>
        </div>
        <div class="card-react--item">
          <a :href="'/detail?id=' + scholarship.id">
            <el-button style="color: #FF275A" circle icon="el-icon-s-promotion"></el-button>
          </a>

        </div>
      </div>
    </div>
  </div>
</template>
<script>
import utils from "@/utils";
import Pages from "@/router/Pages";
import Auth from "@/security/Authentication";
import ScholarshipInteractiveApi from "@/api/ScholarshipInteractiveApi";
import AlertService from "@/services/AlertService";

export default {
  name: "Scholarship",
  props: ["scholarship", "showImage", "showInteractive"],
  data(){
    return {
      isFavorite: false
    }
  },
  methods: {
    formatDate(date) {
      return utils.formatDate(date);
    },
    detail() {
      this.$router.push({name: Pages.detailScholarship.name, query: {id: this.scholarship.id}});
    },
    async addFavorite(){
      try {
        let id = this.scholarship.id;
        await ScholarshipInteractiveApi.addFavorite(id,true).then(result=>{
          if (result === true) this.isFavorite = true;
        });
      } catch (e) {
        AlertService.error(e)
      }
    },
  }
  }
</script>
<style>
  .card {
    margin: 20px;
    width: 90%;
    max-width: 300px;
    height: 100%;
    border-radius: 10px;
    /*border: 1px solid var(--primary-color);*/
    box-shadow: rgba(148, 149, 174, 0.2) 8px 10px 8px 0px, rgba(148, 149, 174, 0.14) 0px 3px 4px 0px, rgba(148, 149, 174, 0.12) 0px 3px 3px -2px;
    display: block;
    position: relative;
    font-size: 12px;
    background-color: white;
  }
  .card-image {
    position: relative;
    height: 200px;
  }
  .card-image img {
    object-fit: cover;
    width: 100%;
    max-height: 200px;
    border-radius: 0px 2px 0px 2px;
  }
  .card-content {
    text-align: left;
    padding: 10px;
    height: 160px;
  }

  .card-footer {
    width: 100%;
    bottom: 10px;
    position: absolute;
  }

  .card-link {
    margin: 10px;
  }

  .card-link .btn:hover {
    width: 80%;
  }

  .card-link .btn {
    width: 70%;
    padding: 5px 10px 5px 10px;
    transition: width 2s;
    cursor: pointer;
  }

  .card-react {
    display: flex;
    justify-content: center;
    padding: 4px;
  }
  .card-react--item {
    margin-left: 10px;
    margin-right: 10px;
    position: relative;
    background-color: rgb(255, 255, 255);
    box-shadow: 0px 3px 5px -1px rgba(148, 149, 174,0.2), 0px 6px 10px 0px rgba(148, 149, 174,0.14), 0px 1px 18px 0px rgba(148, 149, 174,0.12);
    border-radius: 50%;
    width: 40px;
    height: 40px;
  }
  .card-react--heart svg {
    width: 1em;
    vertical-align: -0.7em;
    display: inline-block;
  }
  .card-react--cancel svg {
    width: 1em;
    vertical-align: -1em;
    display: inline-block;
  }
  @media only screen and (max-width: 1000px) {
    .card{
      margin: 10px;

    }
  }

</style>
