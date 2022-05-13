use rand::Rng;
use std::thread;
use std::time;

const WIDTH: usize = 45;
const HEIGHT: usize = 27;
const ALIVE_CHANCE_ON_SPAWN: f32 = 0.2;
const FPS: u8 = 8;

#[derive(Copy, Clone)]
struct Cell {
    alive: bool,
    neighbors: u8,
}

impl Cell {
    pub fn new() -> Self {
        Self {
            alive: rand::thread_rng().gen::<f64>() < ALIVE_CHANCE_ON_SPAWN.into(),
            neighbors: 0
        }
    }
    fn draw(&self) { print!("{}", if self.alive { "@"} else {" "}); }
    fn sync(&mut self) {
        if self.neighbors == 3 { self.alive = true; }
        else if self.neighbors < 2 || self.neighbors > 3 { self.alive = false; }
    }
}


fn update_cells(mut cells: [[Cell; HEIGHT]; WIDTH]) -> [[Cell; HEIGHT]; WIDTH] {
    for x in 0..WIDTH {
        for y in 0..HEIGHT {
            cells[x][y].neighbors = 0;
            let offsets: [[i8; 2]; 8] = [[1, 0], [1, 1], [1, -1], [0, 1], [0, -1], [-1, 0], [-1, 1], [-1, -1]];
            for offset in offsets {
                if x as i8 + offset[0] >= 0 && x as i8 + offset[0] < WIDTH.try_into().unwrap() && y as i8 + offset[1] >= 0 && y as i8 + offset[1] < HEIGHT.try_into().unwrap() && cells[(x as i8 + offset[0]) as usize][(y as i8 + offset[1]) as usize].alive {
                    cells[x][y].neighbors += 1;
                }
            }
        }
    }
    for x in 0..WIDTH {
        for y in 0..HEIGHT {
            cells[x][y].sync();
        }
    }
    cells
}

fn draw_cells(cells: [[Cell; HEIGHT]; WIDTH]) -> [[Cell; HEIGHT]; WIDTH] {
    print!("\x1B[2J\x1B[1;1H");
    for _i in 0..WIDTH + 2 { print!("#"); }
    println!("");
    for y in 0..HEIGHT {
        print!("#");
        for x in 0..WIDTH { cells[x][y].draw(); }
        println!("#");
    }
    for _i in 0..WIDTH + 2 { print!("#"); }
    println!("");
    cells
}

fn main() {
    println!("Hello, world!");
    let mut cells: [[Cell; HEIGHT]; WIDTH] = [[Cell::new(); HEIGHT]; WIDTH];
    for x in 0..WIDTH {
        for y in 0..HEIGHT { cells[x][y] = Cell::new(); }
    }

    loop {
        cells = draw_cells(cells);
        cells = update_cells(cells);
        thread::sleep(time::Duration::from_millis(1000/FPS as u64));
    }
}
