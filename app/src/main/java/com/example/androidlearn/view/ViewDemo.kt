package com.example.androidlearn.view

/*
* view的绘制流程
*       View根节点（大多数DecorView）-》measure-》layout-》draw 递归绘制
*       View 树绘制流程就是：从根 View 开始，先 measure 测量，再 layout 布局，最后 draw 绘制，并且这个过程会递归传递到整个 View 树
*
*
* Activity 生命周期和 View 流程对应关系
        * onCreate()
            ↓
        setContentView()

        onStart()
            ↓

        onResume()
            ↓
        --------------------------------
        ViewRootImpl.performTraversals()
            ↓
        measure()
            ↓
        layout()
            ↓
        draw()
        --------------------------------
        用户看到界面

* 为什么在 onCreate()/onResume() 中获取 View 宽高可能为 0？
    * 因为 Activity 生命周期与 View 的 measure/layout 流程是独立的，
    * onResume() 执行时 View 可能还没有经过 measure → layout → draw，
    * 宽高尚未确定，所以得到的可能是 0。真正完成测量和布局是在 ViewRootImpl.performTraversals() 中进行的
*
*
*
* post() 方法
*       在 onCreate()、onStart() 这些生命周期里直接调用 getWidth() 是拿不到真实宽度的，因为 View 还没测量、没绘制完。
        而 post() 能保证：
        等 View 测量、布局、渲染都完成了，再执行里面的代码。
        所以你才能拿到真正的宽度。
*
*
*
* 为什么会掉帧？
    本质都和 View 树绘制有关
    * 一帧时间：16.67ms 系统需要在这一帧完成：Measure Layout Draw GPU渲染
    *
 为什么 ConstraintLayout 比 RelativeLayout 快？
 * 减少 Measure/Layout 次数

*
*
* MeasureSpec 是什么？
*       MeasureSpec 是父 View 在测量阶段传递给子 View 的尺寸约束，
*       由 Mode（测量模式）和 Size（可用大小）组成。Mode 分为 EXACTLY、AT_MOST、UNSPECIFIED 三种，分别表示
*       固定大小、最大限制和无限制。子 View 根据 MeasureSpec 在 onMeasure() 中计算自己的 measuredWidth
*       和 measuredHeight
*
* MeasureSpec本质上是一个int
* 高2位 -> Mode
  低30位 -> Size

*       31       30 29------------------0
        +----------+--------------------+
        |   Mode   |        Size        |
        +----------+--------------------+

*
* MATCH_PARENT 实现原理
*       MATCH_PARENT 本质上不是一种尺寸，而是一种布局请求：我的大小希望和父容器给我的可用空间一样大。”
*  MATCH_PARENT 并不是直接等于父 View 的宽高，而是在测量阶段由父容器通过 ViewGroup.getChildMeasureSpec() 计算得到。
* 父容器会根据自身的 MeasureSpec、Padding、Margin 等信息，为子 View 生成一个新的 MeasureSpec。对于 MATCH_PARENT，
* 通常会转换为 EXACTLY + 可用空间大小，然后传递给子 View，因此子 View 最终测量出的尺寸会填满父容器的剩余可用空间。

*
* WRAP_CONTENT 实现原理
*   WRAP_CONTENT 不是“包裹内容”，而是“父 View 给一个最大边界（AT_MOST），子 View 自己决定最终大小”
*     WRAP_CONTENT 在测量阶段会被父容器通过 getChildMeasureSpec() 转换成 AT_MOST 模式的 MeasureSpec，
*     表示子 View 的尺寸不能超过父容器提供的最大可用空间。随后子 View 在 onMeasure() 中根据自身内容计算实际需要的大小，
*     并取 min(内容大小, AT_MOST限制) 作为最终测量结果。因此 WRAP_CONTENT 的本质是“有上限的自适应内容大小”，
*     而不是简单地等于内容大小。
*
        * 一、父布局 = EXACTLY（父有固定大小）
        子固定值 → EXACTLY + 固定大小
        子 match_parent → EXACTLY + 父剩余空间
        子 wrap_content → AT_MOST + 父可用大小（不能超父）
        二、父布局 = AT_MOST（父有最大上限）
        子固定值 → EXACTLY + 固定大小
        子 match_parent → AT_MOST + 父上限大小
        子 wrap_content → AT_MOST + 父上限大小
        三、父布局 = UNSPECIFIED（父无约束，如 ScrollView）
        子固定值 → EXACTLY + 固定大小
        子 match_parent → UNSPECIFIED + 0
        子 wrap_content → UNSPECIFIED + 0
        最关键的 3 条规律（一眼记住）
        子是固定值 → 永远 EXACTLY
        父是 UNSPECIFIED → 除了子固定值，其他全是 UNSPECIFIED
        子 wrap_content → 永远不会是 EXACTLY
*
*
* View 的 draw 流程：
        1. 绘制背景
        background.draw(canvas)

        2. 绘制自己
        onDraw(canvas)

        3. 绘制子 View
        dispatchDraw(canvas)

        4. 绘制装饰
        onDrawForeground(canvas) / onDrawScrollBars(canvas)

 draw 阶段负责真正绘制内容，ViewGroup 通过 dispatchDraw() 递归绘制子 View；
 * 子 View 的尺寸和绘制范围可以超过父 View，但是否显示取决于父级裁剪规则，如 clipChildren

*
*
* 为什么 TextView 的 wrap_content 能正常工作，而自定义 View 不行？
*   TextView重写了onMeasure()能够根据文字内容计算尺寸
    普通View没有内容概念 ,默认直接采用MeasureSpec给出的最大值

*
*
* requestLayout 和 invalidate 区别
    * requestLayout() 用于通知系统当前 View 的尺寸或位置可能发生变化，需要重新执行 Measure、Layout 和 Draw 流程；
    * invalidate() 用于通知系统当前 View 的显示内容发生变化，但尺寸和位置不变，因此只需要重新执行 Draw 流程。
    * 前者开销更大，后者开销更小，实际开发中涉及尺寸变化使用 requestLayout()，仅内容刷新使用 invalidate()。

*
* ViewGroup 如何摆放子 View？
*       ViewGroup 的职责就是计算每个子 View 的位置，然后调用 child.layout() 把子 View 放到指定位置。
*
* onDetachedFromWindow 是什么？
    * onDetachedFromWindow() 是 View 从 Window 中移除时回调的方法。
    * 它通常用于资源释放和生命周期管理，例如停止动画、终止线程、移除 Handler 消息、取消网络订阅、注销广播接收器以及释放播放器或 Camera 资源等，以避免内存泄漏和无效的后台任务。
    * 它通常与 onAttachedToWindow() 配对使用，分别负责资源初始化和资源释放。需要注意的是，
    * onDetachedFromWindow() 并不代表 View 被销毁，在 RecyclerView、ViewPager 等场景中，
    * 一个 View 可能多次 attach 和 detach


* 实现自定义 View
        * 一般先在 attrs.xml 中定义自定义属性，
        * 然后在 XML 布局中引用该 View 并配置属性；
        * 接着在构造方法中通过 TypedArray 读取属性值；
        * 之后重写 onMeasure 处理测量逻辑，尤其是 wrap_content；
        * 最后重写 onDraw，通过 Canvas 和 Paint 完成具体绘制
        *
        *
        *
    为什么有事件分发机制？
*       view是树形结构的，view会重叠，点击会无法判断，所以需要事件分发来统一调度
*   事件传递路线是固定的：
        Activity → ViewGroup → View
*
    * 三个核心方法（必须记住）
    1. dispatchTouchEvent()
    作用：分发事件
    谁有：Activity、ViewGroup、View 都有
    含义：事件来了，先传给我，我决定往下发还是自己处理
    2. onInterceptTouchEvent()
    作用：拦截事件
    谁有：只有 ViewGroup 有
    含义：要不要把事件截下来，不给子 View
    3. onTouchEvent()
    作用：处理事件
    谁有：ViewGroup、View 都有
    返回 true = 消费了（事件到此结束）
    返回 false = 不消费（往上抛）
    *
    *
    事件分发流程
    * 事件从 Activity → ViewGroup → View 逐层分发；
    * ViewGroup 通过 onInterceptTouchEvent 决定是否拦截；
    * View 通过 onTouchEvent 决定是否消费；
    * 一旦某个 View 在 ACTION_DOWN 返回 true，则整个事件序列优先交给它处理，除非父 ViewGroup 后续拦截，此时子 View 会收到 ACTION_CANCEL。
*
* */
class ViewDemo {

}