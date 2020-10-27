<template>
  <el-container style="margin: 50px">
    <el-container>
      <el-header class="header">
        <h2> Học bổng du học New Zealand- PhD </h2>
        <p>University of Wellington</p>
      </el-header>
      <el-main class="content">
        <div class="rating"></div>
        <div class="content-field">
          <i class="icon el-icon-location"></i>
          <span class="label">Quốc gia: </span>
          <span class="value">New Zealand</span>
        </div>
        <div class="content-field">
          <i class="icon el-icon-school"></i>
          <span class="label">Trường học </span>
          <span class="value">New Zealand</span>
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


  export default {
    name: "Detail",
    components: {},
    data() {
      return {}
    }
  }
</script>
<style scoped>
  .header {
    margin-bottom: 30px;
  }

  .content {
    padding: 10px;
    border-radius: 15px;
    border: 1px solid var(--primary-color);
    text-align: left;
    margin-right: 30px;
    box-shadow: 5px 10px 8px rgba(148, 149, 174, 0.2);
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
