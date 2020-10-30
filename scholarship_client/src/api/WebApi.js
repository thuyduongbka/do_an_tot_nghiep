import {AdminRequest as adminRequest} from "@/api/Request"
import utils from "@/utils";

export default {
  save(url){
    return adminRequest.post("/api/web/save?url=" + url);
  },
  getAll() {
    return adminRequest.get("/api/web/getAll");
  },
  update(data){
    return adminRequest({
      url: "api/web/update",
      method: "POST",
      data: data,
      headers: {
        "Content-Type": "application/json"
      }
    })
  }
}
