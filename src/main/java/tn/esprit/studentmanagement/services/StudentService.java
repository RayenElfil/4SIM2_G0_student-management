package tn.esprit.studentmanagement.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.StudentRepository;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        log.info("Récupération de la liste complète des étudiants");
        List<Student> students = studentRepository.findAll();
        log.debug("Nombre d'étudiants trouvés : {}", students.size());
        return students;
    }

    public Student getStudentById(Long id) {
        log.info("Récupération de l'étudiant avec id={}", id);
        return studentRepository.findById(id)
                .map(student -> {
                    log.debug("Étudiant trouvé : {}", student);
                    return student;
                })
                .orElseGet(() -> {
                    log.warn("Aucun étudiant trouvé avec id={}", id);
                    return null;
                });
    }

    public Student saveStudent(Student student) {
        log.info("Sauvegarde d'un étudiant");
        log.debug("Détails de l'étudiant à sauvegarder : {}", student);
        Student saved = studentRepository.save(student);
        log.info("Étudiant sauvegardé avec succes");
        return saved;
    }

    public void deleteStudent(Long id) {
        log.info("Suppression de l'étudiant avec id={}", id);
        try {
            studentRepository.deleteById(id);
            log.info("Étudiant avec id={} supprimé avec succès", id);
        } catch (Exception e) {
            log.error("Erreur lors de la suppression de l'étudiant avec id={}", id, e);
            throw e;
        }
    }
}
