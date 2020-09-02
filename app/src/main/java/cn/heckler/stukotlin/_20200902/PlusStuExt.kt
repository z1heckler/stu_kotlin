package cn.heckler.stukotlin._20200902

import java.lang.IllegalArgumentException

/**
 * 1. 这里用到了运算符重载，Kotlin 在 Maps.kt 文件里面，定义了一系列用关键字 operator 声明的 Map 的扩展函数。
 */
public fun study1() {
    val numberMap = mapOf("one" to 1, "two" to 2, "three" to 3)
    //plus(+)
    println(numberMap + Pair("four", 4))
    println(numberMap + Pair("one", 10))
    println(numberMap + Pair("five", 5) + Pair("one", 11))
    //minus(-)
    println(numberMap - "one")
    println(numberMap - listOf("tow", "four"))
}

/**
 * 2. Map集合的默认值  .withDefault { any }
 */
fun study2() {
    val map = mapOf(
            "java" to 1,
            "kotlin" to 2,
            "python" to 3
    ).withDefault { "?" }

    println(map.getValue("java"))
    println(map.getValue("kotlin"))
    println(map.getValue("c++"))
}

/**
 * 3. 使用require 或者check 函数作为条件检查
 */

fun study3() {
    //传统做法
    val age = -1
    if (age <= 0) {
        throw IllegalArgumentException("age must not be negative")
    }
    //使用require去检查
    require(age > 0) { "age must not be negative" }

    //使用checkNotNUll检查
    val name: String? = null

    checkNotNull(name) {
        "name must not be null"
    }
}

/**
 * 4. 区分和使用run with let also apply
 * 三个方面  是否是扩展函数；作用域函数的参数（this、it）；作用域函数的返回值（调用本身、其他类型即最后一行）。
 *
 * 函数	是否是扩展函数	函数参数(this、it)	返回值(调用本身、最后一行)
with	不是	        this	                最后一行
T.run	是	            this	                最后一行
T.let	是	            it	                    最后一行
T.also	是	            it	                    调用本身
T.apply	是	            this	                调用本身
 */
//with 和 run
// with 是普通函数
// T.run 是扩展函数，需要对象去调用
fun study4() {
    var name: String? = "null"
    with(name) {
        val subName = this!!.substring(1, 2)
    }

    name?.run { val subName = name.substring(1, 2) }
            ?: throw IllegalArgumentException("name must not be null")

//this 和 it
//参数this可以省略
    name?.run { println("The length is ${this.length}, this 是可以忽略的${length}") }

//参数it 可以自定义
    name?.let { println("The length is ${it.length}") }

    name?.let { str ->
        println("The length is ${str.length}")
    }

//回调本身和其他类型
//T.let回调最后一行， T.also回调而本身
    var str1 = "hi-dh1"
    str1 = str1.also {
        val result = 1 * 1
        "juejin"
    }
    println("name = ${str1}")

    str1 = str1.let {
        val result = 1 * 1
        "lalala"
    }
    println("name = ${str1}")
}

/**
 * in 和 when 关键字
 * in 关键字其实是contains 操作符的简写
 */
fun study5() {
    //用in 和when 验证用户输入
    var input = "adsf"
    when (input) {
        in Regex("[0-9]") -> println("contains a number")
        in Regex("[a-zA-Z]") -> println("contains a letter")
    }

    val language = "kotlin"
    when (language) {
        in listOf("java", "kotlin") -> println("found ${language}")
        in setOf("python", "c++") -> println("found ${language}")
    }

}

/**
 * kotlin 单例的三种写法
 * 使用object实现单例
 * 使用by lazy 实现单例
 * 可接受参数的单例(这个没看，哈哈)
 *
 * object关键字就是一个单例，如下为反编译代码
 * 优点   饿汉式且线程安全
 * 缺点   类加载时就初始化，浪费内存
 *
 * public final class WorkSingleton {
 *      public static final WorkSingleton INSTANCE;
 *      static {
 *          WorkSingleton var0 = new WorkSingleton();
 *          INSTANCE = var0;
 *      }
 * }
 *
 */
class WorkSingleton private constructor() {

    companion object{

        //方式1
//        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
//            WorkSingleton()
//        }
        //方式2
        val INSTANCE by lazy { WorkSingleton }
//        上面代码所示 mode = LazyThreadSafetyMode.SYNCHRONIZED，lazy 默认的模式，可以省掉，这个模式的意思是：如果有多个线程访问，只有一条线程可以去初始化 lazy 对象。
//        当 mode = LazyThreadSafetyMode.PUBLICATION 表达的意思是：对于还没有被初始化的 lazy 对象，可以被不同的线程调用，如果 lazy 对象初始化完成，其他的线程使用的是初始化完成的值。
//        mode = LazyThreadSafetyMode.NONE 表达的意思是：只能在单线程下使用，不能在多线程下使用，不会有锁的限制，也就是说它不会有任何线程安全的保证以及相关的开销。


    }
}



//使用扩展函数重写contains操作符
operator fun Regex.contains(text: CharSequence): Boolean {
    return this.containsMatchIn(text);
}




