<template>
  <div class="match-container">
    <van-nav-bar @click-left="onClickLeft" :border="false">
      <template #left>
        <van-icon name="cross" size="20" color="#fff" @click="onClickLeft" />
      </template>
      <template #title>
        <div class="title">匹配聊天</div>
      </template>
    </van-nav-bar>

    <div class="content">
      <div class="img">
        <van-image round width="1.5rem" height="1.5rem" :src="userInfo.avatar" />
      </div>

      <div class="time-text">
        <span>{{ this.time }}</span>
      </div>

      <div class="match-text">
        <span>匹配中</span>
      </div>
    </div>
  </div>
</template>

<script>
import store from '@/store'
import { Dialog, Toast } from 'vant';

export default {
  name: 'Match',
  data() {
    return {
      userInfo: store.getters.userInfo,
      timer: null,
      time: '00:00'
    }
  },
  mounted() {
    // 开始计算时间
    let n = 0;
    clearInterval(this.timer);
    this.timer = setInterval(() => {
      if (n > 360) {
        this.hintFailure()
      }
      //2秒后才发送匹配请求
      if (n > 1) {
        // 发送匹配请求
        this.match();
      }
      n++;
      let m = parseInt(n / 60);
      let s = parseInt(n % 60);
      this.time = this.toDub(m) + ":" + this.toDub(s);
    }, 1000);

  },
  beforeDestroy() {
    //清除定时器
    clearInterval(this.timer)
  },
  methods: {
    // 发送匹配请求
    match() {
      let message = {
        type: 'MATCH',
      }
      //序列化json对象为字符串
      this.handleMsg(JSON.stringify(message));
    },
    //发送解除匹配请求
    unMatch() {
      let message = {
        type: 'UN_MATCH',
      }
      //序列化json对象为字符串
      this.handleMsg(JSON.stringify(message));
    },
    //点击退出按钮
    onClickLeft() {
      Dialog.confirm({
        title: '退出',
        message: '是否退出匹配',
      })
        .then(() => {
          this.unMatch()
          this.$router.replace('/')
        })
        .catch(() => {
        });
    },
    //提示匹配失败按钮
    hintFailure() {
      Dialog.alert({
        title: '匹配失败',
        message: '匹配超时，请稍后匹配',
      }).then(() => {
        this.unMatch()
        this.$router.replace('/')
      });
    },
    //给数字填充0
    toDub(n) {
      return n < 10 ? "0" + n : "" + n;
    },
    // 发送接收websocket 消息
    handleMsg(msg) {
      let that = this;
      if (that.$global.ws && that.$global.ws.readyState == 1) {
        console.log("发送信息", msg);
        that.$global.ws.send(msg);
      }
      that.$global.ws.onmessage = function(res) {
        console.log("收到服务器内容", res);
        let msg = JSON.parse(res.data);
        if (msg.type == 'GETCOUNT') {
          that.count = msg.content
        } else if (msg.type == 'MATCH') {
          console.log(msg.content)
          Toast({
            message: `匹配成功`,
            position: 'bottom'
          });
          that.$router.replace(`/chat/${msg.content}`)
        }
      };
    }
  }
}
</script>

<style lang="scss" scoped>
.match-container {
  height: 100%;
  background: #52a7fc;
  .van-nav-bar {
    background: transparent;
    .title {
      color: #fff;
      font-size: 18px;
    }
  }

  .content {
    display: flex;
    align-items: center;
    flex-direction: column;
    .img {
      margin-top: 80px;
      background: #fff;
      border-radius: 50%;
      width: 1.55rem;
      height: 1.55rem;
      display: flex;
      justify-content: center;
      overflow: hidden;
      align-items: center;
      // -webkit-animation: living 3s linear infinite;
    }

    .time-text {
      font-size: 28px;
      color: #fff;
      margin-top: 12px;
    }

    .match-text {
      font-size: 18px;
      color: #fff;
      margin-top: 12px;
    }
  }
}
</style>