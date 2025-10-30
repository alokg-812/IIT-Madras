from flask import Flask, render_template, request, redirect, url_for
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)

# ✅ Correct DB URI as required
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///database.sqlite3'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)

# ---------------------- MODELS ----------------------

class Student(db.Model):
    __tablename__ = 'student'
    student_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    roll_number = db.Column(db.String, unique=True, nullable=False)
    first_name = db.Column(db.String, nullable=False)
    last_name = db.Column(db.String)

class Course(db.Model):
    __tablename__ = 'course'
    course_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    course_code = db.Column(db.String, unique=True, nullable=False)
    course_name = db.Column(db.String, nullable=False)
    course_description = db.Column(db.String)

class Enrollments(db.Model):
    __tablename__ = 'enrollments'
    enrollment_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    estudent_id = db.Column(db.Integer, db.ForeignKey('student.student_id'), nullable=False)
    ecourse_id = db.Column(db.Integer, db.ForeignKey('course.course_id'), nullable=False)

# ----------------------------------------------------


@app.route('/')
def index():
    students = Student.query.all()
    return render_template('index.html', students=students)


@app.route('/student/create', methods=['GET', 'POST'])
def create_student():
    if request.method == 'GET':
        return render_template('create.html')

    elif request.method == 'POST':
        roll = request.form['roll']
        f_name = request.form['f_name']
        l_name = request.form['l_name']
        courses = request.form.getlist('courses')

        # Check duplicate roll number
        existing_student = Student.query.filter_by(roll_number=roll).first()
        if existing_student:
            return render_template('exists.html')

        # Create new student
        new_student = Student(roll_number=roll, first_name=f_name, last_name=l_name)
        db.session.add(new_student)
        db.session.commit()

        # Add enrollments
        for c in courses:
            course_id = int(c.split('_')[1])
            enrollment = Enrollments(estudent_id=new_student.student_id, ecourse_id=course_id)
            db.session.add(enrollment)

        db.session.commit()
        return redirect(url_for('index'))


@app.route('/student/<int:student_id>/update', methods=['GET', 'POST'])
def update_student(student_id):
    student = Student.query.get_or_404(student_id)

    if request.method == 'GET':
        enrolls = Enrollments.query.filter_by(estudent_id=student_id).all()
        enrolled_courses = [e.ecourse_id for e in enrolls]
        return render_template('update.html', student=student, enrolled_courses=enrolled_courses)

    elif request.method == 'POST':
        student.first_name = request.form['f_name']
        student.last_name = request.form['l_name']
        selected_courses = request.form.getlist('courses')

        # Delete old enrollments
        Enrollments.query.filter_by(estudent_id=student_id).delete()

        # Add new enrollments
        for c in selected_courses:
            course_id = int(c.split('_')[1])
            db.session.add(Enrollments(estudent_id=student_id, ecourse_id=course_id))

        db.session.commit()
        return redirect(url_for('index'))


@app.route('/student/<int:student_id>/delete')
def delete_student(student_id):
    student = Student.query.get_or_404(student_id)
    Enrollments.query.filter_by(estudent_id=student_id).delete()
    db.session.delete(student)
    db.session.commit()
    return redirect(url_for('index'))


@app.route('/student/<int:student_id>')
def student_details(student_id):
    student = Student.query.get_or_404(student_id)
    enrollments = db.session.query(Course).join(Enrollments, Course.course_id == Enrollments.ecourse_id)\
        .filter(Enrollments.estudent_id == student_id).all()
    return render_template('details.html', student=student, courses=enrollments)


if __name__ == '__main__':
    app.run(debug=True)
