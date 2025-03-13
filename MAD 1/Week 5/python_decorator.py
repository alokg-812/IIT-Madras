# A simple decorator function
def decorator(func):
  
    def wrapper():
        print("Before calling the function.")
        func()
        print("After calling the function.")
    return wrapper




# Applying the decorator to a function
@decorator

def greet():
    print("Hello, World!")

greet()


output:
# > python -u "c:\Users\alokg\Desktop\madras\MAD1\week5\python_decorator.py"
# Before calling the function.
# Hello, World!
# After calling the function.
