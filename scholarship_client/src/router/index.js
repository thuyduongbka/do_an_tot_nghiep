import Vue from 'vue'
import Router from 'vue-router'
import Pages from "@/router/Pages";
import AdminRouter from "@/router/admin_router";
import UserRouter from "@/router/user_router"

import RedirectComponent from "@/components/RedirectComponent";
import LayoutUser from "@/components/layouts/LayoutUser";
import LayoutAdmin from "@/components/layouts/LayoutAdmin";
import LoginAdmin from "@/views/admin/LoginAdmin";
import Error from "@/views/common/Error";

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: "/",
      redirect: Pages.redirect.path,
      component: LayoutUser,
      children: [
        {
          path: Pages.redirect.path,
          component: RedirectComponent,
          meta: {
            title: "Redirecting",
            authorities: []
          }
        },
        ...UserRouter,
      ]
    },
    {
      path: "/admin",
      redirect: Pages.redirect.path,
      component: LayoutAdmin,
      children: [
        ...AdminRouter,
      ]
    },
    {
      ...Pages.loginAdmin,
      component : LoginAdmin,
      meta: {
        title: "Login Admin",
      }
    },
    {
      ...Pages.notFound,
      component: Error,
      meta: {
        title: "Not found",
        error404: true,
      }
    },
    {
      ...Pages.forbidden,
      component: Error,
      meta: {
        title: "Access denied",
        error403: true,
      }
    },
  ]
})
