import {UserRequest as userRequest} from "@/api/Request"

export default {
  getAll() {
    return userRequest.get("/api-public/country/list-all");
  },
}
