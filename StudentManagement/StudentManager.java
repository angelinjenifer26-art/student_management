
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentManager {

    private final List<Student> students;
    private final StudentFileRepository repository;

    public StudentManager(StudentFileRepository repository) {

        this.repository = repository;
        this.students = new ArrayList<>();

        loadStudents();
    }

    private void loadStudents() {

        try {
            Object savedData = repository.load();

            if (savedData == null) {
                return;
            }

            if (!(savedData instanceof ArrayList<?>)) {
                System.out.println(
                    "Saved data has an unexpected format."
                );
                return;
            }

            ArrayList<?> records = (ArrayList<?>) savedData;

            for (Object record : records) {
                if (record instanceof Student) {
                    students.add((Student) record);
                }
            }

        } catch (IOException | ClassNotFoundException exception) {

            System.out.println(
                "Could not load records: " + exception.getMessage()
            );
        }
    }

    public boolean addStudent(Student student) {

        if (findStudent(student.getId()) != null) {
            return false;
        }

        students.add(student);

        if (!saveChanges()) {
            students.remove(student);
            return false;
        }

        return true;
    }

    public Student findStudent(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    public List<Student> getAllStudents() {

        return Collections.unmodifiableList(students);
    }

    public boolean updateStudent(
            int id,
            String name,
            int age,
            String department,
            String email) {

        Student student = findStudent(id);

        if (student == null) {
            return false;
        }

        String oldName = student.getName();
        int oldAge = student.getAge();
        String oldDepartment = student.getDepartment();
        String oldEmail = student.getEmail();

        try {
            student.setName(name);
            student.setAge(age);
            student.setDepartment(department);
            student.setEmail(email);

        } catch (IllegalArgumentException exception) {

            restoreStudent(
                student,
                oldName,
                oldAge,
                oldDepartment,
                oldEmail
            );

            throw exception;
        }

        if (!saveChanges()) {

            restoreStudent(
                student,
                oldName,
                oldAge,
                oldDepartment,
                oldEmail
            );

            return false;
        }

        return true;
    }

    public boolean deleteStudent(int id) {

        Student student = findStudent(id);

        if (student == null) {
            return false;
        }

        students.remove(student);

        if (!saveChanges()) {
            students.add(student);
            return false;
        }

        return true;
    }

    private void restoreStudent(
            Student student,
            String name,
            int age,
            String department,
            String email) {

        student.setName(name);
        student.setAge(age);
        student.setDepartment(department);
        student.setEmail(email);
    }

    private boolean saveChanges() {

        try {
            repository.save(new ArrayList<>(students));
            return true;

        } catch (IOException exception) {

            System.out.println(
                "Unable to save changes: " + exception.getMessage()
            );

            return false;
        }
    }
}
