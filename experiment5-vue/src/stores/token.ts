import { defineStore } from 'pinia';

// 定义 TokenStore 的状态类型
interface TokenStoreState {
  token: string;
}

// 定义 TokenStore 的 actions 类型
interface TokenStoreActions {
  setToken(token: string): void;
  removeToken(): void;
}

// 创建 TokenStore
export const useTokenStore = defineStore<string, TokenStoreState, {}, TokenStoreActions>({
  id: 'token',
  state: () => ({
    token: '',
  }),
  actions: {
    setToken(token: string) {
      this.token = token;
    },
    removeToken() {
      this.token = '';
    },
  },
  persist: true,
});
