import api from './index'
// axios
import request from '@/utils/request'

// 注册
export function register(data) {
  return request({
    url: '/user/register',
    method: 'post',
    data,
    hideloading: false
  })
}

// 登录
export function login(params) {
  return request({
    url: '/user/login',
    method: 'post',
    params,
    hideloading: false
  })
}

export function getUser(id) {
  return request({
    url: `/user/${id}`,
    method: 'get',
    hideloading: false
  })
}

// 用户信息 post 方法
export function getUserInfo(data) {
  return request({
    url: api.UserInfo,
    method: 'post',
    data,
    hideloading: true
  })
}

// 用户名称 get 方法
export function getUserName(params) {
  return request({
    url: api.UserName,
    method: 'get',
    params,
    hideloading: true
  })
}
