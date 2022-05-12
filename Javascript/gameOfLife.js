const width = 45;
const height = 27;
const aliveChanceOnSpawn = .2;
const delay = 50;

class Cell {
    constructor() {
        this.alive = Math.random() < aliveChanceOnSpawn;
        this.neighbors = 0;
    }
    sync() {
        if (this.neighbors == 3) this.alive = true;
        else if (this.neighbors < 2 || this.neighbors > 3) this.alive = false;
    }
    draw() {
        if (this.alive) process.stdout.write('.');
        else process.stdout.write(' ');
    }
}


function updateCells(cells) {
    for (let i = 0; i < width; i++) {
        for (let j = 0; j < height; j++) {
            cells[i][j].neighbors = 0;
            offsets = [[1, 0], [1, 1], [1, -1], [0, 1], [0, -1], [-1, 0], [-1, 1], [-1, -1]];
            for (let k in offsets) {
                try {
                    cells[i][j] += cells[i + offset[k][0]][j + offset[k][1]].alive;
                }
                catch (e) {}
            }
        }
    }
    for (let i = 0; i < width; i++) {
        for (let j = 0; j < height; j++) {
            cells[i][j].sync();
        }
    }
    return cells;
}

function draw(cells) {
    console.clear()
    for (let xPos = 0; xPos < width + 2; xPos++) process.stdout.write('#');
    process.stdout.write('\n');
    for (let yPos = 0; yPos < height; yPos++) {
        process.stdout.write('#');
        for (let xPos = 0; xPos < width; xPos++) cells[xPos][yPos].draw();
        process.stdout.write('#\n');
    }
    for (let xPos = 0; xPos < width + 2; xPos++) process.stdout.write('#');
    process.stdout.write('\n');
}

async function main() {
    let cells = [];
    for (let xPos = 0; xPos < width; xPos++) {
        let cellColumn = [];
        for (let yPos = 0; yPos < height; yPos++) cellColumn.push(new Cell());
        cells.push(cellColumn);
    }
    while (true) {
        draw(cells);
        updateCells(cells);
        await new Promise(r => setTimeout(r, delay));
    }
}


main();