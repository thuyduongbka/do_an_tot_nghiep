import {AdminRequest as adminRequest} from "@/api/Request"
import utils from "@/utils";

export default {
  save(email){
    return adminRequest.post("/api/admin/save?email=" + email);
  },
  getAll() {
    return adminRequest.get("/api/admin/getAll");
  },
  update(data){
    return adminRequest({
      url: "api/admin/update",
      method: "POST",
      data: data,
      headers: {
        "Content-Type": "application/json"
      }
    })
  }
}
