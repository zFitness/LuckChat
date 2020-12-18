<template>
  <div class="chat-container">
    <!-- 导航栏 -->
    <van-nav-bar class="nav-bar" @click-left="onClickLeft" left-text="返回" left-arrow>
      <template #title>
        <div class="title">{{matchUser.nickname}}</div>
      </template>
      <template #left @click="onClickLeft">
        <van-icon name="arrow-left" size="18" />
      </template>
      <template #right>
        <van-icon name="user-o" size="18" @click="showMatchUser" />
      </template>
    </van-nav-bar>

    <!-- 消息列表 -->
    <div class="message-box">
      <van-list v-model="loading" :finished="finished">
        <div class="message-item" v-for="(item,index) in list" :key="index">
          <div class="message-item-content" v-if="item.id != myId">
            <van-image class="avatar" round width="1rem" height="1rem" :src="item.avatar" @click="showMatchUserInfo = true" />
            <div class="message-content" v-if="item.msgType == 'text'">
              {{item.content}}
            </div>
            <div class="message-music-content" v-if="item.msgType == 'music'" v-html="item.content" />
          </div>
          <div class="message-item-content-me" v-else>
            <van-image class="avatar" round width="1rem" height="1rem" :src="item.avatar" />
            <div class="message-content" v-if="item.msgType == 'text'">
              {{item.content}}
            </div>
            <div class="message-music-content" v-if="item.msgType == 'music'" v-html="item.content" />
          </div>
        </div>
      </van-list>
    </div>

    <!-- 输入框 -->
    <div class="control-bottom">
      <div class="control">
        <van-icon class="emoji-btn" name="add-o" size="28" @click="openTools" />
        <van-field class="input" v-model="msg" placeholder="输入消息" @keyup.enter="send" :disabled="disabled" @click="closeTools" />
        <van-icon class="emoji-btn" name="smile-o" size="28" @click="openEmoji" style="margin-right:8px" />
        <van-button class="send-btn" size="small" type="primary" @click="send" :color="disabled?'#D9DDDD':''">发送</van-button>
      </div>
      <!-- 表情框 -->
      <div class="control-emoji" v-if="emojiShow">
        <div v-for="(item,index) in emoji" :key="index" @click="addEmoji(item)">{{ item }}</div>
      </div>

      <!-- 工具框 -->
      <div class="control-tools" v-if="emojiTools">
        <div class="tools-item">
          <div class="tools-item-img">
            <van-icon name="photo-o" size="22px" />
          </div>
          <div>图片</div>
        </div>
        <div class="tools-item">
          <div class="tools-item-img">
            <van-icon name="photo-o" size="22px" />
          </div>
          <div>视频</div>
        </div>
        <tools-music v-if="userInfo != null" :userInfo="userInfo" @send="handleMusicSend" />
        <div class="tools-item">
          <div class="tools-item-img">
            <van-icon name="photo-o" size="22px" />
          </div>
          <div>代码</div>
        </div>
        <div class="tools-item">
          <div class="tools-item-img">
            <van-icon name="photo-o" size="22px" />
          </div>
          <div>游戏</div>
        </div>
      </div>
    </div>

    <!-- 用户信息弹出框 -->
    <van-dialog v-model="showMatchUserInfo" title="对方信息" :overlay="false">
      <div class="match-user-info">
        <div class="img">
          <van-image round width="1.5rem" height="1.5rem" :src="matchUser.avatar" />
        </div>
        <div>昵称：{{ matchUser.nickname}}</div>
        <div>性别：
          <van-icon class-prefix="my-icon" :name="matchUser.gender=='男'?'nan':'nv'" />
        </div>
      </div>
    </van-dialog>

  </div>
</template>

<script src="./chat.js"></script>

<style lang="scss" scoped src="./chat.scss"></style>