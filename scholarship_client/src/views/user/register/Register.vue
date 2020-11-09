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
  import SchoolApi from "@/api/SchoolApi";
  import EndUserApi from "@/api/EndUserApi";
  import router from "@/router";
  import Pages from "@/router/Pages";

  export default {
    name: "Register",
    components: {RegisterOne, RegisterTwo, RegisterThree},
    data() {
      return {
        formData: {
          password: '',
          checkPassword: '',
          username: '',
          name: '',
          gender: null,
          phone: '',
          birthday: null,
          graduationDate: null,
          level: '',
          listCountryId: null,
          listSchoolId: null,
          listMajorId: null,
        },
        tab: 1
      }
    },
    methods: {
      changeTab(tab){
        this.tab = tab;
      },
      async submit(){
        try {
          await EndUserApi.save(this.formData).then(result => {
            AlertService.success("Tạo thành công");
            router.push({path: Pages.loginUser.path});
          })
        } catch (e) {
          AlertService.error("Thất bại :  " + e)
        }
      }
    }
  }
</script>
<style scoped>

</style>
