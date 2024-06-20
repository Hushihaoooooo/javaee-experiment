<template>
  <el-container>
    <el-row>
      <el-col :span="8">
        <!-- 用户信息部分 -->
        <el-card class="user-card">
          <div class="user-info">
            <el-avatar :src="selectedUser.avatar" size="large" />
            <div class="user-details">
              <p>用户名: {{ selectedUser.userName }}</p>
              <p>昵称: {{ selectedUser.nickName }}</p>
              <p>关注人数: {{ selectedUser.followingCount }}</p>
              <p>粉丝人数: {{ selectedUser.followerCount }}</p>
            </div>
            <el-button type="primary" @click="goToUserProfile(selectedUser.id)">进入个人主页</el-button>
          </div>
        </el-card>
      </el-col>
      <el-col :span="16">
        <!-- 图片列表部分 -->
        <el-card class="photo-card">
          <el-row :gutter="20" v-for="photo in paginatedPhotos" :key="photo.id">
            <el-col :span="8">
              <el-image :src="photo.url" class="photo" />
              <div class="photo-info">
                <p>照片名称: {{ photo.name }}</p>
                <p>上传时间: {{ formatDate(photo.uploadTime) }}</p>
                <p>
                  上传人:
                  <el-link @click="selectUser(photo.user)">{{ photo.user.nickName }}</el-link>
                </p>
              </div>
            </el-col>
          </el-row>
          <el-pagination
            background
            layout="prev, pager, next"
            :total="photos.length"
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
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'

// 示例数据
interface User {
  id: string
  userName: string
  nickName: string
  avatar: string
  followingCount: number
  followerCount: number
}

interface Photo {
  id: string
  name: string
  url: string
  uploadTime: string
  user: User
}

const photos = ref<Photo[]>([
  {
    id: '1',
    name: 'Photo 1',
    url: 'https://via.placeholder.com/300',
    uploadTime: '2024-06-20T10:00:00',
    user: {
      id: 'user1',
      userName: 'user1',
      nickName: 'User One',
      avatar: 'https://via.placeholder.com/100',
      followingCount: 100,
      followerCount: 200,
    }
  },
  // 更多照片数据...
])

const selectedUser = ref<User>(photos.value[0].user)
const pageSize = 6
const currentPage = ref(1)

const paginatedPhotos = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  const end = start + pageSize
  return photos.value.slice(start, end)
})

const formatDate = (dateString: string) => {
  const options: Intl.DateTimeFormatOptions = { year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric' }
  return new Date(dateString).toLocaleDateString('zh-CN', options)
}

const handlePageChange = (page: number) => {
  currentPage.value = page
}

const selectUser = (user: User) => {
  selectedUser.value = user
}

const router = useRouter()
const goToUserProfile = (userName: string) => {
  router.push(`/userProfile/${userName}`).catch(err => {
    ElMessage.error('导航到用户主页失败: ' + err.message)
  })
}
</script>

<style scoped>
.user-card {
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

