<template>
  <div>
    <div class="forget_view">
      <el-form :model="forgetForm" class="forget_box">
        <div class="forget_title">
          学校人员管理系统{{
            pageType == 1 ? '找回密码' : pageType == 2 ? '输入密保' : '重置密码'
          }}
        </div>
        <div class="tab_view">
          <div class="tab" :class="pageType == 1 ? 'tab_active' : ''">
            找回密码
          </div>
          <div
            class="tab"
            :class="pageType == 2 ? 'tab_active tab_active1' : ''"
          >
            输入密保
          </div>
          <div
            class="tab"
            :class="pageType == 3 ? 'tab_active tab_active1 tab_active2' : ''"
          >
            重置密码
          </div>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick } from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties
const pageType = ref(1)
const forgetForm = ref({})
const userForm = ref({})
//返回登录
const close = () => {
  context?.$router.push({
    path: '/login',
  })
}
</script>

<style lang="scss" scoped>
.forget_view {
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
  .forget_box {
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
    // 标题
    .forget_title {
      padding: 0px;
      margin: 20px auto 30px;
      color: #333;
      font-weight: 500;
      width: 80%;
      font-size: 18px;
      text-align: center;
    }
    // tab
    // 盒子
    .tab_view {
      padding: 0;
      margin: 0 auto 20px;
      display: flex;
      width: 80%;
      align-items: center;
      // 默认样式
      .tab {
        padding: 20px 0;
        color: #666;
        width: calc(100% / 3);
        font-size: 14px;
        box-sizing: border-box;
        text-align: center;
      }
      // 选中样式一
      .tab_active {
        clip-path: polygon(
          0% 20%,
          60% 20%,
          60% 0%,
          100% 50%,
          60% 100%,
          60% 80%,
          0% 80%
        );
        color: #fff;
        background: #00000050;
      }
      // 选中样式二
      .tab_active1 {
        color: #fff;
        background: #00000050;
      }
      // 选中样式三
      .tab_active2 {
        color: #fff;
        background: #00000050;
      }
    }
    // item
    .list_item {
      margin: 10px auto;
      background: #00000030;
      display: flex;
      width: 80%;
      justify-content: flex-start;
      align-items: center;
      // label
      .item_label {
        color: #ccc;
        background: none;
        display: block;
        width: 120px;
        font-size: 14px;
        line-height: 36px;
        box-sizing: border-box;
        text-align: right;
        height: 36px;
      }
      // 输入框
      :deep(.list_inp) {
        border: 0px solid #ddd;
        border-radius: 0px;
        padding: 0 10px;
        color: #ccc;
        background: none;
        width: 80%;
        line-height: 36px;
        box-sizing: border-box;
        height: 36px;
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
      //下拉框样式
      :deep(.list_sel) {
        border: 0px solid #ddd;
        border-radius: 0px;
        padding: 0 10px;
        color: #ccc;
        background: none;
        width: 80%;
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
    }
    // 按钮盒子
    .list_btn {
      margin: 20px auto 0;
      display: flex;
      width: 80%;
      justify-content: center;
      align-items: center;
      flex-wrap: wrap;
      // 获取密保
      :deep(.el-button--success) {
        border: 1px solid rgb(238, 162, 54);
        cursor: pointer;
        border-radius: 30px;
        padding: 0 40px;
        margin: 0 10px 0 0;
        color: #333;
        background: rgba(240, 173, 78, 0.55);
        width: auto;
        font-size: 14px;
        height: 40px;
      }
      // 获取密保悬浮
      :deep(.el-button--success:hover) {
      }
      // 确认密保
      :deep(.el-button--primary) {
        border: 1px solid rgb(238, 162, 54);
        cursor: pointer;
        border-radius: 30px;
        padding: 0 40px;
        margin: 0 10px 0 0;
        outline: none;
        color: #333;
        background: rgba(240, 173, 78, 0.55);
        width: auto;
        font-size: 14px;
        height: 40px;
      }
      // 确认密保悬浮
      :deep(.el-button--primary:hover) {
      }
      // 重置密码
      :deep(.el-button--warning) {
        border: 1px solid rgb(238, 162, 54);
        cursor: pointer;
        border-radius: 30px;
        padding: 0 40px;
        margin: 0 10px 0 0;
        outline: none;
        color: #333;
        background: rgba(240, 173, 78, 0.55);
        width: auto;
        font-size: 14px;
        height: 40px;
      }
      // 重置密码悬浮
      :deep(.el-button--warning:hover) {
      }
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
}
</style>
