// 按需全局引入 vant组件
import Vue from 'vue'
import {
  Button,
  RadioGroup,
  Radio,
  Field,
  List,
  Icon,
  Cell,
  Image as VanImage,
  Tabbar,
  TabbarItem,
  NavBar,
  CellGroup,
  Dialog,
  Divider
} from 'vant'
Vue.use(Button)
Vue.use(Cell)
Vue.use(List)
Vue.use(Icon)
Vue.use(NavBar)
Vue.use(Field)
Vue.use(VanImage)
Vue.use(Tabbar).use(TabbarItem)
Vue.use(Cell).use(CellGroup)
Vue.use(Radio)
Vue.use(RadioGroup)
Vue.use(Dialog)
Vue.use(Divider)
