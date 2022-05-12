#include <iostream>
#include <stdlib.h>
#include <time.h>

using namespace std;

#define WIDTH 45
#define HEIGHT 27
#define aliveChanceOnSpawn 0.2
#define FPS 8.0


class Cell {
private:
    bool alive;
    int neighbors;
public:
    Cell() {
        alive = (double)rand()/(double)RAND_MAX < aliveChanceOnSpawn; 
    }

    bool getAlive() { return alive; }
    void clearNeighbors() { neighbors = 0; }
    void addNeighbor(bool condition) { neighbors += condition ? 1 : 0; }
    void drawCell() { cout << (alive ? "@" : " "); }

    void sync() {
        if (neighbors == 3) alive = true;
        else if (neighbors < 2 || neighbors > 3) alive = false;
    }
};


void draw(Cell cells[WIDTH][HEIGHT]) {
    system("CLS");
    for (int i = 0; i < WIDTH + 2; i++) cout << "#";
    printf("\n");
    for (int y = 0; y < HEIGHT; y++) {
        cout << "#";
        for (int x = 0; x < WIDTH; x++) cells[x][y].drawCell();
        cout << "#" << endl;
    }
    for (int i = 0; i < WIDTH + 2; i++) cout << "#";;
}

void updateNeighbors(Cell cells[WIDTH][HEIGHT]) {
    for (int x = 0; x < WIDTH; x++) {
        for (int y = 0; y < HEIGHT; y++) {
            cells[x][y].clearNeighbors();
            int offsets[8][2] = {{1, 0}, {1, 1}, {1, -1}, {0, 1}, {0, -1}, {-1, 0}, {-1, 1}, {-1, -1}};
            for (int i = 0; i < 8; i++) {
                if (x + offsets[i][0] >= 0 && x + offsets[i][0] < WIDTH && y + offsets[i][1] >= 0 && y + offsets[i][1] < HEIGHT) {
                    cells[x][y].addNeighbor(cells[x + offsets[i][0]][y + offsets[i][1]].getAlive());
                }
            }
        }
    }
    for (int x = 0; x < WIDTH; x++) {
        for (int y = 0; y < HEIGHT; y++) cells[x][y].sync();
    }
}


int main() {
    srand(time(NULL));

    Cell cells[WIDTH][HEIGHT];
    for (int i = 0; i < WIDTH; i++) {
        for (int y = 0; y < HEIGHT; y++) cells[i][y] = Cell();
    }

    while (true) {
        clock_t start = clock();
        
        draw(cells);
        updateNeighbors(cells);

        double sleepTime = 1000.0/FPS - ((clock() - start)/(double)CLOCKS_PER_SEC * 1000);
        _sleep((sleepTime > 0) ? sleepTime : 0);
    }

}