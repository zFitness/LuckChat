<template>
  <div class="register-container">
    <div class="index-title">
      <h3>注册</h3>
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
      <div class="form-sex">
        <p>性别</p>
        <van-radio-group v-model="gender" direction="horizontal" class="sex-item-wrapper">
          <div class="sex-item">
            <van-radio name="男">男</van-radio>
            <img src="../../assets/img/nan.png" width="67" height="67" alt="">
          </div>
          <div class="sex-item">
            <van-radio name="女">女</van-radio>
            <img src="../../assets/img/nv.png" width="67" height="67" alt="">
          </div>
        </van-radio-group>
      </div>
    </div>

    <div class="register-btn">
      <van-button size="large" class="btn" @click="createUser">创建</van-button>
      <div class="hint-text" @click="$router.push('/login')">已经有账号，登陆?</div>
    </div>
  </div>
</template>


<script>
import store from '@/store'
import { Toast } from 'vant';


export default {
  name: 'Register',
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
          'password': this.password,
          'avatar': this.avatar,
          'gender': this.gender,
          'email': ''
        }
        store.dispatch('user/registerUser', userInfo).then(data => {
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
    .sex-item-wrapper {
      display: flex;
      justify-content: space-around;
    }
    .form-sex {
      p {
        font-size: 16px;
      }
      .sex-item {
        display: flex;
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
      margin-top: 16px;
    }
  }
}
</style>