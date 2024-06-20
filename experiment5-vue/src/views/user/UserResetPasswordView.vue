<template>
  <el-card style="max-width: 480px">
    <template #header>修改密码</template>
    <el-form :model="pwdData" label-width="auto" style="max-width: 600px" :rules="rules">
      <el-form-item label="原密码" prop="oldPwd">
        <el-input v-model="pwdData.oldPwd" />
      </el-form-item>
      <el-form-item label="新密码" prop="newPwd">
        <el-input v-model="pwdData.newPwd" />
      </el-form-item>
      <el-form-item label="再次输入新密码" prop="rePwd">
        <el-input v-model="pwdData.rePwd" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updatePwd">修改</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { userPwdUpdateService } from '@/api/user'
import { ElMessage, ElMessageBox } from 'element-plus'

const pwdData = ref({
  oldPwd: '',
  newPwd: '',
  rePwd: ''
})

const checkRePassword = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== pwdData.value.newPwd) {
    callback(new Error('两次输入的密码不一致'))
  } else {
    callback()
  }
}

const rules = {
  oldPwd: [
    { required: true, message: '请输入原密码', trigger: 'blur' },
    { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' }
  ],
  newPwd: [
    { required: true, message: '请输入新密码', trigger: 'blur' },
    { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' }
  ],
  rePwd: [
    { validator: checkRePassword, trigger: 'blur' }
  ]
}


const updatePwd = async () => {
  ElMessageBox.confirm(
    '你确定要修改密码吗？',
    '温馨提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(async () => {
    let res = await userPwdUpdateService(pwdData.value)
    ElMessage.success(res.data.message ? res.data.message : '修改成功')
  }).catch(() => {
    ElMessage({
      type: 'info',
      message: '用户取消修改'
    })
  })
}
</script>