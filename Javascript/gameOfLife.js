const WIDTH = 45;
const HEIGHT = 27;
const aliveChanceOnSpawn = .2;
const FPS = 8.0;

class Cell {
    constructor() {
        this.alive = Math.random() < aliveChanceOnSpawn;
        this.neighbors = 0;
    }
    draw() { process.stdout.write((this.alive ? '@' : " ")); }
    sync() {
        if (this.neighbors == 3) this.alive = true;
        else if (this.neighbors < 2 || this.neighbors > 3) this.alive = false;
    }
}


function draw(cells) {
    console.clear()
    for (let i = 0; i < WIDTH + 2; i++) process.stdout.write('#');
    process.stdout.write('\n');
    for (let y = 0; y < HEIGHT; y++) {
        process.stdout.write('#');
        for (let x = 0; x < WIDTH; x++) cells[x][y].draw();
        process.stdout.write('#\n');
    }
    for (let i = 0; i < WIDTH + 2; i++) process.stdout.write('#');
}

function updateCells(cells) {
    for (let x = 0; x < WIDTH; x++) {
        for (let y = 0; y < HEIGHT; y++) {
            cells[x][y].neighbors = 0;
            let offsets = [[1, 0], [1, 1], [1, -1], [0, 1], [0, -1], [-1, 0], [-1, 1], [-1, -1]];
            for (let i in offsets) {
                try { cells[x][y].neighbors += cells[x + offsets[i][0]][y + offsets[i][1]].alive; }
                catch (e) {}
            }
        }
    }
    for (let x = 0; x < WIDTH; x++) {
        for (let y = 0; y < HEIGHT; y++) cells[x][y].sync();
    }
}

async function main() {
    let cells = [];
    for (let x = 0; x < WIDTH; x++) {
        let cellColumn = [];
        for (let y = 0; y < HEIGHT; y++) cellColumn.push(new Cell());
        cells.push(cellColumn);
    }

    while (true) {
        start = performance.now();
        
        draw(cells);
        updateCells(cells);

        sleepTime = 1000/FPS - (performance.now() - start);
        await new Promise(r => setTimeout(r, (sleepTime > 0 ? sleepTime : 0)));
    }
}


main();