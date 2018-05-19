import os
import sys

filename = sys.argv[1]

if os.path.exists(filename) == False:
    print("No input file")
    exit(0)
inFile = open(filename)

if os.path.getsize(filename) <= 0:
    print("No protein sequence")
    exit(0)
    
str = inFile.readline()
if (str.startswith('>') == False):
    print("No correct format")
    exit(0)

def fasta_validate(inFile):
    count = 1
    for line in inFile:
        line = line.rstrip()
        if line.startswith(">"):
            count += 1
    if count < 2:
        print "Need one more sequence"
        exit(1)
    elif count >= 2:
        inFile.seek(0)
    return

def fasta_read(inFile):
    inFile.seek(0)
    sequences = {}
    key = []
    for line in inFile:
        line = line.rstrip('\n').rstrip('\r')
        if line.startswith('>'):
            index = line[1:].rstrip('\n').rstrip('\r')
            key.append(index)
            sequences[index] = ''
        else:
            sequences[index] += line.rstrip('\n').rstrip('\r')
    return key, sequences

fasta_read(inFile)
key, seq = fasta_read(inFile)

sequences = {}
myReturn = False

for i in range(0, len(key)):
    sequences[i] = seq[key[i]].upper()


def find_score(rowLetter, colLetter):
    array2d = [ [9,-1,-1,-3,0,-3, -3,-3,-4,-3,-3,-3,-3,-1,-1,-1,-1,-2,-2,-2,-5],
           [-1,4,1,-1,1,0,1,0,0,0,-1,-1,0,-1,-2,-2,-2,-2,-2,-3,-5],
           [-1,1,4,-1,0,-2,0,-1,-1,-1,-2,-1,-1,-1,-1,-1, 0,-2,-2,-2,-5],
           [-3,-1,-1,7,-1,-2,-2,-1,-1,-1,-2,-2,-1,-2,-3,-3,-2,-4,-3,-4,-5],
           [0,1,0,-1,4,0,-2,-2,-1,-1,-2,-1,-1,-1,-1,-1,0,-2,-2,-3,-5],
           [-3, 0, -2, -2, 0, 6, 0, -1, -2, -2, -2, -2, -2, -3, -4, -4, -3, -3, -3, -2, -5],
           [-3, 1, 0, -2, -2, 0, 6, 1, 0, 0, 1, 0, 0, -2, -3, -3, -3, -3, -2, -4, -5],
           [-3, 0, -1, -1, -2, -1, 1, 6, 2, 0, -1, -2, -1, -3, -3, -4, -3, -3, -3, -4, -5],
           [-4, 0, -1, -1, -1, -2, 0, 2, 5, 2, 0, 0, 1, -2, -3, -3, -2, -3, -2, -3, -5],
           [-3, 0, -1, -1, -1, -2, 0, 0, 2, 5, 0, 1, 1, 0, -3, -2, -2, -3, -1, -2, -5],
           [-3, -1, -2, -2, -2, -2, 1, -1, 0, 0, 8, 0, -1, -2, -3, -3, -3, -1, 2, -2, -5],
           [-3, -1, -1, -2, -1, -2, 0, -2, 0, 1, 0, 5, 2, -1, -3, -2, -3, -3, -2, -3, -5],
           [-3, 0, -1, -1, -1, -2, 0, -1, 1, 1, -1, 2, 5, -1, -3, -2, -2, -3, -2, -3, -5],
           [-1, -1, -1, -2, -1, -3, -2, -3, -2, 0, -2, -1, -1, 5, 1, 2, 1, 0, -1, -1, -5],
           [-1, -2, -1, -3, -1, -4, -3, -3, -3, -3, -3, -3, -3, 1, 4, 2, 3, 0, -1, -3, -5],
           [-1, -2, -1, -3, -1, -4, -3, -4, -3, -2, -3, -2, -2, 2, 2, 4, 1, 0, -1, -2, -5],
           [-1, -2, 0, -2, 0, -3, -3, -3, -2, -2, -3, -3, -2, 1, 3, 1, 4, -1, -1, -3, -5],
           [-2, -2, -2, -4, -2, -3, -3, -3, -3, -3, -1, -3, -3, 0, 0, 0, -1, 6, 3, 1, -5],
           [-2, -2, -2, -3, -2, -3, -2, -3, -2, -1, 2, -2, -2, -1, -1, -1, -1, 3, 7, 2, -5],
           [-2, -3, -2, -4, -3, -2, -4, -4, -3, -2, -2, -3, -3, -1, -3, -2, -3, 1, 2, 11, -5],
           [-5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, 0]]
    
    
    
    
    
    arrayRow = ['C', 'S', 'T', 'P', 'A', 'G', 'N', 'D', 'E', 'Q', 'H', 'R', 'K', 'M', 'I', 'L', 'V', 'F', 'Y', 'W'];
    arrayCol = ['C', 'S', 'T', 'P', 'A', 'G', 'N', 'D', 'E', 'Q', 'H', 'R', 'K', 'M', 'I', 'L', 'V', 'F', 'Y', 'W'];
        
    countR = 0
    countC = 0
    for i in arrayRow:
        if i == rowLetter:
            break
        else:
            countR += 1
    for j in arrayCol:
        if j == colLetter:
            break
        else:
            countC += 1
    score = array2d[countR][countC]
    return score

