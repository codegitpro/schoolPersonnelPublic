<template>
  <div>
    <div class="login_view">
      <el-form :model="loginForm" class="login_form">
        <div class="title_view">学校人员管理系统登录</div>
        <div class="list_item" v-if="loginType == 1">
          <div class="list_label">账号：</div>
          <input
            class="list_inp"
            v-model="loginForm.username"
            placeholder="请输入账号"
          />
        </div>
        <div class="list_item" v-if="loginType == 1">
          <div class="list_label">密码：</div>
          <input
            class="list_inp"
            v-model="loginForm.password"
            type="password"
            placeholder="请输入密码"
            @keydown.enter.native="handleLogin"
          />
        </div>
        <div class="list_type" v-if="userList.length > 1">
          <div class="list_label">用户类型：</div>
          <el-select v-model="loginForm.role" placeholder="请选择用户类型">
            <el-option
              v-for="(item, index) in userList"
              :label="item.roleName"
              :value="item.roleName"
            ></el-option>
          </el-select>
        </div>
        <div class="remember_view" v-if="loginType == 1">
          <el-checkbox
            v-model="rememberPassword"
            label="记住密码"
            size="large"
            :true-label="true"
            :false-label="false"
          />
        </div>
        <div class="btn_view">
          <el-button
            class="login"
            v-if="loginType == 1"
            type="success"
            @click="handleLogin"
            >登录</el-button
          >
          <el-button
            class="register"
            type="primary"
            @click="handleRegister('xueshengxinxi')"
            >注册学生信息</el-button
          >
          <el-button
            class="register"
            type="primary"
            @click="handleRegister('jiaoshixinxi')"
            >注册教师信息</el-button
          >
        </div>
      </el-form>
    </div>
    <Vcode
      :show="isShow"
      @success="success"
      @close="close"
      @fail="fail"
    ></Vcode>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick, onMounted } from 'vue'
const userList = ref([])
const menus = ref([])
const loginForm = ref({
  role: '',
  username: '',
  password: '',
})
const tableName = ref('')
const loginType = ref(1)
//是否记住密码
const rememberPassword = ref(true)
const context = getCurrentInstance()?.appContext.config.globalProperties
//注册
const handleRegister = (tableName) => {
  context?.$router.push(`/${tableName}Register`)
}
const handleLogin = () => {
  if (!loginForm.value.username) {
    context?.$toolUtil.message('请输入用户名', 'error')

    return
  }
  if (!loginForm.value.password) {
    context?.$toolUtil.message('请输入密码', 'error')

    return
  }
  if (userList.value.length > 1) {
    if (!loginForm.value.role) {
      context?.$toolUtil.message('请选择角色', 'error')
      verifySlider.value.reset()
      return
    }
    for (let i = 0; i < menus.value.length; i++) {
      if (menus.value[i].roleName == loginForm.value.role) {
        tableName.value = menus.value[i].tableName
      }
    }
  } else {
    tableName.value = userList.value[0].tableName
    loginForm.value.role = userList.value[0].roleName
  }
  login()
}
const login = () => {
  context
    ?.$http({
      url: `${tableName.value}/login?username=${loginForm.value.username}&password=${loginForm.value.password}`,
      method: 'post',
    })
    .then(
      (res) => {
        //是否保存当前账号密码至缓存
        if (rememberPassword.value) {
          let loginForm1 = JSON.parse(JSON.stringify(loginForm.value))
          delete loginForm1.code
          context?.$toolUtil.storageSet('loginForm', JSON.stringify(loginForm1))
        } else {
          context?.$toolUtil.storageRemove('loginForm')
        }
        context?.$toolUtil.storageSet('Token', res.data.token)
        context?.$toolUtil.storageSet('role', loginForm.value.role)
        context?.$toolUtil.storageSet('sessionTable', tableName.value)
        context?.$toolUtil.storageSet('adminName', loginForm.value.username)
        context?.$router.push('/')
      },
      (err) => {}
    )
}
//获取菜单
const getMenu = () => {
  let params = {
    page: 1,
    limit: 1,
    sort: 'id',
  }

  context
    ?.$http({
      url: 'menu/list',
      method: 'get',
      params: params,
    })
    .then((res) => {
      menus.value = JSON.parse(res.data.data.list[0].menujson)
      for (let i = 0; i < menus.value.length; i++) {
        if (menus.value[i].hasBackLogin == '是') {
          userList.value.push(menus.value[i])
        }
      }
      //获取缓存是否有保存的账号密码
      let form = context?.$toolUtil.storageGet('loginForm')
      if (form) {
      } else {
        loginForm.value.role = userList.value[0].roleName
      }
      context?.$toolUtil.storageSet('menus', JSON.stringify(menus.value))
    })
}
//初始化
const init = () => {
  getMenu()
  //获取缓存是否有保存的账号密码
  let form = context?.$toolUtil.storageGet('loginForm')
  if (form) {
    loginForm.value = JSON.parse(form)
  }
}
onMounted(() => {
  init()
})
</script>

