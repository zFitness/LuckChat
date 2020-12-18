<template>
  <div id="app">
    <router-view />
  </div>
</template>
<script>
import store from '@/store'
import { wsBaseApi } from '@/config'
export default {
  name: 'App',
  methods: {
    localSocket() {
      let that = this;
      if ("WebSocket" in window) {
        console.log("您的浏览器支持 WebSocket!");

        that.ws = new WebSocket(`${wsBaseApi}/chat/${store.getters.userInfo.id}`);
        that.$global.setWs(that.ws);
        that.ws.onopen = function() {
          console.log('websocket连接成功');
        };

        that.ws.onclose = function() {
          // 关闭 websocket
          console.log("连接已关闭...");
          setTimeout(() => {
            that.localSocket();
          }, 2000);
        };
      } else {
        // 浏览器不支持 WebSocket
        console.log("您的浏览器不支持 WebSocket!");
      }
    },
  },
  created() {
    /**
     * 判断用户是否登录
     */
    if (store.getters.userInfo.id != undefined || store.getters.userInfo.id != null) {
      this.localSocket()
    }


  }
}
</script>
<style lang="scss">
#app {
  height: 100%;
}
</style>
