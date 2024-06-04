import request from '@/utils/request'

// 查询作品管理列表
export function listWorks(query) {
  return request({
    url: '/events/works/list',
    method: 'get',
    params: query
  })
}

// 查询作品管理详细
export function getWorks(workId) {
  return request({
    url: '/events/works/' + workId,
    method: 'get'
  })
}

// 新增作品管理
export function addWorks(data) {
  return request({
    url: '/events/works',
    method: 'post',
    data: data
  })
}

// 修改作品管理
export function updateWorks(data) {
  return request({
    url: '/events/works',
    method: 'put',
    data: data
  })
}

// 删除作品管理
export function delWorks(workId) {
  return request({
    url: '/events/works/' + workId,
    method: 'delete'
  })
}
