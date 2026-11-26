
import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private String name;
    private int age;
    private String department;
    private String email;

    public Student(int id, String name, int age,
                   String department, String email) {

        if (id <= 0) {
            throw new IllegalArgumentException("Student ID must be positive.");
        }

        this.id = id;
        setName(name);
        setAge(age);
        setDepartment(department);
        setEmail(email);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name.trim();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 1 || age > 120) {
            throw new IllegalArgumentException(
                "Age must be between 1 and 120."
            );
        }
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Department cannot be empty."
            );
        }
        this.department = department.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null ||
            !email.trim().matches(
                "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {

            throw new IllegalArgumentException(
                "Enter a valid email address."
            );
        }

        this.email = email.trim();
    }

    public void display() {
        System.out.println("----------------------------------------");
        System.out.println("Student ID : " + id);
        System.out.println("Name       : " + name);
        System.out.println("Age        : " + age);
        System.out.println("Department : " + department);
        System.out.println("Email      : " + email);
        System.out.println("----------------------------------------");
    }
}
