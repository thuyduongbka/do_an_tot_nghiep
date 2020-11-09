<template>
  <div style="width: 100%">
    <RegisterOne v-if="tab === 1" :form-data="formData" @changeTab="changeTab"></RegisterOne>
    <RegisterTwo v-if="tab === 2" :form-data="formData" @changeTab="changeTab"></RegisterTwo>
    <RegisterThree v-if="tab === 3" :form-data="formData" @changeTab="changeTab" @submit="submit"></RegisterThree>
  </div>
</template>
<script>
  import AlertService from "@/services/AlertService";
  import RegisterOne from "@/views/user/register/RegisterOne";
  import RegisterTwo from "@/views/user/register/RegisterTwo";
  import RegisterThree from "@/views/user/register/RegisterThree";

  export default {
    name: "Register",
    components: {RegisterOne, RegisterTwo, RegisterThree},
    data() {
      return {
        formData: {
          password: '',
          checkPassword: '',
          email: '',
          name: '',
          gender: null,
          phone: '',
          birthday: null,
          dateGraduation: null,
          level: '',
          country: null,
          school: null,
          major: null,
        },
        tab: 1
      }
    },
    methods: {
      changeTab(tab){
        this.tab = tab;
      },
      submit(){
        AlertService.success("uOK")
      }
    }
  }
</script>
<style scoped>

</style>
