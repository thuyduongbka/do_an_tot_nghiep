import {UserRequest as userRequest} from "@/api/Request"

export default {
  getAll() {
    return userRequest.get("/api-public/major/list-all");
  },
  getAllChild() {
    return userRequest.get("/api-public/major/list-all-child");
  },
  getMajorByParent(parentId) {
    return userRequest.get("/api-public/major/list-major-by-parent?parentId="+ parentId);
  },
  updateParent(id, parentId) {
    return userRequest.get("/api-public/major/update-parent?majorId=" + id + "&parentId="+ parentId);
  },
  deleteParent(id, parentId) {
    return userRequest.get("/api-public/major/delete-parent?majorId=" + id + "&parentId="+ parentId);
  },
  create(name) {
    return userRequest.get("/api-public/major/create?name="+ name);
  },
}
