with open('data.txt', 'r') as f:
    data = f.readlines()
    individuals = list()
    words = list()
    values = list()
    for line in data:
        word = line.split()
        if word[0] == "end":
            individuals.append(words)
            value = individuals[len(individuals) -1][0][1] - individuals[len(individuals) -1][0][0]
            values.append(value)
            words = list()
        else:
            word = list(map(float, word))
            words.append(word)


individuals = [x for (y,x) in sorted(zip(values,individuals), reverse = True)]
values = sorted(values, reverse = True)

while True:
    money = int(input('Ile masz hajsu w 1000? ')) * 1000
    for i in range(len(individuals)):
        if(individuals[i][0][0] <= money):
            print(("zysk: {}\n\nkapital: {}\nwolumen: {}\njakosc{}\ncena: {}\nTV: {}\nint: {}\nmag: {}\n").format(values[i], individuals[i][0], individuals[i][1], individuals[i][2], individuals[i][3], individuals[i][4], individuals[i][5], individuals[i][6]))
            break
    else:
        print("Nie ma przykladu w bazie")