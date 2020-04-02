package com.bruce.sx.pattern

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.pattern
 * @description:单列模式
 * @date: 2020/3/31
 * @time:  18:23
 */
class SingleClass {

    /**
     *双层加锁判断，并且线程安全（推荐使用）
     */
//    public class SingleClass {
//        private static MyInstanceinstance;
//        public static MyInstance getInstance() {
//            if (instance ==null) {
//                synchronized (MyInstance.class) {
//                    if(instance==null){
//                        instance=new MyInstance();
//                    }
//                }
//            }
//            return  instance;
//        }
//    }

    companion object{
        val instance by lazy (LazyThreadSafetyMode.SYNCHRONIZED){
            SingleClass()
        }
    }

    //内部类方式实现单例（推荐使用）
//    public class SingleClass {
//        private static MyInstanceinstance;
//        public static MyInstance getsInstance() {
//            if (instance ==null) {
//                synchronized (MyInstance.class) {
//                    if(instance==null){
//                        instance=new MyInstance();
//                    }
//                }
//            }
//            return  instance;
//        }
//    }

//    companion object{
//        fun getInstance() = SingletonHolder.holder
//    }
//    private object Helper{
//        val instance = SingleClass()
//    }
}