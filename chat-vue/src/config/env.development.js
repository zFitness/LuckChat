let domain = 'localhost'
// let domain = '192.168.123.13'

// 本地环境配置
module.exports = {
  title: 'chat',
  baseUrl: 'http://localhost:9020', // 项目地址
  baseApi: `http://${domain}:8099`,
  wsBaseApi: `ws://${domain}:8099`, //websocket 地址
  APPID: 'xxx',
  APPSECRET: 'xxx',
  $cdn: 'https://www.sunniejs.cn/static'
}
