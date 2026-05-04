package com.example.androidlearn.handlethread

// 注意包名：os
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import java.io.File
import java.io.FileOutputStream

// HandleThread 案例：HandlerThread做本地文件写入
// 1、HandlerThread 继承 Thread，内部自动完成 Looper.prepare() 和 Looper.loop()；
// 2、必须调用 start() 才会开启线程、初始化 Looper；
// 3、文件 IO 耗时操作放在子线程，不阻塞 UI；
// 任务在消息队列串行执行，避免多线程同时读写同一个文件造成错乱；
// 页面销毁调用 quit() 终止消息循环，防止内存泄漏。

/*
* HandlerThread模拟本地文件写入
* 场景:串行写入本地文件，不卡UI 有序写入，防止并发文件发生错误*/
// 私有化构造器：外部不能直接new实例对象
// 目的：1 禁止外部随意创建多个对象 2 全局只有唯一一个实例 3 统一管理文件的写入，线程、handler、IO资源
class FileWriteManager private constructor(){

    // 子线程 + 自带 Looper
    private val fileThread = HandlerThread("file-write-thread")
    // lateinit 是 非空延后初始化关键字，
    // 用于 Android 中无法在类定义时赋值、但保证后续会赋值的变量，
    // 避免把变量变成可空类型，让代码更简洁安全。
    private lateinit var fileHandler: Handler

    // 目标文件
    private val logFile by lazy {
        File("/sdcard/Download/app_log.txt")
    }

    // init 是 Kotlin 类的初始化代码块 **，在类被实例化（new/ 创建对象）时自动执行，执行时机在构造函数之前

    init {
        // 必须先start 然后在绑定子线程
        fileThread.start()
        fileHandler = Handler(fileThread.looper)
    }
    
    // 对外暴漏 追加写入文件
    fun writeLog(content:String){
        // 切到子线程执行IO
        // 把任务丢给指定线程去执行（而不是立刻执行）
        fileHandler.post{
            wirteFile(content)
        }
    }

    // 把一行字符串 content 追加写入到 logFile 文件中
    private fun FileWriteManager.wirteFile(content: String) {
       // runCatching 是 Kotlin 提供的异常捕获工具函数，作用可以概括成一句话：
        // 执行一段代码，并把成功或失败都包装成 Result 对象返回
        // 它让你不用到处写 try-catch，代码更链式、更简洁。
        runCatching {
            // 输入流和输出流
                // 文件 → 程序   = 输入流
                // 程序 → 文件   = 输出流
            // 字节流和字符流的区别
                // 对比点	字节流（Byte Stream）	字符流（Character Stream）
                // 数据单位	1 字节（8 bit）	1 字符（char）
                // 是否涉及编码	❌ 不关心编码	✅ 自动处理编码
                // 处理内容	图片 / 视频 / 文件 / 任意数据	文本（字符串、日志）
                // 典型类	FileOutputStream / FileInputStream	FileWriter / FileReader
            // 是否有缓冲
                // 字节流（无缓冲）：
                // FileOutputStream
                // 👉 每次 write 都可能触发 IO（慢）
                // 缓冲流：
                // BufferedOutputStream
                // BufferedWriter
                // 👉 优点：
                // 减少磁盘 IO
                // 性能提升明显
            // 用户态和内核态的区别
                // 用户态：普通程序运行的环境（权限低）
                // 内核态：操作系统核心运行的环境（权限高）
            // FileOutputStream.write() 做了什么
                // 你的代码（用户态）
                //    ↓
                // 调用 write()
                //    ↓
                // 触发系统调用
                //    ↓
                // 进入内核态
                //    ↓
                // 操作磁盘
                //    ↓
                // 返回用户态
            // 解释 IO 为什么慢
                //  具体有三层成本：
                // 1️⃣ 系统调用（用户态 ↔ 内核态切换）
                // 从 用户态 切到 内核态
                // 再切回来
                // 👉 这一步很贵（上下文切换）。
                // 2️⃣ 磁盘/存储访问（最慢的一层）
                // 内存：纳秒级
                // 磁盘：毫秒级（差几千倍）
                // 👉 如果你频繁小块写入，就等于不停打断磁盘工作。
                // 3️⃣ 数据拷贝
                // 典型路径：
                // 磁盘 → 内核缓冲区 → 用户缓冲区
                // 👉 多次拷贝也有成本
            //  Buffer为什么这么快
                    // 减少系统调用次数
                        // 原来：写 1000 次 → 1000 次 IO
                        // 现在：写 1000 次 → 可能只 1~10 次 IO
                    // 2️⃣ 利用内存速度快
                        // 先写内存（快）
                        // 再批量写磁盘（慢但次数少）
                    // 3️⃣ 顺序写优化磁盘
                        // 连续写比随机写快很多
                    //    缓冲代码
                    //BufferedOutputStream bos =
                    //     new BufferedOutputStream(new FileOutputStream(file));
                    //
                    // for (...) {
                    //     bos.write(1 byte);
                    // }
                    // bos.flush();
                    // 实际发生 先陷入内存缓冲区 然后一次性写入 IO次数减少

            val fos = FileOutputStream(logFile,true)
            // use = 自动关闭资源（类似 Java 的 try-with-resources）
            fos.use{
                it.write("$content\n".toByteArray())
            }
            Log.d("FileLog", "写入成功：$content，线程：${Thread.currentThread().name}")
        }.onFailure {
            it.printStackTrace()
        }
    }

    // 页面销毁释放
    fun release(){
        fileThread.quit()
    }

    companion object {
        val instance by lazy { FileWriteManager() }
    }
}


