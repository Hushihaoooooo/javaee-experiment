import request from '@/utils/request'

export const pictureUploadService = (uploadData: any) => {
  return request.post('picture/upload', uploadData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
}

export const pictureGetAllService = () => {
  return request.get('picture/getAll');
}

export const pictureGetListService = (userId:number) => {
  return request.get('picture/getList', {
    params: { userId }
  });
}

