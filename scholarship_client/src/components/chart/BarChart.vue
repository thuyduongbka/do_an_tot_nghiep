<script>
import { Bar } from "vue-chartjs";

export default {
  extends: Bar,
  props: {
    chartData: {
      type: Object,
      default: null
    },
    options: {
      type: Object,
      default: null
    }
  },
  mounted() {
    console.log(this.chartData)
    this.renderChart(this.chartData,
      {responsive: true, maintainAspectRatio: false}
    );
  }
};
</script>
