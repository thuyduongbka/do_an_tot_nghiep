import {UserRequest as userRequest} from "@/api/Request"
import utils from "@/utils";

let baseUrl = "/api/comment";
export default {
  create(message, scholarshipId, userId) {
    return userRequest({
      url: baseUrl + "/create?userId=" + userId + "&scholarshipId=" + scholarshipId,
      method: "POST",
      data: message,
      headers: {
        "Content-Type": "application/json"
      }
    });
  },
  delete(id) {
    return userRequest.get(baseUrl + "/delete?id=" + id);
  },
}
