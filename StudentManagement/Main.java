
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner INPUT = new Scanner(System.in);

    private static final StudentManager MANAGER =
        new StudentManager(
            new StudentFileRepository("students.dat")
        );

    public static void main(String[] args) {

        System.out.println("Student Management System");

        boolean running = true;

        while (running) {

            showMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    System.out.println(
                        "Application closed. Goodbye!"
                    );
                    running = false;
                    break;

                default:
                    System.out.println(
                        "Please choose a number from 1 to 6."
                    );
            }
        }

        INPUT.close();
    }

    private static void showMenu() {

        System.out.println();
        System.out.println("========== STUDENT MENU ==========");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.println("==================================");
    }

    private static void addStudent() {

        System.out.println("\nAdd Student");

        int id = readPositiveInt("Student ID: ");

        String name = readNonEmptyText("Name: ");

        int age = readAge("Age: ");

        String department =
            readNonEmptyText("Department: ");

        String email = readEmail("Email: ");

        try {

            Student student = new Student(
                id, name, age, department, email
            );

            if (MANAGER.addStudent(student)) {

                System.out.println(
                    "Student added successfully."
                );

            } else {

                System.out.println(
                    "Student ID already exists or record could not be saved."
                );
            }

        } catch (IllegalArgumentException exception) {

            System.out.println(exception.getMessage());
        }
    }

    private static void viewStudents() {

        List<Student> students = MANAGER.getAllStudents();

        if (students.isEmpty()) {

            System.out.println(
                "No student records available."
            );

            return;
        }

        System.out.println("\nStudent Records");

        for (Student student : students) {
            student.display();
        }
    }

    private static void searchStudent() {

        int id = readPositiveInt("Enter Student ID: ");

        Student student = MANAGER.findStudent(id);

        if (student == null) {

            System.out.println("Student not found.");

        } else {

            System.out.println("Student found:");
            student.display();
        }
    }

    private static void updateStudent() {

        int id = readPositiveInt(
            "Enter Student ID to update: "
        );

        if (MANAGER.findStudent(id) == null) {

            System.out.println("Student not found.");
            return;
        }

        String name = readNonEmptyText("New name: ");

        int age = readAge("New age: ");

        String department =
            readNonEmptyText("New department: ");

        String email = readEmail("New email: ");

        try {

            if (MANAGER.updateStudent(
                    id, name, age, department, email)) {

                System.out.println(
                    "Student updated successfully."
                );

            } else {

                System.out.println(
                    "Update could not be saved. Previous details were retained."
                );
            }

        } catch (IllegalArgumentException exception) {

            System.out.println(
                "Invalid student details: " + exception.getMessage()
            );
        }
    }

    private static void deleteStudent() {

        int id = readPositiveInt(
            "Enter Student ID to delete: "
        );

        if (MANAGER.deleteStudent(id)) {

            System.out.println(
                "Student deleted successfully."
            );

        } else {

            System.out.println(
                "Student not found or deletion could not be saved."
            );
        }
    }

    private static int readInt(String prompt) {

        while (true) {

            System.out.print(prompt);

            String value = INPUT.nextLine().trim();

            try {
                return Integer.parseInt(value);

            } catch (NumberFormatException exception) {

                System.out.println(
                    "Enter a valid whole number."
                );
            }
        }
    }

    private static int readPositiveInt(String prompt) {

        while (true) {

            int value = readInt(prompt);

            if (value > 0) {
                return value;
            }

            System.out.println(
                "Value must be greater than zero."
            );
        }
    }

    private static int readAge(String prompt) {

        while (true) {

            int age = readInt(prompt);

            if (age >= 1 && age <= 120) {
                return age;
            }

            System.out.println(
                "Age must be between 1 and 120."
            );
        }
    }

    private static String readNonEmptyText(String prompt) {

        while (true) {

            System.out.print(prompt);

            String value = INPUT.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println(
                "This field cannot be empty."
            );
        }
    }

    private static String readEmail(String prompt) {

        while (true) {

            String email = readNonEmptyText(prompt);

            if (email.matches(
                "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {

                return email;
            }

            System.out.println(
                "Enter a valid email address."
            );
        }
    }
}
