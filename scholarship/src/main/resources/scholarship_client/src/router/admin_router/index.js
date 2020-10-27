import Roles from "@/security/Roles";
import Pages from "@/router/Pages";

import LoginAdmin from "@/views/admin/LoginAdmin";
import HomeAdmin from "@/views/admin/HomeAdmin";

let meta = {
  authorities : [Roles.ADMIN],
  functionScope : "ADMIN"
}
let AdminRouter = [
  {
    ...Pages.homeAdmin,
    component : HomeAdmin,
    meta: {
      title: "Home Admin",
      ...meta
    }
  },
  {
    ...Pages.loginAdmin,
    component : LoginAdmin,
    meta: {
      title: "Login Admin",
      ...meta
    }
  }
]

export default AdminRouter;
