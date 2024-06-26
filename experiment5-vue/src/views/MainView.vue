<script setup lang="ts">
import {useRouter} from 'vue-router';
import {ElMessageBox} from 'element-plus';
import {useTokenStore} from "@/stores/token.ts";
import {useUserInfoStore} from "@/stores/userInfo.ts";
import {userInfoService} from "@/api/user.ts";

const router = useRouter();
const tokenStore = useTokenStore();
const userInfoStore = useUserInfoStore();

const getUserInfo = async () => {
  let res = await  userInfoService();
  console.log("getUserInfo successful");
  userInfoStore.setInfo(res.data.data)
}

getUserInfo()


const handleSelect = (index : string) => {
  if (index === '/login') {
    ElMessageBox.confirm(
      '确定要退出登录吗？',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
    ).then(async () => {
      // 用户点击了确定按钮，执行退出登录逻辑
      tokenStore.removeToken();
      userInfoStore.removeInfo();
      await router.push('/login');
    }).catch(() => {
      // 用户点击了取消按钮，不做任何操作
    });
  } else {
    router.push(index);
  }
};
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <el-menu
          class="el-menu-demo"
          mode="horizontal"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          @select="handleSelect"
        >
          <el-menu-item index="/home">主页</el-menu-item>
          <el-menu-item index="/userInfo">个人信息</el-menu-item>
          <el-menu-item index="/resetPassword">修改密码</el-menu-item>
          <el-menu-item index="/upload">上传照片</el-menu-item>
          <el-menu-item index="/login">退出登录</el-menu-item>
          <!-- 添加头像并居中 -->
          <div class="avatar-container">
            <el-avatar :src="userInfoStore.info.avatar"  class="avatar"></el-avatar>
          </div>
        </el-menu>
      </el-header>
      <el-main style="height: 750px;">
        <router-view/>
      </el-main>
      <el-footer>experiment5 ©2024 Created by ShiHaoHu</el-footer>
    </el-container>
  </div>
</template>

<style>
.el-footer {
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 14px;
  color: #666;
}
.el-menu-demo .el-menu-item {
  width: 150px; /* 设置菜单项的统一宽度 */
  text-align: center; /* 居中文本 */
}
.avatar-container {
  display: flex;
  align-items: center;
  padding: 0 15px;
}

</style>