import {AdminRequest as adminRequest} from "@/api/Request"

export default {
  login(data){
    let formData = new FormData();
    formData.append("username", data.username);
    formData.append("password", data.password);
    return adminRequest.post("/login", formData);
  },
  isAuthenticated() {
    return adminRequest.get("/api/isAuthenticated");
  }
}
