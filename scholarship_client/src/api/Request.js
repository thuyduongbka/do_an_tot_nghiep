import axios from 'axios';
import auth from '@/security/Authentication';
import router from '@/router';
import Pages from '@/router/Pages';
import AlertService from "@/services/AlertService";

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
        if (error && error.response && (error.response.status == 401 || error.response.status == 403)) {
            auth.logout(); // call logout to remove current user & token.
            router.push({path: loginURL});
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

const server =  "http://localhost:8085";
const AdminRequest = createRequest(server, Pages.loginAdmin.path);
const UserRequest = createRequest(server, Pages.loginUser.path);
export {AdminRequest, UserRequest};