<style lang="scss" scoped>
.login_view {
  background-repeat: no-repeat;
  flex-direction: column;
  background-size: 100% 100%;
  background: url(http://localhost:8080/schoolPersonnel/file/9856e3cfee38498caf8cb14a647f8c29.jpg)
    no-repeat center center / cover;
  display: flex;
  min-height: 100vh;
  justify-content: center;
  align-items: center;
  position: relative;
  background-position: center center;
  // 表单盒子
  .login_form {
    border-radius: 0px;
    padding: 120px 120px 120px 120px;
    margin: 0 auto;
    clip-path: polygon(
      50% 0%,
      80% 10%,
      100% 35%,
      100% 70%,
      80% 90%,
      50% 100%,
      20% 90%,
      0% 70%,
      0% 35%,
      20% 10%
    );
    background: rgba(255, 255, 255, 0.93);
    display: flex;
    width: 35vw;
    min-height: 35vw;
    justify-content: flex-start;
    flex-wrap: wrap;
  }
  .title_view {
    padding: 0px;
    margin: 0 auto 20px;
    color: #333;
    font-weight: 500;
    width: 80%;
    font-size: 18px;
    text-align: center;
  }
  // item盒子
  .list_item {
    border-radius: 30px;
    margin: 0px auto 20px;
    background: #99999930;
    display: flex;
    width: 80%;
    justify-content: flex-start;
    align-items: center;
    // label
    .list_label {
      color: #333;
      background: none;
      width: 110px;
      font-size: 13px;
      line-height: 36px;
      text-align: right;
    }
    // 输入框
    .list_inp {
      border: 0px solid #ddd;
      border-radius: 0px;
      padding: 0 10px;
      color: #333;
      background: none;
      width: 100%;
      line-height: 36px;
      height: 36px;
    }
  }
  .list_type {
    border-radius: 30px;
    margin: 0 auto 20px;
    background: #99999930;
    display: flex;
    width: 80%;
    justify-content: flex-start;
    align-items: center;
    order: 3;
    .list_label {
      color: #333;
      background: none;
      width: 110px;
      font-size: 13px;
      line-height: 36px;
      text-align: right;
    }
    // 下拉框样式
    :deep(.el-select) {
      border: 0px solid #ddd;
      border-radius: 0px;
      padding: 0 20px 0 10px;
      color: #333;
      background: none;
      width: 100%;
      font-size: 14px;
      line-height: 36px;
      box-sizing: border-box;
      height: 36px;
      //去掉默认样式
      .select-trigger {
        height: 100%;
        .el-input {
          height: 100%;
          .el-input__wrapper {
            border: none;
            box-shadow: none;
            background: none;
            border-radius: 0;
            height: 100%;
            padding: 0;
          }
          .is-focus {
            box-shadow: none !important;
          }
        }
      }
    }
  }
  // 记住密码样式
  .remember_view {
    border-radius: 30px;
    margin: 0 auto 20px;
    background: #99999930;
    width: 80%;

    // 未选中样式
    :deep(.el-checkbox) {
      padding: 0 0 0 40px;
      margin: 0;
      display: flex;
      width: 100%;
      align-items: center;
      height: 36px;
      // 复选框
      .el-checkbox__inner {
        border: 1px solid #dcdfe6;
        background: #fff;
      }
      // 提示文字
      .el-checkbox__label {
        color: #999;
      }
    }
    // 选中样式
    :deep(.is-checked) {
      //复选框
      .el-checkbox__inner {
        background-color: #333;
        border-color: #333;
      }
      // 提示文字
      .el-checkbox__label {
        color: #333;
      }
    }
  }
  // 按钮盒子
  .btn_view {
    padding: 0;
    margin: 0 auto 20px;
    display: flex;
    width: 80%;
    justify-content: flex-start;
    align-items: center;
    flex-wrap: wrap;
    order: 5;
    // 登录
    .login {
      border: 1px solid rgb(238, 162, 54);
      cursor: pointer;
      border-radius: 30px;
      padding: 0 24px;
      margin: 0 auto 10px;
      color: #333;
      background: rgba(240, 173, 78, 0.55);
      letter-spacing: 2px;
      width: auto;
      font-size: 15px;
      min-width: 80px;
      height: 40px;
    }
    // 注册
    .register {
      border: 0;
      cursor: pointer;
      border-radius: 0px;
      padding: 0 10px;
      margin: 0 0px 10px 0;
      color: #333;
      background: none;
      width: auto;
      font-size: 13px;
      height: 32px;
    }
  }
}
</style>
