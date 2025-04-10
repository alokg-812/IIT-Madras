import math

# Returns Euclidean distance between two points
def distance(p, q):
    return math.sqrt((p[0] - q[0])**2 + (p[1] - q[1])**2)

def minDistanceRec(Px, Py):
    s = len(Px)
    if s == 2:
        return distance(Px[0], Px[1])
    elif s == 3:
        return min(distance(Px[0], Px[1]), distance(Px[1], Px[2]), distance(Px[2], Px[0]))

    m = s // 2
    Qx = Px[:m]
    Rx = Px[m:]
    xR = Rx[0][0]

    Qy, Ry = [], []
    for p in Py:
        if p[0] < xR:
            Qy.append(p)
        else:
            Ry.append(p)

    delta = min(minDistanceRec(Qx, Qy), minDistanceRec(Rx, Ry))
    Sy = [p for p in Py if abs(p[0] - xR) <= delta]

    sizeS = len(Sy)
    if sizeS > 1:
        minS = distance(Sy[0], Sy[1])
        for i in range(sizeS):
            for j in range(i+1, min(i+7, sizeS)):  # 7 is sufficient for 2D closest pair
                minS = min(minS, distance(Sy[i], Sy[j]))
        return min(delta, minS)
    else:
        return delta

def minDistance(Points):
    Px = sorted(Points)
    Py = sorted(Points, key=lambda x: x[1])
    return round(minDistanceRec(Px, Py), 2)

# Example
pts = [(2, 15), (40, 5), (20, 1), (21, 14), (1, 4), (3, 11)]
result = minDistance(pts)
print(result)
