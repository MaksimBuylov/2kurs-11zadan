import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            TeacherCourseManager.connect();
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\n1. Добавить преподавателя");
                System.out.println("2. Добавить курс");
                System.out.println("3. Назначить курс преподавателю");
                System.out.println("4. Удалить курс у преподавателя");
                System.out.println("5. Обновить преподавателя");
                System.out.println("6. Обновить курс");
                System.out.println("7. Показать всех преподавателей и их курсы");
                System.out.println("8. Показать все курсы");
                System.out.println("9. Показать всех преподавателей");
                System.out.println("0. Выход");
                System.out.print("Выберите опцию: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Введите ФИО преподавателя: ");
                        String fullName = scanner.nextLine();
                        System.out.print("Введите возраст преподавателя: ");
                        int age = scanner.nextInt();
                        TeacherCourseManager.addTeacher(fullName, age);
                        break;
                    case 2:
                        System.out.print("Введите название курса: ");
                        String courseName = scanner.nextLine();
                        System.out.print("Введите количество часов: ");
                        int hours = scanner.nextInt();
                        TeacherCourseManager.addCourse(courseName, hours);
                        break;
                    case 3:
                        System.out.print("Введите ID преподавателя: ");
                        int teacherId = scanner.nextInt();
                        System.out.print("Введите ID курса: ");
                        int courseId = scanner.nextInt();
                        TeacherCourseManager.assignCourseToTeacher(teacherId, courseId);
                        break;
                    case 4:
                        System.out.print("Введите ID преподавателя: ");
                        int teacherIdToRemove = scanner.nextInt();
                        System.out.print("Введите ID курса: ");
                        int courseIdToRemove = scanner.nextInt();
                        TeacherCourseManager.removeCourseFromTeacher(teacherIdToRemove, courseIdToRemove);
                        break;
                    case 5:
                        System.out.print("Введите ID преподавателя для обновления: ");
                        int teacherIdUpdate = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Введите новое ФИО преподавателя: ");
                        String newFullName = scanner.nextLine();
                        System.out.print("Введите новый возраст преподавателя: ");
                        int newAge = scanner.nextInt();
                        TeacherCourseManager.updateTeacher(teacherIdUpdate, newFullName, newAge);
                        break;
                    case 6:
                        System.out.print("Введите ID курса для обновления: ");
                        int courseIdUpdate = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Введите новое название курса: ");
                        String newCourseName = scanner.nextLine();
                        System.out.print("Введите новые часы курса: ");
                        int newHours = scanner.nextInt();
                        TeacherCourseManager.updateCourse(courseIdUpdate, newCourseName, newHours);
                        break;
                    case 7:
                        TeacherCourseManager.showTeachersAndCourses();
                        break;
                    case 8:
                        TeacherCourseManager.showCourses();
                        break;
                    case 9:
                        TeacherCourseManager.showTeachers();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Неверная опция.");
                }
            }

            TeacherCourseManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}