import  random
import time
import os


width = 45 # 20
height = 27 # 12
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
                offsets = [
                    [1, 0],
                    [1, 1],
                    [1, -1],
                    [0, 1],
                    [0, -1],
                    [-1, 0],
                    [-1, 1],
                    [-1, -1]
                ]

                for offset in offsets:
                    try:
                        # if self.cells[xPos + offset[0]][yPos + offset[1]].alive:
                        #     self.cells[xPos][yPos].neighbors += 1
                        self.cells[xPos][yPos].neighbors += self.cells[xPos + offset[0]][yPos + offset[1]].alive
                    except:
                        continue

                if self.cells[xPos][yPos].neighbors == 3:
                    self.cells[xPos][yPos].shouldBeAlive = True
                elif self.cells[xPos][yPos].neighbors < 2 or self.cells[xPos][yPos].neighbors > 3:
                    self.cells[xPos][yPos].shouldBeAlive = False

        for cellColumn in self.cells:
            for cell in cellColumn:
                cell.alive = cell.shouldBeAlive


def draw(cellHolder):
    os.system("cls")
    print("#" * (width + 2))
    for yPos in range(height):
        print("#", end='')
        for xPos in range(width):
            cellHolder.cells[xPos][yPos].drawCell()
        print("#")
    print("#" * (width + 2))     


def main():

    cellHolder = CellHolder()

    while True:
        draw(cellHolder)
        cellHolder.updateNeighbors()
        time.sleep(delay)



main()