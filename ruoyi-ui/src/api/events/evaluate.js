import request from '@/utils/request'

// 查询评价管理列表
export function listEvaluate(query) {
  return request({
    url: '/events/evaluate/list',
    method: 'get',
    params: query
  })
}

// 查询评价管理详细
export function getEvaluate(evaluateId) {
  return request({
    url: '/events/evaluate/' + evaluateId,
    method: 'get'
  })
}

// 新增评价管理
export function addEvaluate(data) {
  return request({
    url: '/events/evaluate',
    method: 'post',
    data: data
  })
}

// 修改评价管理
export function updateEvaluate(data) {
  return request({
    url: '/events/evaluate',
    method: 'put',
    data: data
  })
}

// 删除评价管理
export function delEvaluate(evaluateId) {
  return request({
    url: '/events/evaluate/' + evaluateId,
    method: 'delete'
  })
}
