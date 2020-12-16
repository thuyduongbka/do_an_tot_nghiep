import {AdminRequest as adminRequest, UserRequest as userRequest} from "@/api/Request"
import utils from "@/utils";

let baseUrl = "/api/recommend";
export default {
  getRecommend() {
    return userRequest({
      url : baseUrl + "/get-recommend" ,
      method: "GET",
    });
  },
  getRecommendConversation(data){
    return adminRequest({
      url: baseUrl + "/get-recommend-conversation" ,
      method: "POST",
      data: data,
      headers: {
        "Content-Type": "application/json"
      }
    })
  }
}
