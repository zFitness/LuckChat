<template>
  <div class="tools-wrapper">
    <div class="tools-item" @click="showMusicDialog = !showMusicDialog">
      <div class="tools-item-img">
        <van-icon name="photo-o" size="22px" />
      </div>
      <div>音乐</div>
    </div>
    <!-- 网易云音乐弹出框 -->
    <van-dialog v-model="showMusicDialog" title="发送音乐" :overlay="false" show-cancel-button>
      <van-list finished-text="没有更多了" class="music-box">
        <div v-for="(item,index) in list" :key="index" class="music-item" @click="send(item.musicId)">
          <div>{{item.title}}</div>
          <div>{{item.author}}</div>
        </div>
      </van-list>
    </van-dialog>
  </div>
</template>

<script>
export default {
  name: 'ToolsMusic',
  props: {
    userInfo: {
      type: Object
    }
  },
  data() {
    return {
      //音乐弹框
      showMusicDialog: false,
      list: [
        {
          title: '可不可以',
          author: '张东豪',
          musicId: '553755659',
        },
        {
          title: 'Señorita',
          author: 'Shawn',
          musicId: '1373168742',
        }
      ],
      keywords: ''
    }
  },
  methods: {
    send(id) {
      if (this.musicId == '') {
        return
      }
      let musicUrl = `<iframe frameborder="no" border="0" marginwidth="0" marginheight="0" width=240 height=86 src="//music.163.com/outchain/player?type=2&id=${id}&auto=1&height=66"></iframe>`;
      let content = {
        id: this.userInfo.id,
        avatar: this.userInfo.avatar,
        msgType: 'music',
        content: musicUrl,
        time: new Date()
      }



      this.$emit("send", content)
    },
  }
}
</script>

<style lang="scss" scoped>
.tools-wrapper {
  flex: 0 0 25%;
  margin-bottom: 16px;
}
.tools-item {
  flex-direction: column;
  align-items: center;
  display: flex;
  .tools-item-img {
    padding: 0.32rem;
    box-sizing: border-box;
    border-radius: 16px;
    background: #fff;
    margin-bottom: 8px;
  }
}
.music-box {
  padding: 0.32rem;
  .music-item {
    display: flex;
    justify-content: space-between;
    background: #f2eded;
    align-items: center;
    height: 40px;
    padding: 0.32rem;
    box-sizing: border-box;
    border-radius: 16px;
    margin-bottom: 8px;
  }
}
</style>