package rs.dodalovic.extension_functions

import java.util.*

class Student(val name: String, val lastname: String) {
    var marks = mutableListOf<Int>()
    var average: Double = 0.toDouble()
        get () = marks.average()
    fun addMark(mark: Int) = marks.add(mark)
    override fun toString() = "\n{'name':'$name','lastname':'$lastname','average':'$average','marks':'$marks'}\n"
}

val List<Student>.averageMark: Double get() = this.flatMap { it.marks }.average()
val List<Student>.bestStudent: Student? get() = this.sortedByDescending { it.average }.first()

fun List<Student>.aboveAndBelowAverage() = this.partition { it.average >= this.averageMark }
val List<Student>.highestMark: Int get() = this.flatMap { it.marks }.max() ?: 0
fun Random.between(lower: Int, upper: Int) = this.nextInt(upper - lower) + lower

fun Student.addRandomMarks(): Student {
    (1..5).forEach { this.addMark(Random().between(1, 6)) }
    return this
}

fun main(args: Array<String>) {
    val studentsCount = 10
    val students = (1..studentsCount).map { Student("name_$it", "lastname_$it") }.map { it.addRandomMarks() }.toList()

    println("**** Students average: ${students.averageMark} ****")
    val (aboveAverageStudents, belowAverageStudents) = students.aboveAndBelowAverage()
    println("\nAbove average: \n$aboveAverageStudents\n")
    println("\nBelow average: \n$belowAverageStudents\n")
    println("\nHighest mark: \n${students.highestMark}\n")
    println("\nBest student: \n${students.bestStudent}\n")
}

