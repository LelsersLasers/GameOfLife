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
        thread::sleep(time::Duration::from_millis(1000/FPS as u64));
    }
}
