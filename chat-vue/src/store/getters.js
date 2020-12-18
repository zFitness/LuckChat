const getters = {
  userName: state => state.app.userName,
  userInfo: state => state.user.userInfo,
  loginStatus: state => state.user.loginStatus
}
export default getters
