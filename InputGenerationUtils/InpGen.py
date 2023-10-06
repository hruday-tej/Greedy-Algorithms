import os
import random
inp = ""
n = random.randint(10000, 20000)
k = 4
inp = str(n) + " " + str(k) + "\n"
for i in range(0,n):
    total = random.randint(1000, 9000)
    perc = random.randint(1, 9)/100
    working = total * perc
    inp = inp + str(round(working)) + " " + str(total) + "\n"

with open(os.path.join('Greedy-Algorithms/Inputs',"Input.txt"), "w") as text_file:
    text_file.write(inp)