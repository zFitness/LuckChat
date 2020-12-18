import qs from 'qs'
import router from '@/router'
import store from '@/store'

const whiteList = ['/404', '/register', '/login']
router.beforeEach(async (to, from, next) => {
  // 在白名单，直接进入
  if (whiteList.indexOf(to.path) !== -1) {
    return next()
  }
  const { loginStatus } = store.getters

  console.log(loginStatus)
  switch (Number(loginStatus)) {
    case 0:
      // 没有登录，跳转到登陆页面
      next('/login')
      break
    case 1:
      next()
      break
    default:
      next()
      break
  }
})
