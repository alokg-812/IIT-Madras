from flask import Flask, render_template, request, url_for
import csv
import io
import base64
import matplotlib
matplotlib.use('Agg')  # Use non-interactive backend
import matplotlib.pyplot as plt

app = Flask(__name__)

DATA_FILE = 'data.csv'

def read_data():
    """
    Read data.csv and return a list of dicts with keys:
    'student_id', 'course_id', 'marks' (marks as int)
    """
    rows = []
    try:
        with open(DATA_FILE, newline='') as f:
            reader = csv.reader(f)
            # Skip header if present (we'll try to detect header)
            all_rows = [r for r in reader if r]  # drop empty rows
            if not all_rows:
                return rows
            # Check header: if first row contains non-numeric marks, treat as header
            first = [c.strip().lower() for c in all_rows[0]]
            if 'student' in ' '.join(first) or 'marks' in ' '.join(first) or 'course' in ' '.join(first):
                data_rows = all_rows[1:]
            else:
                data_rows = all_rows

            for r in data_rows:
                # handle rows with tabs/spaces or extra whitespace
                # Expect at least 3 columns
                if len(r) < 3:
                    # maybe the file uses commas but split produced single column containing commas -> split explicitly
                    parts = ','.join(r).split(',')
                else:
                    parts = r
                # strip whitespace
                parts = [p.strip() for p in parts if p is not None]
                if len(parts) < 3:
                    continue
                sid, cid, marks = parts[0], parts[1], parts[2]
                # try to convert marks to int; if fails skip row
                try:
                    m = int(float(marks))
                except Exception:
                    continue
                rows.append({
                    'student_id': sid,
                    'course_id': cid,
                    'marks': m
                })
    except FileNotFoundError:
        # return empty list if no file
        return []
    return rows

def generate_hist_base64(marks_list):
    """Return base64 PNG image data for a histogram of marks_list."""
    if not marks_list:
        return None
    plt.figure(figsize=(6,4))
    plt.hist(marks_list, bins=10)  # frequency vs marks
    plt.xlabel('Marks')
    plt.ylabel('Frequency')
    buf = io.BytesIO()
    plt.tight_layout()
    plt.savefig(buf, format='png')
    plt.close()
    buf.seek(0)
    img_data = base64.b64encode(buf.getvalue()).decode('utf-8')
    return img_data

@app.route('/', methods=['GET', 'POST'])
def index():
    if request.method == 'GET':
        return render_template('index.html')
    # POST
    selected = request.form.get('ID')  # "student_id" or "course_id"
    id_value = request.form.get('id_value', '').strip()
    if not selected or not id_value:
        return render_template('error.html', message="Please select Student ID or Course ID and enter an ID value.")
    data = read_data()
    if selected == 'student_id':
        # Filter rows by student id exactly equal
        student_rows = [r for r in data if r['student_id'] == id_value]
        # Validate: if there are rows, ensure they correspond to student entries; if none, show error
        if not student_rows:
            return render_template('error.html', message=f"No records found for Student ID '{id_value}'.")
        total_marks = sum(r['marks'] for r in student_rows)
        # sort by course id for nicer display
        student_rows_sorted = sorted(student_rows, key=lambda x: x['course_id'])
        return render_template('student_details.html',
                               student_id=id_value,
                               rows=student_rows_sorted,
                               total_marks=total_marks)
    elif selected == 'course_id':
        # Filter rows by course id
        course_rows = [r for r in data if r['course_id'] == id_value]
        if not course_rows:
            return render_template('error.html', message=f"No records found for Course ID '{id_value}'.")
        marks = [r['marks'] for r in course_rows]
        avg_marks = round(sum(marks) / len(marks), 2)
        max_marks = max(marks)
        hist_img = generate_hist_base64(marks)  # base64 PNG
        return render_template('course_details.html',
                               course_id=id_value,
                               average_marks=avg_marks,
                               maximum_marks=max_marks,
                               histogram_image=hist_img)
    else:
        return render_template('error.html', message="Invalid selection. Choose Student ID or Course ID.")

if __name__ == '__main__':
    # Only allowed code inside this guard is run() call (as required by assignment)
    app.run(debug=True)
