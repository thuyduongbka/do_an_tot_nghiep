import Roles from "@/security/Roles";

const accessKey = 'access-token';
const currentUserKey = "current-user";
let accessTokenInMemory = null;
let userCodeJson = localStorage.getItem(currentUserKey);
let userInMemory = userCodeJson != null ? JSON.parse(userCodeJson) : null;

function getToken() {
  return localStorage.getItem(accessKey);
}

function setToken(token) {
  accessTokenInMemory = token;
  if(token !== localStorage.getItem(accessKey)) console.log('change token');
  return localStorage.setItem(accessKey, token);
}

function removeToken() {
  accessTokenInMemory = null;
  localStorage.removeItem(accessKey);
}

function setCurrentUser(currentUser) {
  currentUser.defaultMall = "";
  userInMemory = JSON.parse(JSON.stringify(currentUser));
  localStorage.setItem(currentUserKey, JSON.stringify(currentUser));
  return getCurrentUser();
}

function removeCurrentUser() {
  localStorage.removeItem(currentUserKey);
}

function getCurrentUser() {
  let currentUser = {
    ...JSON.parse(localStorage.getItem(currentUserKey)),
    hasRole: function (checkRole) {
      return this.roles.some(role => role.roleName === checkRole);
    },
    hasAnyRoles: hasAnyRoles,
    getDefaultMall: function () {
      return this.defaultMall;
    },
  };
  return currentUser;
}

function isAuthenticated() {
  return getToken() != undefined && getToken() != null && getCurrentUser() != null && getCurrentUser() != undefined;
}

function getUserRoles() {
  let currentUser = JSON.parse(localStorage.getItem(currentUserKey));
  let roles = [];
  if (currentUser) {
    currentUser.authorities.forEach(function (role) {
      roles.push(role.authority);
    });
  }
  return roles;
}

function hasAnyRoles(authorities) {
  let roles = getUserRoles();
  if (!roles || roles.length == 0) {
    return false;
  }
  if(authorities.length == 0){
    return true;
  }

  for (let i = 0; i < authorities.length; i++) {
    let valid = roles.indexOf(authorities[i]) > -1;
    if (valid) {
      return true;
    }
  }
  return false;
}

function getCurrentRole() {
  if (hasAnyRoles([Roles.ROLE_ADMIN])) {
    return Roles.ROLE_ADMIN;
  } else if (hasAnyRoles([Roles.ROLE_END_USER])) {
    return Roles.ROLE_END_USER;
  }
}

let Auth = {
  setToken: setToken,
  setCurrentUser: setCurrentUser,
  getCurrentUser: getCurrentUser,
  getAccessToken: getToken,
  isAuthenticated: isAuthenticated,
  hasAnyRoles: hasAnyRoles,
  getCurrentRole: getCurrentRole,
  setUserFirstLoginFalse: function(){
    let user  = JSON.parse(localStorage.getItem(currentUserKey));
    user.firstLogin = false;
    localStorage.setItem(currentUserKey, JSON.stringify(user));
  },

  logout() {
    userInMemory = null
    removeToken();
    removeCurrentUser();
  }
};

export default Auth;

