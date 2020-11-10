<template>
  <div class="card" :class="showImage? 'card-image': 'card-no-image'">
    <div class="card-image" v-if="showImage">
        <img src="@/assets/example.jpg">
    </div>
    <div class="card-content">
      <span>{{formatDate(scholarship.time)}}</span>
      <p style="font-weight: bold;">{{scholarship.name}}</p>
      <span>{{scholarship.schoolEntity? scholarship.schoolEntity.name : ""}}</span>
      <p>Quốc gia: {{scholarship.countryEntity.name}}</p>
    </div>
    <div class="card-footer" >
      <div class="card-link">
        <button class="btn btn-white"> Xem thêm </button>
      </div>
      <div class="card-react" v-if="showImage" >
        <div class="card-react--item">
        <span class="card-react--cancel">
          <svg aria-hidden="true" focusable="false" data-prefix="fal" data-icon="times" class="svg-inline--fa fa-times fa-w-10 fa-lg " role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512" color="rgba(48, 50, 100, 0.5)"><path fill="currentColor" d="M193.94 256L296.5 153.44l21.15-21.15c3.12-3.12 3.12-8.19 0-11.31l-22.63-22.63c-3.12-3.12-8.19-3.12-11.31 0L160 222.06 36.29 98.34c-3.12-3.12-8.19-3.12-11.31 0L2.34 120.97c-3.12 3.12-3.12 8.19 0 11.31L126.06 256 2.34 379.71c-3.12 3.12-3.12 8.19 0 11.31l22.63 22.63c3.12 3.12 8.19 3.12 11.31 0L160 289.94 262.56 392.5l21.15 21.15c3.12 3.12 8.19 3.12 11.31 0l22.63-22.63c3.12-3.12 3.12-8.19 0-11.31L193.94 256z"></path></svg>
        </span>
        </div>
        <div class="card-react--item">
        <span class="card-react--heart">
          <svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="heart" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" color="#FFBAC9"><path fill="currentColor" d="M462.3 62.6C407.5 15.9 326 24.3 275.7 76.2L256 96.5l-19.7-20.3C186.1 24.3 104.5 15.9 49.7 62.6c-62.8 53.6-66.1 149.8-9.9 207.9l193.5 199.8c12.5 12.9 32.8 12.9 45.3 0l193.5-199.8c56.3-58.1 53-154.3-9.8-207.9z"></path></svg>
        </span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import utils from "@/utils";

  export default {
    name : "Scholarship",
    props : ["scholarship", "showImage"],
    methods: {
      formatDate(date){
        return utils.formatDate(date);
      }
    }
  }
</script>
<style>
  .card {
    margin: 30px;
    width: 90%;
    max-width: 250px;
    height: 100%;
    border-radius: 15px;
    border: 1px solid var(--primary-color);
    box-shadow: rgba(148, 149, 174, 0.2) 8px 10px 8px 0px, rgba(148, 149, 174, 0.14) 0px 3px 4px 0px, rgba(148, 149, 174, 0.12) 0px 3px 3px -2px;
    display: block;
    position: relative;
    font-size: 12px;
  }
  .card-image {
    min-height: 300px;
  }
  .card-no-image {
    min-height: 200px;
  }
  .card-image {
    position: relative;
  }
  .card-image img {
    object-fit: cover;
    width: 100%;
    max-height: 130px;
    border-radius: var(--radius-value) var(--radius-value) 0px 0px;
  }
  .card-content {
    text-align: left;
    padding: 10px;
  }
  .card-footer {
    width: 100%;
    bottom: 10px;
    position: absolute;
  }
  .card-link {
    margin: 10px;
  }
  .card-link .btn {
    width: 70%;
    padding: 5px 10px 5px 10px;
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

</style>
