import {UserRequest as userRequest} from "@/api/Request"
import utils from "@/utils";

let baseUrl = "/api/interactive";
export default {
  countView(scholarshipId, userId){
    return userRequest.get(baseUrl + "/countView?userId=" + userId + "&scholarshipId=" + scholarshipId );
  },
  countClickContact(scholarshipId, userId){
    return userRequest.get(baseUrl + "/countClickContact?userId=" + userId + "&scholarshipId=" + scholarshipId );
  },
  countCompare(scholarshipId, userId){
    return userRequest.get(baseUrl + "/countCompare?userId=" + userId + "&scholarshipId=" + scholarshipId );
  },
  addFavorite(scholarshipId, favorite){
    return userRequest.get(baseUrl + "/addFavorite?scholarshipId=" + scholarshipId + "&isFavorite=" + favorite);
  },
  like(scholarshipId, userId, like){
    return userRequest.get(baseUrl + "/like?userId=" + userId + "&scholarshipId=" + scholarshipId + "&isLiked=" + like );
  },
  rating(scholarshipId, userId, rating){
    return userRequest.get(baseUrl + "/rating?userId=" + userId + "&scholarshipId=" + scholarshipId + "&rating=" + rating );
  },
  listAllFavorite(){
    return userRequest.get(baseUrl + "/listAllFavorite");
  }
}
