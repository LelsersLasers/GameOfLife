import  random
import time
import os


width = 45 # 20
height = 28 # 12
aliveChanceOnSpawn = 20
delay = 0.05


class Cell():

    def __init__(self):
        self.neighbors = -1
        self.alive = True if random.randint(1, 100) <= aliveChanceOnSpawn else False
        self.shouldBeAlive = self.alive

    def drawCell(self):
        if self.alive:
            # print("%i" % self.neighbors, end='')
            print(".", end='')
        else:
            print(" ", end='')


class CellHolder(): # this all could gave been in main, not a class with 1 function

    def __init__(self):
        self.cells = []
        for xPos in range(width):
            cellColumn = []
            for yPos in range(height):
                cellColumn.append(Cell())
            self.cells.append(cellColumn)

    def updateNeighbors(self):
        for xPos in range(len(self.cells)):
            for yPos in range(len(self.cells[xPos])):
                self.cells[xPos][yPos].neighbors = 0
                
                if xPos - 1 > 0 and yPos - 1 > 0 and self.cells[xPos - 1][yPos - 1].alive:
                    self.cells[xPos][yPos].neighbors = self.cells[xPos][yPos].neighbors + 1
                if xPos - 1 > 0 and self.cells[xPos - 1][yPos].alive:
                    self.cells[xPos][yPos].neighbors = self.cells[xPos][yPos].neighbors + 1
                if xPos - 1 > 0 and yPos + 1 < height and self.cells[xPos- 1][yPos + 1].alive:
                    self.cells[xPos][yPos].neighbors = self.cells[xPos][yPos].neighbors + 1
                if yPos - 1 > 0 and self.cells[xPos][yPos - 1].alive:
                    self.cells[xPos][yPos].neighbors = self.cells[xPos][yPos].neighbors + 1;
                if yPos + 1 < height and self.cells[xPos][yPos + 1].alive:
                    self.cells[xPos][yPos].neighbors = self.cells[xPos][yPos].neighbors + 1
                if xPos + 1 < width and yPos - 1 > 0 and self.cells[xPos+ 1][yPos - 1].alive:
                    self.cells[xPos][yPos].neighbors = self.cells[xPos][yPos].neighbors + 1
                if xPos + 1 < width and self.cells[xPos + 1][yPos].alive:
                    self.cells[xPos][yPos].neighbors = self.cells[xPos][yPos].neighbors + 1
                if xPos + 1 < width and yPos + 1 < height and self.cells[xPos + 1][yPos + 1].alive:
                    self.cells[xPos][yPos].neighbors = self.cells[xPos][yPos].neighbors + 1

                if self.cells[xPos][yPos].neighbors == 3:
                    self.cells[xPos][yPos].shouldBeAlive = True
                elif self.cells[xPos][yPos].neighbors < 2 or self.cells[xPos][yPos].neighbors > 3:
                    self.cells[xPos][yPos].shouldBeAlive = False

        for cellColumn in self.cells:
            for cell in cellColumn:
                cell.alive = cell.shouldBeAlive


def draw(ch):
    os.system("cls")
    for i in range(width + 2):
        print("#", end='')
    print("")
    for yPos in range(height):
        print("#", end='')
        for xPos in range(width):
            ch.cells[xPos][yPos].drawCell()
        print("#")
    for i in range(width + 2):
        print("#", end='')
    print("")      


def main():

    ch = CellHolder()

    while True:
        draw(ch)
        ch.updateNeighbors()
        time.sleep(delay)



main()