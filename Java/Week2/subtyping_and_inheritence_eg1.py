class Rectangle:
    def __init__(self,w=0,h=0):
        self.width = w
        self.height = h
    
    def area(self):
        return self.width * self.height
    
    def perimeter(self):
        return 2*(self.width + self.height)
    
class Square(Rectangle):
    def __init__(self, s=0):
        self.width = s
        self.height = s

rec1 = Rectangle(4,5)
print(rec1.area())
print(rec1.perimeter())

square1 = Square(5)
print(square1.area())
print(square1.perimeter())