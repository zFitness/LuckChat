/**
 * 基础路由
 * @type { *[] }
 */
export const constantRouterMap = [
  {
    path: '/',
    component: () => import('@/views/layouts/index'),
    redirect: '/home',
    meta: {
      title: '首页',
      keepAlive: false
    },
    children: [
      {
        path: '/home',
        name: 'Home',
        component: () => import('@/views/home/index'),
        meta: { title: '首页', keepAlive: false }
      },
      {
        path: '/profile',
        name: 'Profile',
        component: () => import('@/views/profile/index'),
        meta: { title: '关于我', keepAlive: false }
      }
    ]
  },
  {
    path: '/profile',
    component: () => import('@/views/profile/index'),
    meta: { title: '关于我', keepAlive: false }
  },
  {
    path: '/chat/:id',
    component: () => import('@/views/chat/index'),
    meta: { title: '聊天', keepAlive: false }
  },
  {
    path: '/register',
    component: () => import('@/views/register/index'),
    meta: { title: '注册', keepAlive: false }
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    meta: { title: '登录', keepAlive: false }
  },
  {
    path: '/match',
    component: () => import('@/views/matching/index'),
    meta: { title: '匹配', keepAlive: false }
  }
]
