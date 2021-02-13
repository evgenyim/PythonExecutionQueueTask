import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.BufferedReader

fun main() {
    println("Enter path to your python interpreter:")
    val pythonPath = readLine()

    var start = 0

    val job = GlobalScope.launch {
        while(true) {
            println("Running for $start seconds")
            start++
            delay(1000)
        }
    }

    println("Trying to execute command")
    val t = try { Runtime.getRuntime().exec("$pythonPath -m timeit -r 10") }
            catch (e: Exception) { println("Error, wrong path"); return }
    val reader = BufferedReader(t.inputStream.reader())
    println(reader.readText())
    println("Execution finished!")
    job.cancel()
}

