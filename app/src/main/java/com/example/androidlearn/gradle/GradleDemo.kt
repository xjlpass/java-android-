package com.example.androidlearn.gradle

/*
* android的构建流程
*    Android 的构建流程，本质上就是把我们写的代码、资源文件和配置文件，
*    经过编译、转换、打包和签名，最终生成一个可以安装运行的 APK 或 AAB。
*
*    首先，Gradle 会读取 build.gradle，确定编译版本、依赖和构建类型，并生成对应的任务流程。
*    接着进入编译阶段，Java 或 Kotlin 代码会被编译成 .class 文件，资源文件会通过 AAPT2 编译，同时生成 R 文件。
*    然后再把 .class 转换成 Android 运行时能识别的 .dex 文件。对于 release 包，一般还会经过 R8 或 ProGuard 做代码压缩、优化和混淆。
*    之后系统会把 .dex、资源、Manifest 以及 so 库一起打包成 APK 或 AAB，再进行签名和 zipalign 对齐优化。
*    最后安装到设备上，由 ART 加载运行。所以整个流程可以概括为：Gradle 配置、代码和资源编译、dex 转换、混淆优化、打包签名、安装运行。
*
* */
class GradleDemo {
}