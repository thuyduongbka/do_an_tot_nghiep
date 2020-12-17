<template>
  <el-header class="header" height="auto">
    <div class="header-el float-right">

    </div>
    <div>
      <i v-if="isCollapse" class="collapse el-icon-s-unfold" @click="isCollapse=false"></i>
      <i v-else class="collapse el-icon-s-fold" @click="isCollapse=true"></i>
    </div>

    <el-menu
      :collapse="isCollapse"
      :collapse-transition="false"
      active-text-color="#fff"
      background-color="#b69de5"
      class="el-menu-demo"
      default-active="1"
      mode="horizontal"
      router
      text-color="#fff">
      <el-menu-item index="1" route="/">
        <span>
          <img src="@/assets/logo-3.png" style="width: 150px">
        </span>
      </el-menu-item>
      <el-menu-item index="2" route="/search"><span>
        Search Scholarship
      </span></el-menu-item>

      <el-menu-item v-if="isUser" index="3" route="/compare"><span >Compare</span>
      </el-menu-item>
      <el-submenu v-if="isUser" index="4">
        <template slot="title">More</template>
        <el-menu-item route="/country" index="4-1">Country</el-menu-item>
        <el-menu-item route="/level" index="4-2">Level</el-menu-item>
        <el-menu-item route="/major" index="4-3">Major</el-menu-item>
      </el-submenu>

      <el-menu-item index="5" style="float: right">
        <a v-if="isUser" class="header-el-link" @click="logout()">
          <button class="btn btn-blue" round>Logout</button>
        </a>
        <a v-if="!isUser" class="header-el-link" href="/login">
          <button class="btn btn-white" round>Login</button>
        </a>
        <a v-if="!isUser" class="header-el-link" href="/sign-up">
          <button class="btn btn-blue" round>Signup</button>
        </a>
      </el-menu-item>
      <el-menu-item index="4" route="/profile" style="float: right">
        <span v-if="isUser" >{{
            username
          }}</span>
      </el-menu-item>
    </el-menu>
  </el-header>
</template>
<script>
import Auth from "@/security/Authentication";
import Roles from "@/security/Roles";

export default {
  name: "HeaderUser",
  data() {
    return {
      isUser: Auth.getCurrentRole() === Roles.ROLE_END_USER,
      username: Auth.getCurrentUser().name,
      isCollapse: false
    }
  },
  methods: {
    logout() {
      Auth.logout();
      location.reload();
    }
  }
}
</script>
<style scoped>
.header {
  border-bottom: 0.5px solid #6637EB;
  font-weight: bold;
  display: flex;
}

.header-el {
  height: 100%;
  display: flex;
  align-items: center;
}

.header-el > * {
  margin-right: 10px;
  margin-left: 10px;
  vertical-align: middle;
}

.header-el-link {
  color: #6637EB;
  min-width: 80px;
  transition: all .2s ease-in-out;
  text-decoration: none;
}

.header-el-link:hover {
  color: #FF275A;
  transform: scale(1.1);
}

.el-menu-demo {
  width: 100%;
}

.el-menu-demo.el-menu--collapse {
  display: none;
}

.collapse {
  font-size: 50px;
  display: none;
}

@media only screen and (max-width: 500px) {

  .header {
    font-size: 10px;
  }

  .header-el > * {
    margin-right: 5px;
    margin-left: 5px;
    vertical-align: middle;
  }

  .header-el-link {
    min-width: 0px;
  }

  img {
    width: 60px !important;
  }

  .collapse {
    display: flex;
  }
}

@media only screen and (max-width: 400px) {
  .header {
    font-size: 10px;
    display: flex;
    flex-direction: row;
  }
}
</style>
