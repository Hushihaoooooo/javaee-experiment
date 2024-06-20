import request from '@/utils/request'

interface Data {
  [key: string]: string;
}

export const userRegisterService = (registerData: Data) => {
  return request.post('user/register', registerData)
}

export const userLoginService = (loginData: Data) => {
  return request.post('user/login', loginData)
}

export const userInfoUpdateService = (loginData: Data) => {
  return request.post('user/update', loginData)
}

export const userInfoService = () => {
  return request.get('user/userInfo')
}

export const userPwdUpdateService = (pwdData: Data) => {
  return request.post('user/updatePwd', pwdData);
}