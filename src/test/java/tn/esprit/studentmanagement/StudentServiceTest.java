package tn.esprit.studentmanagement;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.StudentRepository;
import tn.esprit.studentmanagement.services.StudentService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testGetAllStudents() {
        Student s1 = new Student();
        Student s2 = new Student();
        when(studentRepository.findAll()).thenReturn(Arrays.asList(s1, s2));

        List<Student> result = studentService.getAllStudents();

        assertEquals(2, result.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testGetStudentByIdFound() {
        Long id = 1L;
        Student s = new Student();
        when(studentRepository.findById(id)).thenReturn(Optional.of(s));

        Student result = studentService.getStudentById(id);

        assertNotNull(result);
        verify(studentRepository, times(1)).findById(id);
    }

    @Test
    void testGetStudentByIdNotFound() {
        Long id = 2L;
        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        Student result = studentService.getStudentById(id);

        assertNull(result);
        verify(studentRepository, times(1)).findById(id);
    }

    @Test
    void testSaveStudent() {
        Student s = new Student();
        when(studentRepository.save(s)).thenReturn(s);

        Student result = studentService.saveStudent(s);

        assertNotNull(result);
        verify(studentRepository, times(1)).save(s);
    }

    @Test
    void testDeleteStudent() {
        Long id = 3L;

        studentService.deleteStudent(id);

        verify(studentRepository, times(1)).deleteById(id);
    }
}
