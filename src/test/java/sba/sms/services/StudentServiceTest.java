package sba.sms.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.CommandLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


public class StudentServiceTest {

    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        studentService = new StudentService();
    }

    @Test
    public void testValidateStudent() {
        studentService.setupStudentData();

        boolean isValid = studentService.validateStudent("test@gmail.com", "password");
        assertTrue(isValid, "The student should be valid with correct credentials");

        boolean isInvalid = studentService.validateStudent("invalid@gmail.com", "wrongpassword");
        assertFalse(isInvalid, "The student should be invalid with incorrect credentials");
    }

    @Test
    public void testRegisterStudentToCourse() {
        studentService.setupStudentData();

        studentService.registerStudentToCourse("test@gmail.com", 1);

        List<Course> courses = studentService.getStudentCourses("test@gmail.com");
        assertEquals(1, courses.size(), "The student should be registered to one course");
    }
}
