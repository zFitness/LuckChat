<template>
  <van-tabbar fixed route class="tabbar">
    <van-tabbar-item icon="chat-o" to="/home" replace></van-tabbar-item>
    <van-tabbar-item icon="user-o" to="/profile" replace></van-tabbar-item>
  </van-tabbar>
</template>
<script>
export default {
  name: 'TabBar',
  props: {
    data: {
    }
  },
  data() {
    return {
      active: 0
    }
  },
  methods: {
  }
}
</script>

<style scoped>
.tabbar {
  height: 48px;
  background: #ededed;
}
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
