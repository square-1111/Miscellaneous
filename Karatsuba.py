import math

def multiplication(x,y):
    # if x and y are single digit number (base case)
    if(x<10 and y<10):
        return x*y

    largest_power = max(len(str(x)), len(str(y)))
    power = math.ceil(largest_power/2)

    x_l = int(x/(10**power))
    x_r = int(x%(10**power))

    y_l = int(y/(10**power))
    y_r = int(y%(10**power))

    # z = z1*(10**(2*power)) + z2*(10**power) +z3
    # z1 = x_l*y_l
    # z2 = x_r*y_l + x_l*y_r
    # z3 = x_r*y_r
    # z2 = (x_l + x_r)*(y_l + y_r) -z1 -z2

    #Using divide and conquer to simpilfy equation

    z1 = multiplication(x_l,y_l)
    z3 = multiplication(x_r,y_r)
    z2 = multiplication(x_l + x_r , y_l + y_r) - z1 - z3

    z = z1*(10**(2*power)) + z2*(10**power) + z3

    return z

#Input First Number
number1 = input("Give first number ")

multiplier = 1
if(number1[0] == '-'):
    multiplier = -1
    number1 = number1[1:]

seperator1 = -1
for i in range(len(number1)):
    if(number1[i] == '.'):
        seperator1 = i
        number1 = number1[:i] + number1[i+1:]
        break


#Input Second Number
number2 = input("Give second number ")

if(number2[0] == '-'):
    multiplier *= -1
    number2 = number2[1:]

seperator2 = -1
for i in range(len(number2)):
    if(number2[i] == '.'):
        seperator2 = i
        number2 = number2[:i] + number2[i+1:]
        break

#Karatsuba
number1 = int(number1)
number2 = int(number2)
product = multiplication(number1, number2)

product = str(product)


#if there is decimal seperator
seperator1_from_left = seperator2_from_left = 0
if(seperator1 > -1):
    seperator1_from_left = len(str(number1)) - seperator1

if(seperator2 > -1):
    seperator2_from_left = len(str(number2)) - seperator2

seperator_from_left = seperator1_from_left + seperator2_from_left
seperator = len(product) - seperator_from_left
product = product[:seperator] + "." + product[seperator:]

#if one of the number is negative
if(multiplier == -1):
    product = "-" + product

print(product)
