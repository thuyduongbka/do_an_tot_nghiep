import {UserRequest as userRequest} from "@/api/Request"

export default {
  getAll() {
    return userRequest.get("/api-public/school/list-all");
  },
}
