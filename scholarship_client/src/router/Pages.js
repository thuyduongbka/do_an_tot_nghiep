const userPathPrefix= "/user"
const adminPathPrefix = "/admin"

let Pages = {
  /** common pages **/
  homeUser: {name: "home-user", path: "/"},
  signUp : {name: "sign-up", path: "sign-up"},
  loginUser: {name: "login-user", path: "login"},

  redirect: {name: "redirect", path: "redirect"},
  notFound: {name: "notFound", path: "/not-found"},
  forbidden: {name: "forbidden", path: "/forbidden"},
  /** user page **/
  detailScholarship: {name: "detail", path: "detail"},
  search: {name: "search", path: "search"},
  compare: {name: "compare", path: "compare"},
  profile: {name: "profile", path: "profile"},

  /** admin page **/
  homeAdmin: {name: "home-admin", path: ""},
  loginAdmin: {name: "login-admin", path: "/login/admin"},
  adminManagement: {name: "admin-management", path: "admin-management"},
  majorManagement: {name: "major-management", path: "major-management"}
}

export default Pages;
