<template>
  <div>
    <el-upload
      v-model:file-list="fileList"
      :http-request="handleUploadWithAxios"
      list-type="picture-card"
      :on-preview="handlePictureCardPreview"
      :on-remove="handleRemove"
    >
      <el-icon><Plus /></el-icon>
    </el-upload>

    <el-input v-model="customFileName" placeholder="请输入图片名" />

    <el-dialog v-model="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="Preview Image" />
    </el-dialog>

  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { Plus } from '@element-plus/icons-vue'
import { ElInput } from 'element-plus'

import type { UploadProps, UploadUserFile } from 'element-plus'
import { pictureUploadService } from '@/api/picture'
import { useUserInfoStore } from '@/stores/userInfo'

const fileList = ref<UploadUserFile[]>([])

const dialogImageUrl = ref('')
const dialogVisible = ref(false)

const customFileName = ref('')

const userInfoStore = useUserInfoStore()
const userInfo = ref({
  ...userInfoStore.info
})

const handleRemove: UploadProps['onRemove'] = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles)
}

const handlePictureCardPreview: UploadProps['onPreview'] = (uploadFile) => {
  dialogImageUrl.value = uploadFile.url!
  dialogVisible.value = true
}

const handleUploadWithAxios: UploadProps['httpRequest'] = async ({ file }) => {
  const formData = new FormData()
  formData.append('file', file)
  formData.append('uploadedBy', userInfo.value.id) // 上传人
  formData.append('customFileName', customFileName.value) // 自定义文件名

  console.log(customFileName.value)

  try {
    const response = await pictureUploadService(formData)
    if (response.status === 200) {
      // const newFile = { name: file.name, url: response.data.data }
      // fileList.value.push(newFile)
      console.log('File uploaded successfully:', response.data)
    }
  } catch (error) {
    console.error('File upload failed:', error)
  }
}
</script>
