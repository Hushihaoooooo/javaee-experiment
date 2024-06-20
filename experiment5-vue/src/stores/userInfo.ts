import { defineStore } from 'pinia';
import { ref } from 'vue';

// 定义用户信息的类型
interface UserInfo {
  [key: string]: any;
}

export const useUserInfoStore = defineStore('userInfo', () => {
  // 定义状态相关的内容

  const info = ref<UserInfo>({});

  const setInfo = (newInfo: UserInfo) => {
    info.value = newInfo;
  };

  const removeInfo = () => {
    info.value = {};
  };
  return { info, setInfo, removeInfo };
}, {
  persist: true
});