// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    // 引用预定义在版本目录中的「Kotlin JVM 插件」别名，但暂时不将该插件应用到当前模块。
    // alias(...)：解析插件别名
    // Gradle 提供的 alias() 函数，
    // 作用是将版本目录中定义的插件别名（如 libs.plugins.jetbrains.kotlin.jvm）解析为 Gradle 可识别的插件引用（包含插件 ID 和版本）。
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}