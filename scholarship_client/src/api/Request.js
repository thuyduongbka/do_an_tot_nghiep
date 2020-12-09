import axios from 'axios';
import auth from '@/security/Authentication';
import router from '@/router';
import Pages from '@/router/Pages';
import AlertService from "@/services/AlertService";
import ro from "element-ui/src/locale/lang/ro";

let createRequest = (baseURL, loginURL) => {

    let timeout = 1000*60*5;
    const request = axios.create({
        baseURL: baseURL,
        timeout: timeout,

    });

    request.interceptors.response.use(response => {
        return response.data;
    }, error => {

      /**
       * perform redirect to login page when server response with status 401 ( un authorization )
       *
       */
      // if (error && error.response && (error.response.status == 500)) {
      //   router.push({path: "/forbidden"})
      // }
      if (error && error.response && (error.response.status == 401 || error.response.status == 403)) {
        auth.logout(); // call logout to remove current user & token
        if (router.currentRoute.fullPath != "/" + loginURL) {
          router.push({path: loginURL})
        } else {
          throw new Error("Error")
        }
        AlertService.setDisabled(true);
      }
      return Promise.reject(error);
    });

    request.interceptors.request.use(function (config) {

        /**
         * add Authorization header to request if user authenticated, run before sent request
         */
        if (auth.isAuthenticated()) {
            const accessToken = auth.getAccessToken();
            config.headers = {
                'Authorization': `Bearer ${accessToken}`,
            }
        }
        return config;

    }, function (error) {
        return Promise.reject(error);
    });

    return request;
};

const server =  "http://192.168.0.120:8085";
const AdminRequest = createRequest(server, Pages.loginAdmin.path);
const UserRequest = createRequest(server, Pages.loginUser.path);
export {AdminRequest, UserRequest};
