import http from "../utils/http"
export const index=(data)=>{
    return http.get("baidu.com",data)
}
