import request from '@/utils/request'

// 获取访问次数
export function getComeCounts () {
  return request({
    url: `/`,
    method: 'get'
  })
}