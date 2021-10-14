// 首页
// 获取应用实例
const app = getApp()
Page({
    data:{
        canIUse: wx.canIUse('button.open-type.getUserInfo'),
    },
    onLoad: function (options) {
    },
    bindGetUserInfo: function(res) {
        //1.获取用户信息
        console.log(res)
        //2.登录
        wx.login({
            success: function (res) {
                console.log(res.code)
                //发送请求
                wx.request({
                    url: '127.0.0.1:8600/user/auth', //仅为示例，并非真实的接口地址
                    data: {
                        code: res.code
                    },
                    header: {
                        'content-type': 'application/json' // 默认值
                    },
                    success(res) {
                        console.log("success")
                        console.log(res)
                    },
                    fail(res) {
                        console.log("shibai")
                        console.log(res)
                    }
                })
            }
        })

    },
})
