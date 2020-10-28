import Router from '@/router'
import Auth from '@/security/Authentication';
import Pages from '@/router/Pages';
import Roles from "@/security/Roles";

let enableSecurity = process.env.VUE_APP_ENABLE_SECURITY == 'true';

if (enableSecurity) {
  Router.beforeEach((to, from, next) => {
    let needAuthoritiesToViewPage = to.meta && to.meta.authorities;

    if (Auth.isAuthenticated()) {
      let currentUser = Auth.getCurrentUser();

      if (to.name == Pages.loginUser.name) {
        next();
        return;
      }

      if (currentUser.firstLogin && !currentUser.hasRole(Roles.SYSTEM_ADMIN)) {
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
        if (!Auth.isAuthenticated()) {
          localStorage.setItem("requestUrl", to.fullPath)
          console.log("Redirect to: ")
          console.log(to)
          next(Pages.loginUser.path);
        } else {
          next(Pages.forbidden.path);
        }
      }
    } else {

      if (Auth.isAuthenticated() && to.path == Pages.login.path)
        next(Pages.redirect.path);
      next();
    }
  });
} else {
  console.log("Disable security");
}
