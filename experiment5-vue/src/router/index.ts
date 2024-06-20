import {createRouter, createWebHistory} from 'vue-router'
import LoginVue from '@/views/LoginView.vue'
import MainVue from '@/views/MainView.vue'
import HomeVue from '@/views/user/HomeView.vue'
import UserInfoVue from '@/views/user/UserInfoView.vue'
import UserResetPasswordVue from '@/views/user/UserResetPasswordView.vue'
import UserProfileVue from '@/views/user/UserProfileView.vue'

const routes = [{
  path: '/login', component: LoginVue
 }, {
  path: '/', component: MainVue, children: [
    {path: '/home', component: HomeVue},
    {path: '/userInfo', component: UserInfoVue},
    {path: '/resetPassword', component: UserResetPasswordVue},
    {path: '/userProfile/:userName', component: UserProfileVue, props: true},
  ]
}

]

const router = createRouter({
  history: createWebHistory(),
  routes
})
export default router