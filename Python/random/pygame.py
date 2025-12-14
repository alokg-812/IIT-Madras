import pygame

pygame.init()

# Screen setup
WIDTH, HEIGHT = 800, 600
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Moving Square")

clock = pygame.time.Clock()

# Square setup
square_pos = pygame.Rect(375, 275, 50, 50)
speed = 10

running = True
while running:
    # Event handling
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    # Key handling
    keys = pygame.key.get_pressed()
    if keys[pygame.K_UP]:
        square_pos.y -= speed
    if keys[pygame.K_DOWN]:
        square_pos.y += speed
    if keys[pygame.K_LEFT]:
        square_pos.x -= speed
    if keys[pygame.K_RIGHT]:
        square_pos.x += speed

    # Drawing
    screen.fill("black")
    pygame.draw.rect(screen, "red", square_pos)
    pygame.display.flip()

    clock.tick(60)

pygame.quit()
