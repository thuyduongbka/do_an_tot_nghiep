import Router from '@/router'
import Auth from '@/security/Authentication';
import Pages from '@/router/Pages';
import Roles from "@/security/Roles";
import de from "element-ui/src/locale/lang/de";

let enableSecurity = true;

if (enableSecurity) {
  Router.beforeEach((to, from, next) => {
    // debugger
    let needAuthoritiesToViewPage = to.meta && to.meta.authorities;

    if (Auth.isAuthenticated()) {
      let currentUser = Auth.getCurrentUser();

      if (to.name == Pages.loginUser.name) {
        next();
        return;
      }

      if (currentUser.firstLogin && !currentUser.hasRole(Roles.ROLE_ADMIN)) {
        // redirect to change password first time login.
        next("/login?step=CHANGE_PASSWORD");
        return;
      }
    }

    if (needAuthoritiesToViewPage) {
      console.log("Authorize " + to.fullPath);
      if (Auth.hasAnyRoles(to.meta.authorities)) {
        /* has permission to access page */
        next();
      } else {
        if (to.meta.functionScope === "USER"){
          next(Pages.search.path);
        } else {
          next(Pages.loginAdmin.path)
        }

        // if (!Auth.isAuthenticated()) {
        //   localStorage.setItem("requestUrl", to.fullPath)
        //   console.log("Redirect to: ")
        //   console.log(to)
        //   console.log(to.meta.functionScope)
        //   next(Pages.loginAdmin.path)
        // } else {
        //   console.log(Pages.forbidden.path)
        //   next("/forbidden");
        // }
      }
    } else {

      if (Auth.isAuthenticated() && to.path == Pages.loginUser.path)
        next(Pages.redirect.path);
      next();
    }
  });
} else {
  console.log("Disable security");
}
