package designpattern

import java.io.Serializable

/***
 * DCL(double check lock)改造懒汉式单例
 * 我们知道线程安全的单例模式直接是使用synchronized同步锁，
 * 锁住getInstance方法，每一次调用该方法的时候都得获取锁，
 * 但是如果这个单例已经被初始化了，其实按道理就不需要申请同步锁了，
 * 直接返回这个单例类实例即可。于是就有了DCL实现单例方式。
 */
class KLazilyDCLSingleton private constructor() : Serializable { //private constructor
    fun doSomething() {
        println("do something in ${this.javaClass.simpleName}")
    }

    companion object {
        /**
         * 通过@JvmStatic注解，使得在Java中调用instance直接像调用静态函数一样
         */
        @JvmStatic
        val instance: KLazilyDCLSingleton by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { KLazilyDCLSingleton() }
    }

    /**
     * 防止单例对象在反序列化时重新生成对象
     */
    private fun readResolve(): Any {
        return instance
    }
}

/***
 * 饿汉式单例
 * 饿汉式单例模式是实现单例模式比较简单的一种方式，
 * 它有个特点就是不管需不需要该单例实例，该实例对象都会被实例化。
 */
object KSingleton : Serializable {
    fun doSomething() {
        println("do something in ${this.javaClass.simpleName}")
    }

    private fun readResolve(): Any {
        return KSingleton
    }
}

/***
 * 线程安全的懒汉式
 * 可是有时候我们并不想当类加载的时候就去创建这个单例实例，
 * 而是想当我们使用这个实例的时候才去初始化它。于是乎就有了懒汉式的单例模式
 */
class KLazilySingleton private constructor() : Serializable {
    fun doSomething() {
        println("do something in ${this.javaClass.simpleName}")
    }

    companion object {
        private var mInstance: KLazilySingleton? = null
            get() {
                return field ?: KLazilySingleton()
            }

        @JvmStatic
        @Synchronized
        fun getInstance(): KLazilySingleton {
            return requireNotNull(mInstance)
        }
    }

    private fun readResolve(): Any {
        return getInstance()
    }
}

/***
 * 静态内部类单例
 * DCL虽然在一定程度上能解决资源消耗、多余synchronized同步、线程安全等问题，
 * 但是某些情况下还会存在DCL失效问题，尽管在JDK1.5之后通过具体化volatile原语来解决DCL失效问题，
 * 但是它始终并不是优雅一种解决方式，在多线程环境下一般不推荐DCL的单例模式。
 * 所以引出静态内部类单例实现
 */
class KOptimizeSingleton private constructor() : Serializable {

    fun doSomething() {
        println("do something in ${this.javaClass.simpleName}")
    }

    companion object {
        @JvmStatic
        fun getInstance(): KOptimizeSingleton {
            return SingletonHolder.mInstance
        }
    }

    private object SingletonHolder {
        val mInstance: KOptimizeSingleton = KOptimizeSingleton()
    }

    private fun readResolve(): Any {
        return SingletonHolder.mInstance
    }
}

/***
 * 枚举类单例
 * 其实细心的小伙伴就会观察到上面例子中我都会去实现Serializable接口，
 * 并且会去实现readResolve方法。这是为了反序列化会重新创建对象而使得原来的单例对象不再唯一。
 * 通过序列化一个单例对象将它写入到磁盘中，然后再从磁盘中读取出来，从而可以获得一个新的实例对象，
 * 即使构造器是私有的，反序列化会通过其他特殊途径创建单例类的新实例。
 * 然而为了让开发者能够控制反序列化，提供一个特殊的钩子方法那就是readResolve方法，
 * 这样一来我们只需要在readResolve直接返回原来的实例即可，就不会创建新的对象。
 * 枚举单例实现，就是为了防止反序列化，因为我们都知道枚举类反序列化是不会创建新的对象实例的。
 * Java的序列化机制对枚举类型做了特殊处理，一般来说在序列枚举类型时，
 * 只会存储枚举类的引用和枚举常量名称，反序列化的过程中，
 * 这些信息被用来在运行时环境中查找存在的枚举类型对象，
 * 枚举类型的序列化机制保证只会查找已经存在的枚举类型实例，而不是创建新的实例。
 */
enum class KEnumSingleton {
    INSTANCE;

    fun doSomething() {
        println("do something in ${this.javaClass.simpleName}")
    }
}

fun main(args: Array<String>) {
    KSingleton.doSomething()
    KLazilySingleton.getInstance().doSomething()
    KLazilyDCLSingleton.instance.doSomething()
    KOptimizeSingleton.getInstance().doSomething()
    KEnumSingleton.INSTANCE.doSomething()
}