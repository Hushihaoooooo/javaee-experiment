<template>
  <el-container>
    <el-row>
      <el-col :span="6">
        <!-- 用户详细信息部分 -->
        <el-card class="user-profile-card">
          <div class="user-info">
            <el-avatar :src="user.avatar" size="large" />
            <div class="user-details">
              <p>用户名: {{ user.userName }}</p>
              <p>昵称: {{ user.nickName }}</p>
              <p>关注人数: {{ user.followingCount }}</p>
              <p>粉丝人数: {{ user.followerCount }}</p>
              <p>个人简介: {{ user.bio }}</p>
            </div>
            <el-button v-if="!isCurrentUser" type="primary" @click="toggleFollow">
              {{ user.isFollowing ? '取消关注' : '关注' }}
            </el-button>
            <el-button v-if="isCurrentUser" disabled>编辑个人资料</el-button>
          </div>
        </el-card>
      </el-col>
      <el-col :span="18">
        <!-- 用户照片列表部分 -->
        <el-card class="photo-card">
          <el-row :gutter="20" v-for="photo in paginatedPhotos" :key="photo.id">
            <el-col :span="8">
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
import { ref, computed, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { ElMessage } from 'element-plus'

// 示例数据
interface User {
  id: string
  userName: string
  nickName: string
  avatar: string
  followingCount: number
  followerCount: number
  bio: string
  isFollowing: boolean
}

interface Photo {
  id: string
  name: string
  url: string
  uploadTime: string
  userId: string
}

// 模拟从服务器获取用户信息
const user = ref<User>({
  id: '',
  userName: '',
  nickName: '',
  avatar: '',
  followingCount: 0,
  followerCount: 0,
  bio: '',
  isFollowing: false // 标记当前用户是否已关注该用户
})

// 模拟从服务器获取用户的照片
const userPhotos = ref<Photo[]>([])

const pageSize = 6
const currentPage = ref(1)

const paginatedPhotos = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  const end = start + pageSize
  return userPhotos.value.slice(start, end)
})

const formatDate = (dateString: string) => {
  const options: Intl.DateTimeFormatOptions = { year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric' }
  return new Date(dateString).toLocaleDateString('zh-CN', options)
}

const handlePageChange = (page: number) => {
  currentPage.value = page
}

const route = useRoute()
const userName = route.params.userName

onMounted(() => {
  // 模拟 API 请求获取用户数据
  // 实际上需要用 axios 或者 fetch 发起请求
  // 示例数据
  // user.value = {
  //   id: userId,
  //   userName: `user${userId}`,
  //   nickName: `User ${userId}`,
  //   avatar: 'https://via.placeholder.com/100',
  //   followingCount: 100,
  //   followerCount: 200,
  //   bio: '这是用户的个人简介',
  //   isFollowing: false // 假设初始情况下未关注
  // }
  // userPhotos.value = [
  //   {
  //     id: '1',
  //     name: 'Photo 1',
  //     url: 'https://via.placeholder.com/300',
  //     uploadTime: '2024-06-20T10:00:00',
  //     userId: userId
  //   },
  //   // 更多照片数据...
  // ]
})

const toggleFollow = () => {
  if (user.value.isFollowing) {
    // 调用取消关注逻辑
    // 示例：此处需要调用 API 取消关注
    // 模拟成功
    user.value.isFollowing = false
    ElMessage.success('取消关注成功')
  } else {
    // 调用关注逻辑
    // 示例：此处需要调用 API 关注
    // 模拟成功
    user.value.isFollowing = true
    ElMessage.success('关注成功')
  }
}

// const isCurrentUser = computed(() => {
//   // 根据实际情况判断当前用户是否为该用户，以确定是否显示编辑个人资料按钮
//   return userId === 'currentUserId' // 假设 'currentUserId' 是当前用户的 ID
// })

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
