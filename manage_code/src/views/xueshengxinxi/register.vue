<template>
  <div>
    <div class="register_view">
      <el-form :model="registerForm" class="register_form">
        <div class="title_view">{{ projectName }}注册</div>
        <div class="list_item">
          <div class="list_label">学号：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.xuehao"
            placeholder="请输入学号"
            type="text"
          />
        </div>
        <div class="list_item">
          <div class="list_label">密码：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.mima"
            placeholder="请输入密码"
            type="password"
          />
        </div>
        <div class="list_item">
          <div class="list_label">确认密码：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.mima2"
            type="password"
            placeholder="请输入确认密码"
          />
        </div>
        <div class="list_item">
          <div class="list_label">姓名：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.xingming"
            placeholder="请输入姓名"
            type="text"
          />
        </div>
        <div class="list_item">
          <div class="list_label">年龄：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.nianling"
            placeholder="请输入年龄"
            type="text"
          />
        </div>
        <div class="list_item">
          <div class="list_label">性别：</div>
          <el-select
            class="list_sel"
            v-model="registerForm.xingbie"
            placeholder="请选择性别"
          >
            <el-option
              v-for="item in xueshengxinxixingbieLists"
              :label="item"
              :value="item"
            ></el-option>
          </el-select>
        </div>

        <div class="list_item">
          <div class="list_label">专业：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.zhuanye"
            placeholder="请输入专业"
            type="text"
          />
        </div>
        <div class="list_item">
          <div class="list_label">班级：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.banji"
            placeholder="请输入班级"
            type="text"
          />
        </div>
        <div class="list_item">
          <div class="list_label">联系方式：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.lianxifangshi"
            placeholder="请输入联系方式"
            type="text"
          />
        </div>
        <div class="list_btn">
          <el-button class="register" type="success" @click="handleRegister"
            >注册</el-button
          >
          <div class="r-login" @click="close">已有账号，直接登录</div>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick } from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties
const projectName = context?.$project.projectName
//获取注册类型
import { useRoute } from 'vue-router'
const route = useRoute()
const tableName = ref('xueshengxinxi')

//公共方法
const getUUID = () => {
  return new Date().getTime()
}
const registerForm = ref({
  xingbie: '',
})
const xueshengxinxixingbieLists = ref([])
const init = () => {
  xueshengxinxixingbieLists.value = '男,女'.split(',')
}
// 多级联动参数
//注册按钮
const handleRegister = () => {
  let url = tableName.value + '/register'
  if (registerForm.value.mima != registerForm.value.mima2) {
    context?.$toolUtil.message('两次密码输入不一致', 'error')
    return false
  }
  if (
    registerForm.value.lianxifangshi &&
    !context?.$toolUtil.isMobile(registerForm.value.lianxifangshi)
  ) {
    context?.$toolUtil.message(`联系方式应输入手机格式`, 'error')
    return false
  }

  context
    ?.$http({
      url: url,
      method: 'post',
      data: registerForm.value,
    })
    .then((res) => {
      context?.$toolUtil.message('注册成功', 'success', (obj) => {
        context?.$router.push({
          path: '/login',
        })
      })
    })
}
//返回登录
const close = () => {
  context?.$router.push({
    path: '/login',
  })
}
init()
</script>
<style lang="scss" scoped>
.register_view {
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
  .register_form {
    border-radius: 0px;
    padding: 120px 120px 120px 120px;
    margin: 20px auto 40px;
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
  // 标题样式
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
    padding: 5px 30px 5px 0;
    margin: 10px auto;
    background: #99999930;
    display: flex;
    width: 80%;
    justify-content: flex-start;
    align-items: center;
    // label
    .list_label {
      color: #333;
      background: none;
      width: 100px;
      font-size: 14px;
      line-height: 26px;
      box-sizing: border-box;
      text-align: right;
    }
    // 输入框
    :deep(.list_inp) {
      border: 0px solid #ddd;
      border-radius: 0px;
      padding: 0 10px;
      color: #333;
      background: none;
      width: 100%;
      line-height: 26px;
      box-sizing: border-box;
      height: 26px;
      //去掉默认样式
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
  //下拉框样式
  :deep(.list_sel) {
    border: 0px solid #ddd;
    border-radius: 0px;
    padding: 0 10px;
    color: #333;
    background: none;
    width: 100%;
    line-height: 36px;
    box-sizing: border-box;
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
  //按钮盒子
  .list_btn {
    margin: 10px auto;
    display: flex;
    width: 80%;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
    //注册按钮
    .register {
      border: 1px solid rgb(238, 162, 54);
      border-radius: 30px;
      margin: 0 auto;
      color: #333;
      background: rgba(240, 173, 78, 0.55);
      width: auto;
      font-size: 16px;
      min-width: 120px;
      height: 40px;
    }
    //注册按钮悬浮样式
    .register:hover {
      border: 1px solid rgb(238, 162, 54);
      border-radius: 30px;
      color: #333;
      background: rgba(240, 173, 78, 0.55);
      width: auto;
      font-size: 16px;
      min-width: 120px;
      height: 40px;
    }
    //已有账号
    .r-login {
      cursor: pointer;
      padding: 10px 0 0;
      color: #999;
      width: 100%;
      font-size: 14px;
      text-align: right;
    }
  }
}
</style>