def global_alignment(seq1, seq2, myReturn):
    matrix = [[ 0 for x in range(len(seq2)+1)] for y in range (len(seq1)+1)]
    row = (len(seq1)+1)
    col = (len(seq2)+1)

    count = -5
    for i in range(1, row):
        matrix[i][0] = count
        count -= 5
        
    count = -5
    for j in range(1, col):
        matrix[0][j] = count
        count -= 5



    for i in range(1, row):
        for j in range(1, col):
            score = find_score(seq1[i-1], seq2[j-1])
            matrix[i][j] = max(matrix[i][j-1] - 5, matrix[i-1][j] - 5,
                                matrix[i-1][j-1] + score)


    if myReturn == False:
        return matrix[i][j]
    elif myReturn == True:
        firstSeq1 = ""
        secondSeq2 = ""
        while i > 0 or j > 0:
            val = matrix[i][j]
            score = find_score(seq1[i-1], seq2[j-1])
            if  j > 0 and i > 0 and matrix[i-1][j-1] + score == val:
                firstSeq1 = seq1[i-1] + firstSeq1
                secondSeq2 = seq2[j-1] + secondSeq2
                j -=1
                i -=1
            elif i > 0 and matrix[i-1][j] - 5 == val:
                firstSeq1 = seq1[i-1] + firstSeq1
                secondSeq2 = '-' + secondSeq2
                i-=1
            else:
                firstSeq1 = '-' + firstSeq1
                secondSeq2 = seq2[j-1] + secondSeq2
                j-=1
    return firstSeq1, secondSeq2


def center_seq(sequences, myReturn):
    scores = [[ 0 for x in range(len(sequences))] for y in range (len(sequences))]
    highest = []
    score = 0
    max_score = 0
    best_score = 0
    seq_index = 0

    for i in range(0, len(sequences)):
        for j in range(0, len(sequences)):
            seq1 = sequences[j]
            seq2 = sequences[i]
            if i != j:
               scores[i][j] = global_alignment(seq1,seq2, myReturn)              
        score = 0
        
    for i in range(0, len(sequences)):
        for j in range(0, len(sequences)):
            max_score += scores[i][j]
        highest.append(max_score)
        max_score = 0

    for i in range(0, len(highest)):
        if highest[i] > best_score:
            best_score = highest[i]
            seq_index = i;

    return sequences[seq_index]

myReturn = False
centerSeq = center_seq(sequences, myReturn)

def checkEqual3(lst):
   return lst[1:] == lst[:-1]

