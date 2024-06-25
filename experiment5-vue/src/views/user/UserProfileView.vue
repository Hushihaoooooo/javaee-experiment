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
            <el-button v-if="isCurrentUser" disabled>编辑个人资料</el-button>
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
import { useRoute } from 'vue-router'
import { ElMessage } from 'element-plus'
import { pictureGetListService } from '@/api/picture'
import { useUserInfoStore } from '@/stores/userInfo'
import { userInfoUpdateService } from '@/api/user'

interface Photo {
  [key: string]: any
}


const userInfoStore = useUserInfoStore()

const userInfo = ref({
  ...userInfoStore.info
})

const user = ref(userInfo.value)


// 模拟从服务器获取用户的照片
const userPhotos = ref<Photo[]>([])

console.log('dudu' + user.value.id)
const getPictureList = async () => {
  let res = await pictureGetListService(user.value.id)
  console.log(res.data)
  userPhotos.value = res.data.data
}

getPictureList()


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


// 模拟 API 请求获取用户数据
// 实际上需要用 axios 或者 fetch 发起请求
// 示例数据
const route = useRoute()
console.log(route.params.userName)
user.value.userName = route.params.userName as string

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

const toggleFollow = () => {
  if (user.value.isFollowing) {
    // 调用取消关注逻辑
    // 示例：此处需要调用 API 取消关注
    // 模拟成功
    user.value.isFollowing = false
    user.value.followerCount--
    ElMessage.success('取消关注成功')
  } else {
    // 调用关注逻辑
    // 示例：此处需要调用 API 关注
    // 模拟成功
    user.value.isFollowing = true
    user.value.followerCount++
    ElMessage.success('关注成功')
  }
  userInfoUpdateService(user.value)
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
