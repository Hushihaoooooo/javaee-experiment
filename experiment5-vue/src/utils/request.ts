import axios from "axios";
import {useTokenStore} from "@/stores/token";

const baseURL = '/api'
const instance = axios.create({baseURL})

instance.interceptors.request.use(
  (config) => {
    const tokenStore = useTokenStore();
    if (tokenStore.token) {
      config.headers.Authorization = tokenStore.token;
    }
    return config;
  },
  (err) => {
    return Promise.reject(err);
  }
)

export default instance;