import request from '@/utils/request'

// 查询账号管理列表
export function listAccount(query) {
  return request({
    url: '/events/account/list',
    method: 'get',
    params: query
  })
}

// 查询账号管理详细
export function getAccount(accountId) {
  return request({
    url: '/events/account/' + accountId,
    method: 'get'
  })
}

// 新增账号管理
export function addAccount(data) {
  return request({
    url: '/events/account',
    method: 'post',
    data: data
  })
}

// 修改账号管理
export function updateAccount(data) {
  return request({
    url: '/events/account',
    method: 'put',
    data: data
  })
}

// 删除账号管理
export function delAccount(accountId) {
  return request({
    url: '/events/account/' + accountId,
    method: 'delete'
  })
}
