package com.bruce.sx.constants

object ApiConstants {
    //1.const只能修饰val，不能修饰var类型变量
    //2.const 只允许在top-level级别和object（伴随对象也是obejct）中声明。

   // 1）const val 修饰的属性相当于java中的public final static修饰的常量，可以通过类名直接访问。
    //（2）val 修饰的属性相当于java中private final static修饰的常量，由于可见行为private，所以只能通过生成getter方法访问。
    //（3）出于性能考虑，使用const val方式可以避免频繁函数调用。

    const val BASE_URL = "https://www.wanandroid.com"
}