def align_seq(centerSeq, sequences, myReturn):
    MSAnum = 0
    myCenterSeq = ""
    index = 0
    for i in range(0, len(sequences)):
        if sequences[i] == centerSeq:
            index = i
            myCenterSeq = sequences[i]



    del sequences[index]
    newCenter = []
    newSeqs = []
    MSA = []
    for i in range((len(sequences)+1)):
       if i != index:
            c, s = global_alignment(myCenterSeq, sequences.get(i), myReturn)
            newCenter.append(c.rstrip(" "))
            newSeqs.append(s.rstrip(" "))


    MSA.append(newCenter[0])
    MSA.append(newSeqs[0])
    MSAnum += 2
    newSeqs.remove(newSeqs[0])

    if len(sequences) <= 2:
        f = open("MSAout.txt", "w")
        c = len(MSA[0])
        r = MSAnum 
        i = 0
        j = 0
        x = 0
        temp3 = []
        star = ''

        while i < c:
            while j < r:
                temp3 += MSA[j][i]
                j += 1
            if checkEqual3(temp3) == True:
                star += '*'
            else:
                star += " "
            temp3 = []
            i += 1
            j = 0
        
        star = star.lstrip(':')
        MSA.append(star)

        v = 0
        for i in MSA:
            print >> f, MSA[v]
            v += 1
        return

    
    centerMsa = list(newCenter[0])
    centerPsa = list(newCenter[1])
    newAlign = []

    #print "lengthP", lengthP
    #print "lengthM", lengthM

    j = 0
    counter = 0
    for f in range(len(newSeqs)):
        lengthP = len(centerPsa)
        lengthM = len(centerMsa)
        alignSeq = list(newSeqs[f])
        if f == 0:
            length1 = len(centerMsa)
        else:
            length1 = len(centerMsa)
 
        j += 1
        centerPsa = list(newCenter[j])

        i = 0
        while i < (length1):
            if centerPsa[i] != '-' and centerMsa[i] != '-':
                if i < len(alignSeq):
                    newAlign += alignSeq[i]
            elif centerPsa[i] != '-' and centerMsa[i] == '-':
                newAlign.insert(i, '-')
                alignSeq.insert(i, '-')
                centerPsa.insert(i, '-')
            elif centerPsa[i] == '-' and centerMsa[i] != '-':
                newAlign += alignSeq[i]
                centerMsa.insert(i, '-')
                length1 += 1
                m = 0
                if len(MSA) > 0:
                    while m < len(MSA):
                        temp = list(MSA[m])
                        temp.insert(i, '-')
                        temp2 = ""
                        e = 0
                        while e < len(temp):
                            temp2 += temp[e]
                            e += 1
                        MSA[m] = temp2
                        m += 1
            elif centerPsa[i] == '-' and centerMsa[i] == '-':
                if i < len(alignSeq):                   
                    newAlign += alignSeq[i]
                    
            length = len(centerMsa)
            length1 = len(centerMsa)
            i += 1


        if len(newAlign) < len(alignSeq) and f == len(newSeqs) - 1:
                dif = len(alignSeq) - len(newAlign)
                ind = len(alignSeq) - dif
                while ind < len(alignSeq):
                    newAlign += alignSeq[ind]
                    ind += 1
                    
        MSA.append(''.join(newAlign))
        MSAnum += 1
        newAlign = []
        
        length1 = 0
        lengthP = len(centerPsa)
        lengthM = len(centerMsa)
        
    if len(newAlign) < len(alignSeq):
        newAlign += alignSeq[len(alignSeq) - 1]
    MSA.remove(MSA[0])
    MSA.insert(0, ''.join(centerMsa))
    
    f = open("output_Fitzgerald.txt", "w")
    v = 0
    charTemp = []
    myTemp = []
    for i in MSA:
        charTemp += MSA[v].rstrip(" ")
        v += 1
        charTemp += " "
    v = 0
    while charTemp[v] != ' ':
        myTemp += charTemp[v]
        v += 1

    c = len(MSA[0])
    r = MSAnum 
    i = 0
    j = 0
    x = 0
    temp3 = []
    star = ''

    while i < c:
        while j < r:
            temp3 += MSA[j][i]
            j += 1
        if checkEqual3(temp3) == True:
            star += '*'
        else:
            star += " "
        temp3 = []
        i += 1
        j = 0
    
    star = star.lstrip(':')
    MSA.append(star)

    v = 0
    for i in MSA:
        print >> f, MSA[v]
        v += 1
            
        
    return sequences, myCenterSeq

myReturn = True
align_seq(centerSeq, sequences, myReturn)

#myReturn = True
#oneSeq, twoSeq = global_alignment(sequences[1], sequences[2], myReturn)
#print oneSeq
#print twoSeq







