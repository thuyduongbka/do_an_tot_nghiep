import Roles from "@/security/Roles";
import Pages from "@/router/Pages";

import HomeUser from "@/views/user/home/HomeUser";
import LoginUser from "@/views/user/LoginUser";
import Detail from "@/views/user/detail/Detail";

let meta = {
  authorities : [Roles.USER],
  functionScope : "USER"
}
let UserRouter = [
  {
    ...Pages.homeUser,
    component : HomeUser,
    meta: {
      title: "Home User",
      ...meta
    }
  },
  {
    ...Pages.detailScholarship,
    component : Detail,
    meta: {
      title: "Detail Scholarship",
      ...meta
    }
  },
  {
    ...Pages.loginUser,
    component : LoginUser,
    meta: {
      title: "Login User",
      ...meta
    }
  }
]

export default UserRouter;
