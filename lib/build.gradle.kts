plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    // sourceCompatibility 是 Java/Gradle 构建体系中的核心配置项，用于指定编译 Java/Kotlin 源代码时遵循的 Java 版本规范
    sourceCompatibility = JavaVersion.VERSION_11
    // 指定编译生成的字节码（.class 文件）兼容的 Java 运行时版本
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
