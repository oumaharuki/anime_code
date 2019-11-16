const axios = require('axios')

axios.defaults.timeout = 10000 // 超时时间
let VueCookie = require('vue-cookie')
let cookieVue = VueCookie.get('x_auth_token') // 获取cookie
axios.interceptors.request.use(
    config => {
        config.headers['X-Auth-Token'] = cookieVue
        config.headers['Content-Type'] = 'application/json'
        return config
    },
    error => {
        console.log('请求超时，请稍后重试！');
        return Promise.reject(error)
    }
)
// http响应拦截器
axios.interceptors.response.use(res => {
        if (res.data.bodyObj && res.data.bodyObj.code) {
            let code = res.data.bodyObj.code
            // 10101是未登录状态码
            if (code === 10101) { // 如果是未登录直接踢出去
                location.href = '/login.html'
            }
        }
        return res
    },
    error => {
        console.log('请求超时，请稍后重试！');
        return Promise.reject(error)
    }
)

function get (url,data) {
    return axios({
        method:'get',
        url:url+toQueryString(data)
    })
}
function post (url,params) {
    return axios({
        method:'post',
        url:url,
        data:params
    })
}
function toQueryString(obj) {
    return obj
        ? "?" +
        Object.keys(obj)
            .sort()
            .map(key => {
                let val = obj[key];
                if (Array.isArray(val)) {
                    return val
                        .sort()
                        .map(function(val2) {
                            return key + "=" + val2;
                        })
                        .join("&");
                }
                return key + "=" + val;
            })
            .join("&")
        : "";
}
export default {get,post}
