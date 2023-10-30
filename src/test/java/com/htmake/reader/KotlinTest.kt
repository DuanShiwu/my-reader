package com.htmake.reader

import io.vertx.core.Vertx
import kotlin.reflect.KProperty

fun main() {
    val bean = Bean()
    println("bean.str2:${bean.str2}")
    println("Init Bean")
    println(bean.str)
    println(bean.str)
}

 class Bean {
    init {
        println("init Bean 1")
    }

    val str by lazy {
        println("Init lazy")
        "Hello World"
    }
    val str2 = "str2".also {
        println("str2:$it")
        "str2new"
    }
    val str3 = "str3".apply {
        println("str3")
        "str3new"
    }

    init {
        println("init Bean 2")
    }
}

class People {
    val name: String? = null
    var age: Int = 0
    val isAbove18: Boolean = false

    //email属性进行委托，把它委托给ProduceEmail类
    var email: String by ProduceEmail()

    val vertx by lazy({ Vertx.vertx() })
}

class ProduceEmail {
    private val emails = arrayListOf("111@qq.com")

    //对应于被委托属性的get函数
    operator fun getValue(people: People, property: KProperty<*>): String {
        println("getValue: 操作的属性名是 ${property.name}->${emails.last()}")
        return emails.last()
    }

    //对于被委托属性的get函数
    operator fun setValue(people: People, property: KProperty<*>, s: String) {
        println("setValue: 操作的属性名是 ${property.name}->$s")
        emails.add(s)
    }

}