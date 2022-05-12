import random
import time
import os


width = 45
height = 28
aliveChanceOnSpawn = 0.2
delay = 0.05


class Cell():
    def __init__(self):
        self.alive = random.random() < aliveChanceOnSpawn
        self.neighbors = 0
    def sync(self):
        if self.neighbors == 3: self.alive = True
        elif self.neighbors < 2 or self.neighbors > 3: self.alive = False
    def drawCell(self):
        if self.alive: print(".", end='')
        else: print(" ", end='')


def updateCells(cells):
    for i in range(len(cells)):
        for j in range(len(cells[i])):
            cells[i][j].neighbors = 0
            offsets = [[1, 0], [1, 1], [1, -1], [0, 1], [0, -1], [-1, 0], [-1, 1], [-1, -1]]
            for offset in offsets:
                try:
                    cells[i][j].neighbors += cells[i + offset[0]][j + offset[1]].alive
                except:
                    continue
    for cellColumn in cells:
        for cell in cellColumn:
            cell.sync()


def drawCells(cells):
    os.system("cls")
    print("#" * (width + 2))
    for yPos in range(height):
        print("#", end='')
        for xPos in range(width):
            cells[xPos][yPos].drawCell()
        print("#")
    print("#" * (width + 2))     


def main():
    cells = []
    for i in range(width):
        cellColumn = []
        for j in range(height):
            cellColumn.append(Cell())
        cells.append(cellColumn)
    while True:
        drawCells(cells)
        updateCells(cells)
        time.sleep(delay)



main()