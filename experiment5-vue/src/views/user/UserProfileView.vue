<template>
  <el-container>
    <el-row>
      <el-col :span="8">
        <!-- 用户详细信息部分 -->
        <el-card class="user-profile-card">
          <div class="user-info">
            <el-avatar :src="user.avatar" size="large" />
            <div class="user-details">
              <p>用户名: {{ user.userName }}</p>
              <p>昵称: {{ user.nickName }}</p>
              <p>关注人数: {{ user.followingCount }}</p>
              <p>粉丝人数: {{ user.followerCount }}</p>
              <p>个人简介: {{ user.intro }}</p>
            </div>
            <el-button v-if="!isCurrentUser" type="primary" @click="toggleFollow">
              {{ user.isFollowing ? '取消关注' : '关注' }}
            </el-button>
            <el-button v-if="isCurrentUser" @click="goToUserInfo">编辑个人资料</el-button>
          </div>
        </el-card>
      </el-col>
      <el-col :span="16">
        <!-- 用户照片列表部分 -->
        <el-card class="photo-card">
          <el-row :gutter="20" >
            <el-col :span="8" v-for="photo in paginatedPhotos" :key="photo.id">
              <el-image :src="photo.url" class="photo" />
              <div class="photo-info">
                <p>照片名称: {{ photo.name }}</p>
                <p>上传时间: {{ formatDate(photo.uploadTime) }}</p>
              </div>
            </el-col>
          </el-row>
          <el-pagination
            background
            layout="prev, pager, next"
            :total="userPhotos.length"
            :page-size="pageSize"
            @current-change="handlePageChange"
          />
        </el-card>
      </el-col>
    </el-row>
  </el-container>
</template>

<script lang="ts" setup>
import { ref, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { pictureGetListService } from '@/api/picture'
import { useUserInfoStore } from '@/stores/userInfo'
import { userInfoUpdateService, userSelectService } from '@/api/user'
import { addConcernService, checkConcernService, deleteConcernService } from '@/api/concern'

interface Photo {
  [key: string]: any
}

const userInfoStore = useUserInfoStore()

const userInfo = ref({
  ...userInfoStore.info
})

const user = ref(userInfo.value)
const route = useRoute()

const getUser = async () => {
  let res = await userSelectService(Number(route.params.id))
  console.log("test userProfileView getUser")
  user.value = res.data.data
  console.log(user.value)
  await getPictureList()
}
getUser()

const userPhotos = ref<Photo[]>([])

const getPictureList = async () => {
  let res = await pictureGetListService(user.value.id)
  console.log(user.value.id)
  console.log(res.data)
  userPhotos.value = res.data.data
  await checkFollow()
}




const pageSize = 6
const currentPage = ref(1)

const paginatedPhotos = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  const end = start + pageSize
  return userPhotos.value.slice(start, end)
})

const formatDate = (dateString: string) => {
  const options: Intl.DateTimeFormatOptions = {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    hour: 'numeric',
    minute: 'numeric'
  }
  return new Date(dateString).toLocaleDateString('zh-CN', options)
}

const handlePageChange = (page: number) => {
  currentPage.value = page
}

const checkFollow = async () => {
  let res = await checkConcernService(user.value.id)
  console.log("checkFollow")
  //console.log(res.data)
  user.value.isFollowing = res.data.data
}


const toggleFollow = async () => {
  if (user.value.isFollowing) {
    user.value.isFollowing = false
    user.value.followerCount--
    await deleteConcernService(user.value.id)
    ElMessage.success('取消关注成功')
  } else {
    user.value.isFollowing = true
    user.value.followerCount++
    await addConcernService(user.value.id)
    ElMessage.success('关注成功')
  }
  await userInfoUpdateService(user.value)
}


const router = useRouter()
const goToUserInfo = async () => {
  await router.push('/userInfo')
}

const isCurrentUser = computed(() => {
  return user.value.id === userInfo.value.id // 假设 'currentUserId' 是当前用户的 ID
})

</script>

<style scoped>
.user-profile-card {
  min-height: 600px;
  padding: 20px;
}

.user-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
}

.user-details {
  width: 400px;
  text-align: center;
}

.photo-card {
  min-height: 600px;
  padding: 20px;
}

.photo {
  width: 100%;
  max-width: 360px;
}

.photo-info {
  text-align: center;
  margin-top: 0.5rem;
}

.el-col {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 250px; /* 保持图片列高度一致 */
}

.el-container {
  padding: 20px;
}
</style>
