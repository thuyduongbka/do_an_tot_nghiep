import Roles from "@/security/Roles";
import Pages from "@/router/Pages";

import HomeAdmin from "@/views/admin/HomeAdmin";
import AdminManagement from "../../views/admin/admin-management/AdminManagement";
import MajorManagement from "@/views/admin/major-management/MajorManagement";

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
  {
    ...Pages.majorManagement,
    component : MajorManagement,
    meta: {
      title: "Major Management",
      ...meta
    }
  },
]

export default AdminRouter;
