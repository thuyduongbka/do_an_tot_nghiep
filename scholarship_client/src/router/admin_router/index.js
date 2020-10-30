import Roles from "@/security/Roles";
import Pages from "@/router/Pages";

import HomeAdmin from "@/views/admin/HomeAdmin";
import AdminManagement from "../../views/admin/admin-management/AdminManagement";

let meta = {
  authorities : [Roles.ROLE_ADMIN],
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
    ...Pages.adminManagement,
    component : AdminManagement,
    meta: {
      title: "Admin Management",
      ...meta
    }
  },
]

export default AdminRouter;
