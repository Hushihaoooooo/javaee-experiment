import request from '@/utils/request'

export const pictureUploadService = (uploadData: any) => {
  return request.post('picture/upload', uploadData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
}