class Point:
    def __init__(self, a=0,b=0):
        self.x=a
        self.y=b
    
    def translate(self, dx, dy):
        self.x+=dx
        self.y+=dy 
    
    def origin_distance(self):
        import math
        d = math.sqrt((self.x*self.x) + (self.y*self.y))
        return d
    
p1 = Point(4,3)
print(p1.origin_distance())