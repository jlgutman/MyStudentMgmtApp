package edu.miu.cs.cs425.studentmgmt;

import edu.miu.cs.cs425.studentmgmt.model.Student;
import edu.miu.cs.cs425.studentmgmt.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyStudentMgmtAppTests {

    @Autowired
    private StudentService studentService;

    @Autowired
    private MyStudentMgmtApp app;

    @Test
    void saveStudent_persistsToDatabase() {
        Student s = new Student(
            "000-61-0002", "Bob", null, "Galverston",
            2.87, LocalDate.of(2020, 9, 1)
        );
        app.saveStudent(s);

        List<Student> all = studentService.getAllStudents();
        assertTrue(all.stream().anyMatch(st -> "000-61-0002".equals(st.getStudentNumber())));
    }

    @Test
    void savedStudent_hasCorrectFields() {
        Student s = new Student(
            "000-61-0003", "Carlos", "M", "Lopez",
            3.90, LocalDate.of(2021, 1, 15)
        );
        Student saved = studentService.saveStudent(s);

        assertEquals("Carlos", saved.getFirstName());
        assertEquals("Lopez", saved.getLastName());
        assertEquals(3.90, saved.getCgpa());
        assertTrue(saved.getStudentId() > 0);
    }
}
