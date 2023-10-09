import os
import random
inp = ""
n = random.randint(20, 30)
k = 60
inp = str(n) + " " + str(k) + "\n"
for i in range(0,n):
    total = random.randint(100, 200)
    # working = random.randint(0,total)
    perc = random.randint(40, 90)/100
    working = total * perc
    inp = inp + str(round(working)) + " " + str(total) + "\n"

with open(os.path.join('Inputs',"Input.txt"), "w") as text_file:
    text_file.write(inp)