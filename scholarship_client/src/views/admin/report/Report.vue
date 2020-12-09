<template>
  <div>
    <p style="color: #36474F; font-weight: bold" v-if="loaded">Tổng số học bổng có sẵn: {{total}}</p>
    <div class="container">
      <bar-chart
        v-if="loaded"
        :chart-data="reportCountry"
        :options="options"/>
      <bar-chart
        v-if="loaded"
        :chart-data="reportMonth"
        :options="options"/>
    </div>
  </div>
</template>
<script>
import LineChart from "@/components/chart/LineChart";
import BarChart from "@/components/chart/BarChart";
import ScholarshipApi from "@/api/ScholarshipApi";
import AlertService from "@/services/AlertService";

export default {
  name: '',
  components: { LineChart, BarChart },
  data() {
    return {
      reportCountry: null,
      reportMonth: null,
      loaded: false,
      options: {
        responsive: true,
        maintainAspectRatio: false
      },
      total: 0
    }
  },
  async created() {
    await this.getReportCountry();
    await this.getReportMonth();
  },
  methods: {
    async getReportCountry() {
      this.loaded = false;
      try {
        await ScholarshipApi.reportByCountry().then(result => {
          let label = []
          let data = []
          this.data = result;
          for (let country of result ){
            label.push(country.name);
            data.push(country.total);
            this.total += country.total;
          }
          this.reportCountry = {
            labels: label,
            datasets: [
              {
                label: "Số Học Bổng",
                backgroundColor: "#f87979",
                data: data
              }
            ]
          }
          this.loaded = true;
        })
      } catch (e) {
        AlertService.error(e);
      }
    },
    async getReportMonth() {
      try {
        this.loaded = false;
        await ScholarshipApi.reportByMonth().then(result => {
          let label = []
          let data = []
          for (let country of result ){
            label.push(country.year + "/" + country.month);
            data.push(country.total);
          }
          this.reportMonth = {
            labels: label,
            datasets: [
              {
                label: "Số Học Bổng",
                backgroundColor: "#f87979",
                data: data
              }
            ]
          }
          this.loaded = true;
        })
      } catch (e) {
        AlertService.error(e);
      }
    }
  },
  computed: {
  }
}
</script>
