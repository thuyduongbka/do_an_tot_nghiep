import {UserRequest as userRequest} from "@/api/Request"

let baseUrl = "/api-public/end-user"
export default {
  getAll(id) {
    return userRequest.get(baseUrl + "/get?id=" + id);
  },
  save(data) {
    return userRequest({
      url: baseUrl + "/save",
      method: "POST",
      data: data,
      headers: {
        "Content-Type": "application/json"
      }
    })
  },
  update(data) {
    return userRequest({
      url: baseUrl + "/update",
      method: "POST",
      data: data,
      headers: {
        "Content-Type": "application/json"
      }
    })
  }
}
