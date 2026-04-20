package com.example.androidlearn.decorator

/**
 * 抽象装饰器：持有被装饰对象引用
 */
/*
* 等价于java写法
* // Java 版抽象装饰器
public abstract class ViewDecorator implements ClickableView {
    // 1. 成员变量需显式声明+赋值
    protected ClickableView decoratedView;

    // 2. 必须写构造器，手动给成员变量赋值
    public ViewDecorator(ClickableView decoratedView) {
        this.decoratedView = decoratedView;
    }

    // 3. 重写方法需显式写 @Override
    @Override
    public void performClick() {
        decoratedView.performClick();
    }
}
* */
abstract class ViewDecorator(protected val decoratedView: ClickableView) : ClickableView {
    override fun performClick() {
        decoratedView.performClick()
    }
}