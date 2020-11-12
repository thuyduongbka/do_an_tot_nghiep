import {UserRequest as userRequest} from "@/api/Request"
import utils from "@/utils";

let baseUrl = "/api/scholarship";
export default {
  getAll(pageParam, dataFilter ) {
    return userRequest({
      url : baseUrl + "/getAll" + utils.createParam(pageParam),
      method: "POST",
      data: dataFilter,
      headers: {
        "Content-Type": "application/json"
      }
    });
  },
  get(userId,scholarshipId){
    return userRequest.get(baseUrl + "/get?userId=" + userId + "&scholarshipId=" + scholarshipId );
  },
}
