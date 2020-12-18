<template>
  <div class="register-container">
    <div class="index-title">
      <h3>登陆</h3>
    </div>
    <div class="title-img">
      <img src="../../assets/img/title.png" width="250px" alt="">
    </div>
    <div class="form">
      <div class="form-name">
        <p>昵称</p>
        <van-field class="input" v-model="nickname" left-icon="user-o" placeholder="请输入用户名" />
      </div>
      <div class="form-name">
        <p>密码</p>
        <van-field class="input" type="password" v-model="password" left-icon="user-o" placeholder="请输入密码" />
      </div>
    </div>

    <div class="register-btn">
      <van-button size="large" class="btn" @click="createUser">登录</van-button>
      <div class="hint-text" @click="$router.push('/register')">没有账号，注册?</div>
    </div>
  </div>
</template>


<script>
import store from '@/store'
import { Toast } from 'vant';


export default {
  name: 'Login',
  data() {
    return {
      maleAvatar: 'https://img.zmblog.wang/blog/20201214/urB3BW6822nv.png?imageslim',
      femaleAvatar: 'https://img.zmblog.wang/blog/20201214/8evAxRKe1K6M.png?imageslim',
      nickname: '',
      gender: '男',
      password: '',
    }
  },
  computed: {
    avatar: function() {
      return this.gender == '男' ? this.maleAvatar : this.femaleAvatar;
    }
  },
  beforeRouteEnter(to, from, next) {
    const { loginStatus } = store.getters
    if (loginStatus == 1) {
      next('/')
    } else {
      next()
    }
  },
  methods: {
    createUser() {
      if (this.nickname != '' && this.password != '') {
        let userInfo = {
          'nickname': this.nickname,
          'password': this.password
        }
        store.dispatch('user/login', userInfo).then(data => {
          location.reload()
        }).catch(error => {
          Toast.fail(error.message);
        })
      } else {
        Toast.fail('请填写完整');
      }

    }
  }
}
</script>


<style lang="scss" scoped>
.register-container {
  height: 100%;
  .index-title {
    height: 64px;
    padding: 0 0.64rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
    .btn {
      font-size: 14px;
    }
    h3 {
      font-size: 20px;
    }
  }
  .title-img {
    margin-top: 24px;
    text-align: center;
  }

  .form {
    padding: 0.64rem;
    .form-name {
      p {
        font-size: 16px;
      }
      .input {
        background: #ededed;
        border-radius: 24px;
      }
    }
  }

  .register-btn {
    text-align: center;
    margin-top: 30px;
    .btn {
      background: $m-primary;
      color: #fff;
      font-size: 18px;
      width: 85%;
      border-radius: 40px;
    }
    .hint-text {
      font-size: 15px;
      margin-top: 15px;
    }
  }
}
</style>