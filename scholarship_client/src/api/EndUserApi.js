import {UserRequest as userRequest} from "@/api/Request"

let baseUrl = "/api-public/end-user"
export default {
  get(id) {
    return userRequest.get(baseUrl + "/get?id=" + id);
  },
  save(data) {
    data.level = data.level.join(',');
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
  },
  addCountryFavorite(countryId){
    return userRequest.get(baseUrl + "/addCountryFavorite?countryId=" + countryId);
  },
  addSchoolFavorite(schoolId){
    return userRequest.get(baseUrl + "/addSchoolFavorite?schoolId=" + schoolId);
  },
  addMajorFavorite(majorId){
    return userRequest.get(baseUrl + "/addMajorFavorite?majorId=" + majorId);
  },
  deleteCountryFavorite(countryId){
    return userRequest.delete(baseUrl + "/deleteCountryFavorite?countryId=" + countryId);
  },
  deleteSchoolFavorite(schoolId){
    return userRequest.delete(baseUrl + "/deleteSchoolFavorite?schoolId=" + schoolId);
  },
  deleteMajorFavorite(majorId){
    return userRequest.delete(baseUrl + "/deleteMajorFavorite?majorId=" + majorId);
  },
  changePassword(changePass){
    return userRequest({
      url: baseUrl + "/changePassword",
      method: "POST",
      data: changePass,
      headers: {
        "Content-Type": "application/json"
      }
    })
  }
}
