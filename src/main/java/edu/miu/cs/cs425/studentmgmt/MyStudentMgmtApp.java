package edu.miu.cs.cs425.studentmgmt;

import edu.miu.cs.cs425.studentmgmt.model.*;
import edu.miu.cs.cs425.studentmgmt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class MyStudentMgmtApp implements CommandLineRunner {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private CourseService courseService;

    public static void main(String[] args) {
        SpringApplication.run(MyStudentMgmtApp.class, args);
    }

    @Override
    public void run(String... args) {
        // Task 2b: persist Classroom first (Student holds the FK)
        Classroom classroom = classroomService.saveClassroom(
            new Classroom("McLaughlin building", "M105")
        );

        // Task 3: persist Course first (join table references both sides)
        Course course = courseService.saveCourse(
            new Course("CS401", "Modern Prog Practices")
        );

        // Task 1: build Student
        Student s1 = new Student(
            "000-61-0001", "Anna", "Lynn", "Smith",
            3.45, LocalDate.of(2019, 5, 24)
        );

        // Task 2a: attach Transcript (cascaded via Student)
        s1.setTranscript(new Transcript("BS Computer Science"));

        // Task 2b: attach Classroom
        s1.setClassroom(classroom);

        // Task 3: attach Course
        s1.setCourses(List.of(course));

        saveStudent(s1);

        System.out.println("\n--- Saved student ---");
        studentService.getAllStudents().forEach(System.out::println);
    }

    public void saveStudent(Student student) {
        studentService.saveStudent(student);
    }
}
