import {createRouter, createWebHistory} from 'vue-router'
// import Main from '@/views/Main.vue'
// import Home from '@/views/Home.vue'
// import User from '@/views/UserView.vue'
import Login from '@/views/LoginView.vue'

const routes = [{
  path: '/login', component: Login
 }, //{
//   path: '/', component: Main, children: [
//     {path: '/home', component: Home},
//     {path: '/user', component: User},
//     {path: '/activity', component: Activity},
//     {path: '/exchange', component: Exchange},
//   ]
// }

]

const router = createRouter({
  history: createWebHistory(),
  routes
})
export default router