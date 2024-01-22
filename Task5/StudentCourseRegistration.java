import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    public String courseCode;
    public String title;
    public String description;
    public int capacity;
    public String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }
}

class Student {
    public String studentID;
    public String name;
    public List<String> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    public void dropCourse(String courseCode) {
        registeredCourses.remove(courseCode);
    }
}

class University {
    public List<Course> courses;
    public List<Student> students;

    public University() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void registerStudent(Student student) {
        students.add(student);
    }

    public void displayAvailableCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println("------------------------------");
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Capacity: " + course.getCapacity());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("------------------------------");
        }
    }

    public void displayStudentCourses(Student student) {
        System.out.println("Registered Courses for " + student.getName() + ":");
        for (String courseCode : student.getRegisteredCourses()) {
            Course course = getCourseByCode(courseCode);
            if (course != null) {
                System.out.println("Course Code: " + course.getCourseCode());
                System.out.println("Title: " + course.getTitle());
                System.out.println("------------------------------");
            }
        }
    }

    public void registerStudentForCourse(Student student, String courseCode) {
        Course course = getCourseByCode(courseCode);
        if (course != null && course.getCapacity() > 0) {
            student.registerCourse(courseCode);
            courseCapacityUpdate(course, -1);
            System.out.println("Registration successful!");
        } else {
            System.out.println("Course registration failed. Either course not found or no available slots.");
        }
    }

    public void dropStudentFromCourse(Student student, String courseCode) {
        Course course = getCourseByCode(courseCode);
        if (course != null) {
            student.dropCourse(courseCode);
            courseCapacityUpdate(course, 1);
            System.out.println("Course dropped successfully!");
        } else {
            System.out.println("Course not found. Unable to drop.");
        }
    }

    public Course getCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public void courseCapacityUpdate(Course course, int change) {
        course.capacity += change;
    }
}

public class StudentCourseRegistration {
    public static void main(String[] args) {
        University university = new University();

        // Adding sample courses
        university.addCourse(new Course("CSCI101", "Introduction to Programming", "Fundamentals of programming", 30, "Mon/Wed 10:00 AM"));
        university.addCourse(new Course("MATH201", "Calculus I", "Limits, derivatives, and integrals", 25, "Tue/Thu 2:00 PM"));
        university.addCourse(new Course("PHYS301", "Physics Mechanics", "Classical mechanics principles", 20, "Mon/Fri 1:00 PM"));

        // Adding sample students
        university.registerStudent(new Student("12205115", "Bekrom Roy"));
        university.registerStudent(new Student("12214314", "Aniket Kumar"));
        university.registerStudent(new Student("12208115", "Amarjit Kumar"));
        university.registerStudent(new Student("12205421", "Amritanshu Singh"));
        university.registerStudent(new Student("12208341", "Rakesh Das"));
        university.registerStudent(new Student("12202121", "Akash Kumar"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nUniversity Management System Menu:");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Display Student's Registered Courses");
            System.out.println("3. Register Student for Course");
            System.out.println("4. Drop Student from Course");
            System.out.println("5. Exit");

            System.out.print("Enter your choice (1-5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    university.displayAvailableCourses();
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    String studentID = scanner.next();
                    Student student = getStudentByID(university, studentID);
                    if (student != null) {
                        university.displayStudentCourses(student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    String regStudentID = scanner.next();
                    Student regStudent = getStudentByID(university, regStudentID);
                    if (regStudent != null) {
                        university.displayAvailableCourses();
                        System.out.print("Enter course code to register: ");
                        String regCourseCode = scanner.next();
                        university.registerStudentForCourse(regStudent, regCourseCode);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    String dropStudentID = scanner.next();
                    Student dropStudent = getStudentByID(university, dropStudentID);
                    if (dropStudent != null) {
                        university.displayStudentCourses(dropStudent);
                        System.out.print("Enter course code to drop: ");
                        String dropCourseCode = scanner.next();
                        university.dropStudentFromCourse(dropStudent, dropCourseCode);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting University Management System. Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    public static Student getStudentByID(University university, String studentID) {
        for (Student student : university.students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }
}
