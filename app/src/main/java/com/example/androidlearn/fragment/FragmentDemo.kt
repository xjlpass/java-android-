package com.example.androidlearn.fragment

import androidx.appcompat.app.AppCompatActivity

/*
FragmentPagerAdapter 和 FragmentStatePagerAdapter 的区别
    对比	             FragmentPagerAdapter	FragmentStatePagerAdapter
    Fragment 是否销毁	        不销毁	                    会销毁
    保存方式	                一直保存在内存	              保存状态后销毁
    内存占用	                    大	                        小
    适合页面数量	                少	                        多
    切换速度	                    快	                        稍慢
    适合场景	                    Tab 页	              新闻流/大量页面
为什么 Fragment 嵌套 Fragment 时常用 FragmentStatePagerAdapter？
    因为嵌套 Fragment 需要由：ChildFragmentManager
    管理，而 FragmentPagerAdapter 会长期缓存 Fragment，
    在复杂嵌套场景下容易导致生命周期和状态恢复异常；FragmentStatePagerAdapter
    会销毁不可见 Fragment，仅保存状态，因此在嵌套 ViewPager 场景中更加稳定、内存占用更小。
Fragment 比 Activity 多哪些
    少了  onRestart()，因为：Fragment 不直接面对系统任务栈
    多了
        方法	        作用
        onAttach()	Fragment 绑定 Activity
        onCreateView()	创建 Fragment View
        onViewCreated()	View 创建完成
        onActivityCreated()	Activity 创建完成（已废弃）
        onDestroyView()	销毁 Fragment View
        onDetach()	Fragment 与 Activity 分离

Fragment 如何通信？
    Fragment 通信常见方式有：
    Fragment 与 Activity 通过接口回调通信
    Activity 通过 FragmentManager 获取 Fragment 并调用方法
    Fragment 与 Fragment 传统上通过 Activity 中转
    现代开发通常使用 SharedViewModel + LiveData 实现 Fragment 间通信，实现解耦和生命周期安全。

getFragmentManager、getSupportFragmentManager 、getChildFragmentManager 之间的区别？
    方法	属于谁	管理谁
        getFragmentManager()	Fragment/Activity	当前层级 Fragment
        getSupportFragmentManager()	AppCompatActivity	AndroidX Fragment
        getChildFragmentManager()	Fragment	Fragment 内部子 Fragment
    getFragmentManager：
        在 Activity 中使用：当前层级activity 的 fragment
        在 Fragment 中使用：父容器 （注意）的 FragmentManager
   getSupportFragmentManager
         解决 getFragmentManager 无法兼容低版本问题
    getChildFragmentManager
         当前 Fragment 内部子（注意） Fragment 的管理器

Fragment 中 add 与 replace 的区别
    add()
        add 不会移除已有 Fragment，而是将新的 Fragment 叠加到容器中，因此通常需要配合 hide/show 使用，否则容易产生 Fragment 重叠问题。
        使用 add 时 Fragment 可以复用，不会频繁重新创建，性能较好。
    replace()
        replace 本质是先 remove 当前 Fragment，再 add 新 Fragment，因此旧 Fragment 会被销毁并重新创建，生命周期会重新执行，但不容易出现 Fragment 重叠问题。
    Fragment 重叠原因
    使用 add 时，如果应用被系统回收重建，FragmentManager 会自动恢复之前的 Fragment，而代码中再次 add 新 Fragment，就会导致多个 Fragment 重叠。
    常见解决方案包括：
        使用 replace
        add 时设置 tag
        使用 hide/show
        在 savedInstanceState == null 时才 add Fragment
启动方式	            方法	                                是否独立运行	是否需要通知	适合场景
启动式 Service	    startService()	                     是	否	短时间后台任务
绑定式 Service	    bindService()	                    否，依赖绑定者	否	Activity 与 Service 通信
前台                Service	startForegroundService()	是	是	音乐、导航、定位等长期任务
启动 service 为什么独立运行 且适合短时间后台任务
        因为普通后台 Service 对系统来说是一个“后台工作”。如果它长时间运行，会带来几个问题：

        第一，系统可能会杀掉它

        Android 需要节省内存和电量。普通 Service 在后台长期运行时，优先级并不高，系统资源紧张时可能直接回收它。

        第二，Android 8.0 以后限制后台 Service

        从 Android 8.0 开始，应用进入后台后，普通后台 Service 不能随便长期运行。否则可能出现：

        IllegalStateException: Not allowed to start service Intent


        也就是说，不是技术上完全不能运行，而是系统规则不鼓励、也不保证它稳定运行。

        第三，用户感知不到，容易耗电

        长期播放音乐、持续定位、导航、录音这类任务，如果没有通知栏提示，用户不知道应用在后台干什么。Android 要求这类长期任务通常使用前台服务，让用户可见。

IntentService
       IntentService 是 Service 的一个子类，用来处理异步、串行、一次性后台任务。
       IntentService 用于按需处理异步请求，但会受到 Android 8.0 以后后台执行限制影响，并建议考虑使用 WorkManager
       定义一个 IntentService：
                   public class MyIntentService extends IntentService {

                public MyIntentService() {
                    super("MyIntentService");
                }

                @Override
                protected void onHandleIntent(Intent intent) {
                    // 这里已经运行在子线程中（注意）
                    String data = intent.getStringExtra("data");

                    // 执行耗时任务
                    uploadLog(data);
                }

                private void uploadLog(String data) {
                    // 网络请求、文件处理、数据库操作等
                }
            }
startService(intent); 的生命周期
    startService()
        ↓
    onCreate()
        ↓
    onStartCommand()
        ↓
    Intent 加入消息队列
        ↓
    HandlerThread 子线程处理
        ↓
    onHandleIntent(intent)
        ↓
    任务全部处理完成
        ↓
    stopSelf()
        ↓
    onDestroy()

IntentService 为什么被废弃？
    主要原因有几个：
    1. 后台限制越来越严格
    Android 8.0 以后，应用在后台时不能随意启动后台 Service。
    2. 只支持串行任务
    3。 不适合长期任务
IntentService 适合短时间、串行、一次性的后台任务

IntentService 为什么会自动停止？
    因为每次任务执行完后，它内部会调用 stopSelf(startId)。当队列中的任务都执行完后，Service 就会销毁。
多次 startService 会怎样？
    onCreate() 只会在 Service 第一次创建时调用一次；
    onStartCommand() 会被调用多次；
    每个 Intent 会排队进入工作线程，依次触发 onHandleIntent()。
IntentService 的 onBind 返回什么？
    null

* */
class FragmentDemo : AppCompatActivity(){
}