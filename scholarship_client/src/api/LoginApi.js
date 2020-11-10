import {AdminRequest as adminRequest, UserRequest as userRequest} from "@/api/Request"

export default {
  login(data){
    let formData = new FormData();
    formData.append("username", data.username);
    formData.append("password", data.password);
    return adminRequest.post("/login", formData);
  },
  loginUser(data){
    let formData = new FormData();
    formData.append("username", data.username);
    formData.append("password", data.password);
    return userRequest.post("/api-public/end-user/login", formData);
  },
  isAuthenticated() {
    return adminRequest.get("/api/isAuthenticated");
  }
}
