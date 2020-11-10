import {UserRequest as userRequest} from "@/api/Request"
import utils from "@/utils";

export default {
  getAll(pageParam, dataFilter ) {
    return userRequest({
      url : "/api/scholarship/getAll" + utils.createParam(pageParam),
      method: "POST",
      data: dataFilter,
      headers: {
        "Content-Type": "application/json"
      }
    });
  },
}
