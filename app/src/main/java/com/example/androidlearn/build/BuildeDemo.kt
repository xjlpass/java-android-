package com.example.androidlearn.build

// 介绍app打包流程
// 什么是aab
    // AAB 是给商店的原料包，商店根据用户手机配置，实时裁剪、拼装出适配的 APK 给用户下载
// AGP 是什么
    /*
            AGP = Android Gradle Plugin，就是 Google 官方给 Gradle 用的安卓构建插件，专门用来把安卓项目编译、打包成 APK / AAB。
            在项目根目录 build.gradle
            dependencies {
            classpath 'com.android.tools.build:gradle:8.5.2' // 这行就是 AGP
        }
    * */
/*  AAPT是什么
    AAPT2 = Android Asset Packaging Tool 2安卓资源打包工具第二代，是 AGP 内置的核心工具
    核心作用
    编译 xml 布局、字符串、图片、颜色、尺寸 等安卓资源
    生成 R.java 资源索引类
    把零散资源编译、压缩、打包进 APK/AAB
    支持增量编译，比旧版 AAPT 更快、更适合 Gradle 流水线
    工作流程
    资源文件 → AAPT2 编译 → 生成二进制资源 + R.java → 参与打包 APK/AAB
* */
/*assets目录是什么
        * assets 是 Android 项目里专门放原生静态资源文件的目录，和 res 目录功能互补，但用法和限制完全不同
        * 对比项
        *       assets 目录	        res 目录
        编译处理	不编译，原样打包	编译优化（图片压缩、字符串索引）
        资源 ID	不生成 R 类 ID	自动生成 R.xxx.xxx 资源 ID
        目录结构	支持任意子文件夹	只能按系统约定的目录结构存放
        访问方式	AssetManager.open("xxx.txt") 文件流	R.drawable.icon / getResources()
        适用场景	大型配置文件、HTML/JS、自定义字体、数据库	图片、字符串、颜色、布局等标准资源
* */
/*
* apk资源包含:
    工程中res目录下的所有文件
    assets目录下的文件
    AndroidManifest.xml*/


class BuildeDemo {
}