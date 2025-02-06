from flask import Flask, render_template, request
import pandas as pd
import matplotlib.pyplot as plt
import os

app = Flask(__name__)

data_file = "data.csv"
data = pd.read_csv(data_file, skipinitialspace=True)

def generate_histogram(course_id):
    course_data = data[data["Course id"] == course_id]
    plt.figure(figsize=(6, 4))
    plt.hist(course_data["Marks"], bins=5, edgecolor='black')
    plt.xlabel("Marks")
    plt.ylabel("Number of Students")
    plt.title(f"Marks Distribution for Course {course_id}")
    hist_path = os.path.join("static", "histogram.png")
    plt.savefig(hist_path)
    plt.close()
    return hist_path

@app.route("/", methods=["GET", "POST"])
def index():
    if request.method == "POST":
        search_type = request.form.get("ID")
        search_value = request.form.get("id_value")
        
        if not search_type or not search_value.isdigit():
            return render_template("error.html", message="Invalid input! Please enter a valid Student ID or Course ID.")
        
        search_value = int(search_value)
        
        if search_type == "student_id":
            student_data = data[data["Student id"] == search_value]
            if student_data.empty:
                return render_template("error.html", message=f"No records found for Student ID {search_value}.")
            
            total_marks = student_data["Marks"].sum()
            return render_template("student_details.html", 
                        student_data=student_data.to_dict(orient="records"), 
                        total_marks=int(total_marks))  # Ensure total marks is an integer

        
        elif search_type == "course_id":
            course_data = data[data["Course id"] == search_value]
            if course_data.empty:
                return render_template("error.html", message=f"No records found for Course ID {search_value}.")
            
            avg_marks = round(course_data["Marks"].mean(), 2)
            max_marks = course_data["Marks"].max()
            hist_path = generate_histogram(search_value)
            return render_template("course_details.html", avg_marks=avg_marks, max_marks=max_marks, hist_path=hist_path)
    
    return render_template("index.html")

if __name__ == "__main__":
    app.run(debug=True)