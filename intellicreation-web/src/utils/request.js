import axios from "axios";
import { Message } from "element-ui";
import { getToken } from "@/utils/auth";

// create an axios instance
const service = axios.create({
  baseURL: "http://localhost:7777", // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 15000, // request timeout
});

// request interceptor
service.interceptors.request.use(
  (config) => {
    // 是否需要设置 token
    const isToken = (config.headers || {}).isToken === false;
    // do something before request is sent
    if (getToken() && !isToken) {
      config.headers.token = getToken();
    }
    // get请求映射params参数
    if (config.method === "get" && config.params) {
      let url = config.url + "?";
      for (const propName of Object.keys(config.params)) {
        const value = config.params[propName];
        var part = encodeURIComponent(propName) + "=";
        if (value !== null && typeof value !== "undefined") {
          if (typeof value === "object") {
            for (const key of Object.keys(value)) {
              if (value[key] !== null && typeof value[key] !== "undefined") {
                const params = propName + "[" + key + "]";
                const subPart = encodeURIComponent(params) + "=";
                url += subPart + encodeURIComponent(value[key]) + "&";
              }
            }
          } else {
            url += part + encodeURIComponent(value) + "&";
          }
        }
      }
      url = url.slice(0, -1);
      config.params = {};
      config.url = url;
    }
    return config;
  },
  (error) => {
    // do something with request error
    console.log(error); // for debug
    return Promise.reject(error);
  }
);

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  (response) => {
    const res = response.data;

    if (res.code !== 200) {
      Message({
        message: res.message,
        type: "error",
        duration: 3 * 1000,
      });

      // 401:未登录;
      // if (res.code === 401) {
      //   MessageBox.confirm(
      //     "你已被登出，可以取消继续留在该页面，或者重新登录",
      //     "确定登出",
      //     {
      //       confirmButtonText: "重新登录",
      //       cancelButtonText: "取消",
      //       type: "warning",
      //     }
      //   ).then(() => {
      //     store.dispatch("FedLogOut").then(() => {
      //       location.reload(); // 为了重新实例化vue-router对象 避免bug
      //     });
      //   });
      // }
      return Promise.reject(new Error(res.msg || "Error"));
    } else {
      return res;
    }
  },
  (error) => {
    console.log("err" + error); // for debug
    Message({
      message: error.message,
      type: "error",
      duration: 3 * 1000,
    });
    return Promise.reject(error);
  }
);

export default service;
