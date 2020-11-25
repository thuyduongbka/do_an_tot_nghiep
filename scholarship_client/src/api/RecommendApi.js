import {UserRequest as userRequest} from "@/api/Request"
import utils from "@/utils";

let baseUrl = "/api/recommend";
export default {
  getRecommend() {
    return userRequest({
      url : baseUrl + "/get-recommend" ,
      method: "GET",
    });
  },

}
