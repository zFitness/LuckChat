<!-- home -->
<template>
  <div class="index-container">
    <div class="index-title">
      <h3>聊天</h3>

      <van-button icon="friends-o" type="info" color="btn" size="small" class="btn" @click="handlePeopleClick">
        {{count}}
      </van-button>
    </div>
    <div class="wrapper">
      <div class="new-bg">
        <img class="img" src="../../assets/img/chat2.png" alt="">
        <p>开始一个新的聊天</p>
      </div>

    </div>
    <div class="start-btn">
      <van-button size="large" class="btn" @click="match">快速匹配</van-button>
    </div>
  </div>
</template>

<script>
import { Toast } from 'vant';

export default {
  data() {
    return {
      count: 0,
      timer: null
    }
  },

  computed: {},

  mounted() {
    this.getOnlineCount()
    this.timer = setInterval(() => {
      this.getOnlineCount()
    }, 10000)
  },
  beforeDestroy() {
    clearInterval(this.timer)
  },
  methods: {
    handlePeopleClick() {
      Toast({
        message: `在线人数：${this.count}`,
        position: 'bottom'
      });
    },
    getOnlineCount() {
      let message = {
        type: 'GETCOUNT',
      }
      //序列化json对象为字符串
      this.handleMsg(JSON.stringify(message));
    },
    match() {
      this.$router.replace('/match')
    },
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
          that.$router.push(`/chat/${msg.content}`)
        }
      };
    }
  }
}
</script>
<style lang="scss" scoped>
.index-container {
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

  .wrapper {
    width: 100%;
    height: calc(100% - 64px);
    .new-bg {
      height: 100%;
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      .img {
        display: block;
        width: 200px;
        height: 200px;
      }
      p {
        font-size: 20px;
      }
    }
  }

  .start-btn {
    position: fixed;
    width: 100%;
    box-sizing: border-box;
    padding: 0 0.1rem;
    bottom: 48px;
    .btn {
      background: $m-primary;
      color: #fff;
      font-size: 17px;
    }
  }
}
</style>
