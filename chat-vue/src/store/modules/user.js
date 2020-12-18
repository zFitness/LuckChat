import {
  saveToken,
  saveLoginStatus,
  saveUserInfo,
  removeToken,
  removeUserInfo,
  removeLoginStatus,
  loadLoginStatus,
  loadToken,
  loadUserInfo
} from '@/utils/cache'
import { register, login } from '@/api/user'

const state = {
  loginStatus: loadLoginStatus(), // 登录状态
  token: loadToken(), // token
  userInfo: loadUserInfo() // 用户登录信息
}

const mutations = {
  SET_USERINFO: (state, userInfo) => {
    state.userInfo = userInfo
  },
  SET_LOGIN_STATUS: (state, loginStatus) => {
    state.loginStatus = loginStatus
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  }
}

const actions = {
  // 注册用户，保存到本地
  registerUser({ commit, dispatch }, userInfo) {
    return new Promise((resolve, reject) => {
      register(userInfo)
        .then(resp => {
          console.log(resp)
          if (resp.code == 200) {
            // 存用户信息，token
            dispatch('setLoginStatus', 1)
            commit('SET_USERINFO', saveUserInfo(resp.data.userInfo))
            commit('SET_TOKEN', saveToken('xxx'))
            resolve('success')
          } else {
            reject(resp)
          }
        })
        .catch(err => {
          reject(err)
        })
    })
  },
  // 登陆，保存到本地
  login({ commit, dispatch }, params) {
    return new Promise((resolve, reject) => {
      login(params)
        .then(resp => {
          console.log(resp)
          if (resp.code == 200) {
            // 存用户信息，token
            dispatch('setLoginStatus', 1)
            commit('SET_USERINFO', saveUserInfo(resp.data.userInfo))
            commit('SET_TOKEN', saveToken('xxx'))
            resolve('success')
          } else {
            reject(resp)
          }
        })
        .catch(err => {
          reject(err)
        })
    })
  },
  // 设置状态
  setLoginStatus({ commit }, query) {
    if (query === 0 || query === 1) {
      // 上线打开注释，本地调试注释掉，保持信息最新
      removeToken()
      removeUserInfo()
    }
    // 设置不同的登录状态
    commit('SET_LOGIN_STATUS', saveLoginStatus(query))
  },
  // 登出
  fedLogOut() {
    // 删除token，用户信息，登陆状态
    removeToken()
    removeUserInfo()
    removeLoginStatus()
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
