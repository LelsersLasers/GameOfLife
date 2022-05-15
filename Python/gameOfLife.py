import random
import time
import os


WIDTH = 45
HEIGHT = 27
aliveChanceOnSpawn = 0.2
FPS = 8.0


class Cell():
    def __init__(self):
        self.alive = random.random() < aliveChanceOnSpawn
        self.neighbors = 0
    def drawCell(self): print(("@" if self.alive else " "), end='')
    def sync(self):
        if self.neighbors == 3: self.alive = True
        elif self.neighbors < 2 or self.neighbors > 3: self.alive = False


def updateCells(cells):
    for x in range(len(cells)):
        for y in range(len(cells[x])):
            cells[x][y].neighbors = 0
            offsets = [[1, 0], [1, 1], [1, -1], [0, 1], [0, -1], [-1, 0], [-1, 1], [-1, -1]]
            for offset in offsets:
                try: cells[x][y].neighbors += cells[x + offset[0]][y + offset[1]].alive
                except: continue
    for x in range(len(cells)):
        for y in range(len(cells[x])):
            cells[x][y].sync()

def drawCells(cells):
    os.system("cls") # might need to be "clear" on UNIX/non-WIndows systems
    print("#" * (WIDTH + 2))
    for y in range(HEIGHT):
        print("#", end='')
        for x in range(WIDTH): cells[x][y].drawCell()
        print("#")
    print("#" * (WIDTH + 2))     


def main():
    cells = []
    for x in range(WIDTH):
        cellColumn = []
        for y in range(HEIGHT):
            cellColumn.append(Cell())
        cells.append(cellColumn)

    while True:
        start = time.time()

        drawCells(cells)
        updateCells(cells)
        
        sleepTime = 1/FPS - (time.time() - start)
        time.sleep(sleepTime if sleepTime > 0 else 0)


main()