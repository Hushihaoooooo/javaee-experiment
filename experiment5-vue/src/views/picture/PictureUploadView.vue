<template>
  <el-container>
    <el-header>Upload File</el-header>
    <el-main>
      <el-form :model="form" ref="uploadForm" label-width="100px">
        <el-form-item label="File Upload">
          <el-upload
            v-model:file-list="fileList"
            action="/api/upload"
            :auto-upload="false"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            list-type="picture">
            <el-button type="primary">Click to upload</el-button>
            <template #tip>
              <div class="el-upload__tip">jpg/png files less than 500kb</div>
            </template>
          </el-upload>
        </el-form-item>
        <el-form-item label="Uploaded By" required>
          <el-input v-model="form.uploadedBy"></el-input>
        </el-form-item>
        <el-form-item label="Upload Date" required>
          <el-date-picker v-model="form.uploadDate" type="date"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm" :disabled="fileList.length === 0">Submit</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { pictureUploadService } from '@/api/picture'

const fileList = ref([])
const form = ref({
  uploadedBy: '',
  uploadDate: ''
})

const handleRemove = (file:any, fileList:any) => {
  console.log(file, fileList)
}

const handlePreview = (file:any) => {
  console.log(file)
}

const submitForm = () => {
  const formData = new FormData()
  fileList.value.forEach(file => {
    formData.append('file', file.raw)
  })
  formData.append('uploadedBy', form.value.uploadedBy)
  formData.append('uploadDate', form.value.uploadDate)
  console.log(formData)
  ElMessageBox.confirm(
    '你确定要上传吗？',
    '温馨提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(async () => {
    let res = await pictureUploadService(formData)
    ElMessage.success(res.data.message ? res.data.message : '上传成功')
  }).catch(() => {
    ElMessage({
      type: 'info',
      message: '用户取消上传'
    })
  })

  clearForm()
}

const clearForm = () => {
  form.value.uploadedBy = ''
  form.value.uploadDate = ''
}
</script>
