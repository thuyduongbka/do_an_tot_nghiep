<template>
  <p>Redirecting....</p>
</template>

<script>
  import Pages from "@/router/Pages";
  export default {
    name: "RedirectComponent",
    beforeMount() {
      console.log(this.$auth.getCurrentUser());
      // this.redirectToUrlWasRequested(Pages.homeUser.path);
    },

    methods: {
      redirectToUrlWasRequested(defaultPath) {
        let requestUrl = localStorage.getItem("requestUrl");
        if (requestUrl != null && requestUrl != undefined && requestUrl != "/redirect") {
          console.log("Request url: " + requestUrl);
          localStorage.removeItem("requestUrl");
          this.$router.push({path: requestUrl});
        } else {
          this.$router.push({path: defaultPath});
        }
      }
    }
  }
</script>

<style scoped>

</style>
