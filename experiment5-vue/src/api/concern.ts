import request from '@/utils/request'

export const addConcernService = (followingId:number) => {
  return request.get('concern/add', {
    params: {followingId}
  });
}


export const deleteConcernService = (followingId:number) => {
  return request.get('concern/delete', {
    params: {followingId}
  });
}

export const checkConcernService = (followingId:number) => {
  return request.get('concern/check', {
    params: {followingId}
  });
}