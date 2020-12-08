import groovy.lang.GroovyClassLoader
import kotlin.test.Test

class Test
{
    @Test
    fun test()
    {
        val l = GroovyClassLoader()
        val c = l.parseClass("""
            class T {
                public static void main() {
                    println("Hello, World!");
                }
            }
        """.trimIndent())

        println(c.getMethod("main").invoke(null))
    }
}