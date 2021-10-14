
// 获取应用实例
const app = getApp()
Page({
    onLoad() {
        if (wx.getUserProfile) {
            console.log(wx.getUserProfile)
            this.setData({
                canIUseGetUserProfile: true
            })
        }
    }
})
