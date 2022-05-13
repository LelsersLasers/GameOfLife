use rand::Rng;
use std::thread;
use std::time;
use std::io;

const WIDTH: usize = 45;
const HEIGHT: usize = 27;
const ALIVE_CHANCE_ON_SPAWN: f32 = 0.2;
const FPS: u32 = 8;

fn main() {
    println!("Hello, world!");
    let cells: [[i32; HEIGHT]; WIDTH] = [[0 as i32; HEIGHT]; WIDTH];

    loop {
        print!("\x1B[2J\x1B[1;1H");
        for _i in 0..WIDTH + 2 {
            print!("#");
        }
        println!("");
        for y in 0..HEIGHT {
            print!("#");
            for x in 0..WIDTH {
                print!("{}", cells[x][y]);
            }
            println!("#");
        }
        for _i in 0..WIDTH + 2 {
            print!("#");
        }
        println!("");

        thread::sleep(time::Duration::from_millis((1000/FPS).into()));
    }
}
