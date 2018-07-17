# Relation to generate Pseudo-Random Number
#       x_n = (a + b*x_(n-1))mod m

a = 5915587277
b = 4093082899
primeMod = 9576890767
iterations = 14


def RandomNumber(iterations):
    seed = int(input('Give seed'))
    temp = seed
    for i in range(iterations):
        x = ( a*temp + b ) % primeMod
        temp = x

    return x

if __name__ == "__main__":
    print(RandomNumber(14))
