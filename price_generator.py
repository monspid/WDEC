UC_W = [3.509787E-22, -4.012947E-16, 1.535677E-10, -1.49134E-5, 6.6003]
UC_Q = [8.03526E-8, -2.2274E-6, -6.982585E-4, 0.0736453, 0.682005]
def calculateUnitCost(wolumen, quality):
    ret = calculatePoly(UC_W, wolumen)
    ret = ret + calculatePoly(UC_Q, quality)
    return round(ret, 2)

def calculatePoly(p, value):
    ret = 0
    for i in range(len(p)):
        ret = ret + value ** (len(p) - i - 1) * p[i]
    return round(ret, 2)

with open("wyniki.txt", 'w', encoding="utf8") as f:
    for i in range(10000, 350000, 15500):
        for j in range(10, 85, 9):
            f.writelines("{} {} {}\n".format(j, i, calculateUnitCost(i, j)))