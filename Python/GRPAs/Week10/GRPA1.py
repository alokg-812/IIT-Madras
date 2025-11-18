class Calculator:
    def __init__(self, a, b):
        self.a = a
        self.b = b
        
    def add(self):
        return self.a+ self.b
        
    def multiply(self):
        return self.a * self.b
    
    def subtract(self):
        return self.a - self.b
    
    def quotient(self):
        if self.a==0:
            raise ZeroDivisionError("Cannot divide by zero")
        return self.a // self.b
        
    def remainder(self):
        if self.a==0:
            raise ZeroDivisionError("Cannot divide by zero")
        return self.a % self.b
