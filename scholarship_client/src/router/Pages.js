const userPathPrefix= "/user"
const adminPathPrefix = "/admin"

let Pages = {
  /** common pages **/
  homeUser: {name: "home-user", path: ""},
  signUp : {name: "sign-up", path: "sign-up"},
  loginUser: {name: "login-user", path: "login"},
  redirect: {name: "redirect", path: "redirect"},
  notFound: {name: "notFound", path: "/not-found"},
  forbidden: {name: "forbidden", path: "/forbidden"},
  /** user page **/
  detailScholarship: {name: "detail", path: "detail"},

  /** admin page **/
  homeAdmin: {name: "home-admin", path: ""},
  loginAdmin: {name: "login-admin", path: "/login/admin"},
}

export default Pages;
