package rs.dodalovic.extension_functions

import org.junit.Test

class StudentTest {

    @Test fun `addRandomMarks adds 5 random marks to student`() {
        val firstStudent = Student("n1", "l1").addRandomMarks()
        assert(firstStudent.marks.size == 5) { "Student should have 5 random marks" }
    }

    @Test fun `highestMark returns highest achieved mark by any student`() {
        val firstStudent = Student("n1", "l1")
        firstStudent.marks = mutableListOf(1, 3, 5)
        val secondStudent = Student("n2", "l2")
        secondStudent.marks = mutableListOf(2, 4)
        val allStudents = listOf(firstStudent, secondStudent)
        assert(allStudents.highestMark == 5) { "5 is highest mark" }
    }

    @Test fun `bestStudent returns best student based on best average mark`() {
        val firstStudent = Student("n1", "l1")
        firstStudent.marks = mutableListOf(4, 5)
        val secondStudent = Student("n2", "l2")
        secondStudent.marks = mutableListOf(3, 4)
        val allStudents = listOf(firstStudent, secondStudent)
        assert(allStudents.bestStudent == firstStudent) { "First student should be the best student" }
    }

    @Test fun `aboveAndBelowAverage splits users in two groups - above and below average`() {
        val first = Student("n2", "l2")
        first.marks = mutableListOf(1, 2)
        val second = Student("n2", "l2")
        second.marks = mutableListOf(2, 3)

        val third = Student("n1", "l1")
        third.marks = mutableListOf(4, 5)
        val fourth = Student("n2", "l2")
        fourth.marks = mutableListOf(3, 4)

        val (above, below) = listOf(first, second, third, fourth).aboveAndBelowAverage()

        assert(above.size == 2)
        assert(above.containsAll(listOf(third, fourth))) { "Third and fourth are above average!" }
        assert(below.size == 2)
        assert(below.containsAll(listOf(first, second))) { "First and second are below average!" }
    }
}