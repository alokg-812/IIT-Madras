package Java.OPPEPractice;

public class Student {
    private String name;
    private String[] courses;

    // Primary constructor: deep-copy the courses array
    public Student(String name, String[] courses) {
        this.name = name;
        if (courses != null) {
            this.courses = new String[courses.length];
            for (int i = 0; i < courses.length; i++) {
                this.courses[i] = courses[i];
            }
        } else {
            this.courses = new String[0];
        }
    }

    // Copy constructor: deep copy so original is not affected by changes to the copy
    public Student(Student other) {
        this.name = other.name;
        if (other.courses != null) {
            this.courses = new String[other.courses.length];
            for (int i = 0; i < other.courses.length; i++) {
                this.courses[i] = other.courses[i];
            }
        } else {
            this.courses = new String[0];
        }
    }

    // Setter for name
    public void setName(String n) {
        this.name = n;
    }

    // Setter for a course at a given index (safe: checks bounds)
    public void setCourse(int index, String c) {
        if (index >= 0 && index < courses.length) {
            courses[index] = c;
        } else {
            // Optionally handle invalid index; here we ignore or could throw
            throw new IndexOutOfBoundsException("Invalid course index: " + index);
        }
    }

    // Getter for name
    public String getName() {
        return this.name;
    }

    // Getter for a specific course (safe: checks bounds)
    public String getCourse(int index) {
        if (index >= 0 && index < courses.length) {
            return courses[index];
        } else {
            throw new IndexOutOfBoundsException("Invalid course index: " + index);
        }
    }

    // Optional: return a copy of the courses array to keep encapsulation
    public String[] getCourses() {
        String[] copy = new String[courses.length];
        for (int i = 0; i < courses.length; i++) {
            copy[i] = courses[i];
        }
        return copy;
    }
}
