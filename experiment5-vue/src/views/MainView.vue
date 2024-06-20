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
  console.log(res.data.data);
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
          <el-menu-item index="/home">首页</el-menu-item>
          <el-menu-item index="/activity">活动大厅</el-menu-item>
          <el-menu-item index="/exchange">积分兑换</el-menu-item>
          <el-menu-item index="/user">个人信息</el-menu-item>
          <el-menu-item index="/login">退出登录</el-menu-item>
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
</style>