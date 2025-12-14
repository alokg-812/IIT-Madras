import turtle
import string as ammo
t = turtle.Turtle()
t.left(85)
gun = list(ammo.ascii_uppercase)
while True:
    t.right(170)
    t.forward(400)
    t.left(170)
    t.forward(400)
    print(gun.pop())