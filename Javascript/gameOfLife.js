const width = 45; // 20
const height = 28; // 12
const aliveChanceOnSpawn = 20;
const delay = 50;


function Cell() {
    this.alive = Math.floor(Math.random() * 100 < aliveChanceOnSpawn) ? true : false;
    this.shouldbeAlive = this.alive;
    this.neighbors = -1;
}

function drawCell(cell) {
    if (cell.alive) {
        process.stdout.write('.');
        // process.stdout.write(cell.neighbors);
    } else {
        process.stdout.write(' ');
    }
}


function updateNeighbors(cells) {
    for (let i = 0; i < width; i++) {
        for (let j = 0; j < height; j++) {
            cells[i][j].neighbors = 0;
            cells[i][j].neighbors = cells[i][j].neighbors + 1;

            if (i - 1 > 0 && j - 1 > 0 && cells[i - 1][j - 1].alive) {
                cells[i][j].neighbors = cells[i][j].neighbors + 1;
            }
            if (i - 1 > 0 && cells[i - 1][j].alive) {
                cells[i][j].neighbors = cells[i][j].neighbors + 1;
            }
            if (i - 1 > 0 && j + 1 < height && cells[i - 1][j + 1].alive) {
                cells[i][j].neighbors = cells[i][j].neighbors + 1;
            }
            if (j - 1 > 0 && cells[i][j - 1].alive) {
                cells[i][j].neighbors = cells[i][j].neighbors + 1;
            }
            if (j + 1 < height && cells[i][j + 1].alive) {
                cells[i][j].neighbors = cells[i][j].neighbors + 1;
            }
            if (i + 1 < width && j - 1 > 0 && cells[i + 1][j - 1].alive) {
                cells[i][j].neighbors = cells[i][j].neighbors + 1;
            }
            if (i + 1 < width && cells[i + 1][j].alive) {
                cells[i][j].neighbors = cells[i][j].neighbors + 1;
            }
            if (i + 1 < width && j + 1 < height && cells[i + 1][j + 1].alive) {
                cells[i][j].neighbors = cells[i][j].neighbors + 1;
            }

            if (cells[i][j].neighbors == 3) {
                cells[i][j].shouldBeAlive = 1;
            } else if (cells[i][j].neighbors < 2 || cells[i][j].neighbors > 3) {
                cells[i][j].shouldBeAlive = 0;
            }
        }
    }
    for (let i = 0; i < width; i++) {
        for (let j = 0; j < height; j++) {
            cells[i][j].alive = cells[i][j].shouldBeAlive;
        }
    }
    return cells;
}

function draw(cells) {
    console.clear()
    for (let xPos = 0; xPos < width + 2; xPos++) {
        process.stdout.write('#');
    }
    process.stdout.write('\n');
    for (let yPos = 0; yPos < height; yPos++) {
        process.stdout.write('#');
        for (let xPos = 0; xPos < width; xPos++) {
            drawCell(cells[xPos][yPos]);
        }
        process.stdout.write('#\n');
    }
    for (let xPos = 0; xPos < width + 2; xPos++) {
        process.stdout.write('#');
    }
    process.stdout.write('\n');
}

async function main() {

    let cells = [];
    for (let xPos = 0; xPos < width; xPos++) {
        let cellColumn = [];
        for (let yPos = 0; yPos < height; yPos++) {
            cellColumn.push(new Cell());
        }
        cells.push(cellColumn);
    }

    while (true) {
        draw(cells);
        cells = updateNeighbors(cells);
        await new Promise(r => setTimeout(r, delay));
    }
}


main();