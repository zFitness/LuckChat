<template>
  <div class="profile-container">
    <!-- 资料背景 -->
    <div class="info-bg">
      <div class="info">
        <div class="img">
          <van-image round width="1.5rem" height="1.5rem" :src="userInfo.avatar" />
        </div>
        <div class="head_card_info">
          <div class="name">{{userInfo.nickname}}
            <van-icon class-prefix="my-icon" :name="userInfo.gender=='男'?'nan':'nv'" />
          </div>
        </div>
      </div>
    </div>
    <!-- 菜单 -->
    <div class="menu">
      <van-cell title="修改信息" icon="user-o" size="large" :border="false" />
      <van-cell title="关于作者" icon="info-o" size="large" :border="false" @click="aboutAuthor" />
      <van-divider />
      <van-cell-group :border="false">
        <van-cell title="设置" icon="setting-o" size="large" :border="false" />
        <van-cell title="检查更新" icon="upgrade" size="large" :border="false" />
      </van-cell-group>

    </div>
    <!-- 注销按钮 -->
    <div class="logout-btn">
      <van-button size="large" class="btn" @click="logout">注销</van-button>
    </div>

    <!-- 作者弹出框 -->
    <van-dialog v-model="authorShow" title="关于">
      <div class="dialog-content-wrapper">
        <div>
          开发者：zfitcode
        </div>
        <div>
          邮箱：zfitcode@163.com
        </div>
      </div>
    </van-dialog>
  </div>
</template>


<script>
import store from '@/store'
import { Dialog } from 'vant';
export default {
  name: 'Profile',
  data() {
    return {
      userInfo: store.getters.userInfo,
      authorShow: false
    }
  },
  methods: {
    aboutAuthor() {
      this.authorShow = true
    },
    logout() {
      Dialog.confirm({
        title: '注销',
        message: '这将账号所有记录',
      })
        .then(() => {
          store.dispatch('user/fedLogOut').then(data => {
            location.reload()
          })
        })
        .catch(() => {
        });
    }
  }
}
</script>


<style lang="scss" scoped>
.profile-container {
  .info-bg {
    width: 100%;
    height: 200px;
    background: #52a7fc;
    padding: 0.64rem;
    box-sizing: border-box;
    width: 100%;
    color: #fff;
    display: flex;
    flex-direction: column;
    justify-content: center;
    .info {
      display: flex;
      flex-direction: column;
      align-items: center;
      .name {
        font-size: 20px;
      }
      .num {
        font-size: 12px;
      }
      .img {
        background: #fff;
        border-radius: 50%;
        width: 1.55rem;
        height: 1.55rem;
        display: flex;
        justify-content: center;
        overflow: hidden;
        align-items: center;
        // -webkit-animation: living 3s linear infinite;
        margin-bottom: 16px;
      }
    }
  }

  .menu {
    margin-top: 16px;
  }

  .logout-btn {
    position: fixed;
    width: 100%;
    bottom: 48px;
    padding: 0 0.1rem;
    box-sizing: border-box;
    .btn {
      background: #ff6348;
      color: #fff;
    }
  }

  .dialog-content-wrapper {
    padding: 0.32rem;
    div {
      margin-bottom: 10px;
    }
  }
}
</style>