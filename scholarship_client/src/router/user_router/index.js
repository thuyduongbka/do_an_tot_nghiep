import Roles from "@/security/Roles";
import Pages from "@/router/Pages";

import HomeUser from "@/views/user/home/HomeUser";
import LoginUser from "@/views/user/LoginUser";
import Detail from "@/views/user/detail/Detail";
import Search from "@/views/user/search/Search";
import Register from "@/views/user/register/Register";
import Compare from "@/views/user/compare/Compare";
import Profile from "@/views/user/profile/Profile";
import ListCountry from "@/views/user/list/ListCountry";
import ListLevel from "@/views/user/list/ListLevel";
import ListMajor from "@/views/user/list/ListMajor";

let meta = {
  authorities : [Roles.ROLE_END_USER],
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
    ...Pages.search,
    component : Search,
    meta: {
      title: "Search User",
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
    ...Pages.compare,
    component : Compare,
    meta: {
      title: "Compare Scholarship",
      ...meta
    }
  },
  {
    ...Pages.profile,
    component : Profile,
    meta: {
      title: "profile user",
      ...meta
    }
  },
  {
    ...Pages.country,
    component : ListCountry,
    meta: {
      title: "list country",
      ...meta
    }
  },
  {
    ...Pages.level,
    component : ListLevel,
    meta: {
      title: "list level",
      ...meta
    }
  },
  {
    ...Pages.major,
    component : ListMajor,
    meta: {
      title: "list major",
      ...meta
    }
  },
  {
    ...Pages.signUp,
    component : Register,
    meta: {
      title: "Register User",
    }
  },
  {
    ...Pages.loginUser,
    component : LoginUser,
    meta: {
      title: "Login User",
    }
  }
]

export default UserRouter;
