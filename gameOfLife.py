"""
Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:
    Any live cell with fewer than two live neighbours dies, as if by underpopulation.
    Any live cell with two or three live neighbours lives on to the next generation.
    Any live cell with more than three live neighbours dies, as if by overpopulation.
    Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
These rules, which compare the behavior of the automaton to real life, can be condensed into the following:
    Any live cell with two or three live neighbours survives.
    Any dead cell with three live neighbours becomes a live cell.
    All other live cells die in the next generation. Similarly, all other dead cells stay dead.
"""


import  random
import time
import os


width = 20 # 20
height = 12 # 12
aliveChanceOnSpawn = 20
delay = 0.05


class Posistion():

    def __init__(self, x, y):
        self.x = x
        self.y = y

    def compare(self, otherPos):
        if otherPos.x == self.x and otherPos.y == self.y:
            return True
        return False


class Cell():

    def __init__(self, position):
        self.position = position
        self.neighbors = -1

        self.alive = False
        chanceValue = random.randint(1, 100)
        if chanceValue <= aliveChanceOnSpawn:
            self.alive = True

        self.shouldBeAlive = self.alive

    def drawCell(self):
        if self.alive:
            # print("%i" % self.neighbors, end='')
            print(".", end='')
        else:
            print(" ", end='')

    def getPosition(self):
        return self.position


class CellHolder():

    def __init__(self):
        self.cells = []

        for yPos in range(height):
            for xPos in range(width):
                tempPos = Posistion(xPos, yPos)
                self.cells.append(Cell(tempPos))

    def findCell(self, position):
        for cell in self.cells:
            if cell.getPosition().compare(position):
                return cell
        print("failed to find cell")
        return -1

    def checkDrawCell(self, position):
        cell = self.findCell(position)
        cell.drawCell()

    def updateNeighbors(self):
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
        for cell in self.cells:
            cell.neighbors = 0
            for offset in offsets:
                newPos = Posistion(cell.getPosition().x + offset[0], cell.getPosition().y + offset[1])
                if newPos.x < 0 or newPos.x >= width or newPos.y < 0 or newPos.y >= height:
                    continue
                if self.findCell(newPos).alive:
                    cell.neighbors += 1

            if cell.neighbors == 3:
                cell.shouldBeAlive = True
            elif cell.neighbors < 2 or cell.neighbors > 3:
                cell.shouldBeAlive = False

        for cell in self.cells:
            cell.alive = cell.shouldBeAlive


def draw(ch):
    os.system("cls")
    for i in range(width + 2):
        print("#", end='')
    print("")
    for yPos in range(height):
        print("#", end='')
        for xPos in range(width):
            drawPos = Posistion(xPos, yPos)
            ch.checkDrawCell(drawPos)
        print("#")
    for i in range(width + 2):
        print("#", end='')
    print("")      


def main():

    ch = CellHolder()

    while True:
        draw(ch)
        ch.updateNeighbors()
        time.sleep(1)



main()