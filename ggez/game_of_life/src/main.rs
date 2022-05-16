use rand::Rng;
use std::time::Duration;
use ggez::{
	Context, GameResult, graphics, event, mint, timer, ContextBuilder, conf,
	input::{
		keyboard::{self, KeyCode},
		mouse::{self, MouseButton}
	}
};


const WIDTH: f32 = 45.0;
const HEIGHT: f32 = 28.0;
const SIZE: f32 = 16.0;
const SPACER: f32 = 4.0;
const ALIVE_CHANCE_ON_SPAWN: f32 = 0.2;
const INITIAL_FPS: u32 = 8;


#[derive(Copy, Clone)]
struct Cell {
	alive: bool,
	neighbors: u8,
	status: usize
}
impl Cell {
	pub fn new() -> Self {
		Self {
			alive: rand::thread_rng().gen::<f64>() < ALIVE_CHANCE_ON_SPAWN.into(),
            neighbors: 0,
			status: 0
		}
	}
    fn sync(&mut self) {
		let alive_before = self.alive;
        if self.neighbors == 3 { self.alive = true; }
        else if self.neighbors < 2 || self.neighbors > 3 { self.alive = false; }

		if !alive_before && !self.alive { self.status = 0; } // still dead
        else if !alive_before { self.status = 1; } // newly alive
		else if !self.alive { self.status = 2; } // newly dead
		else { self.status = 3; }// still alive
    }
	fn draw(&self, draw_mode: u8) -> graphics::Color {
		let color: graphics::Color;
		if draw_mode == 0 { // gold if alive else black
			if self.alive { color = graphics::Color::new(0.855, 0.65, 0.13, 1.0); } // "GOLDENROD"
			else { color = graphics::Color::BLACK; }
		}
		else if draw_mode == 1 { // black if still dead, green if newly alive, red if newly dead, blue if still alive
			color = [graphics::Color::BLACK, graphics::Color::GREEN, graphics::Color::RED, graphics::Color::BLUE][self.status];
		}
		else { // yellow if alive else neighbors as greyscale
			if self.alive { color = graphics::Color::YELLOW; }
			else {
				let brightness = self.neighbors as f32/8.0;
				color = graphics::Color::new(brightness, brightness, brightness, 1.0);
			}
		}

		// if color != graphics::Color::BLACK { // no point in drawing if it is the same color as the background
		// 	let rect_mesh = graphics::Mesh::new_rectangle(context, graphics::DrawMode::fill(), rect, color)?;
		// 	graphics::draw(context, &rect_mesh, graphics::DrawParam::default())?;

		// 	if draw_mode == 3 && self.neighbors > 0 { // draw neighbors as text
		// 		let text = graphics::Text::new(self.neighbors.to_string());
		// 		graphics::draw(context, &text, graphics::DrawParam::default().dest(rect.point()))?;
		// 	}
		// }
		color
	}
}


struct ToggleKey {
	was_down: bool
}
impl ToggleKey {
	pub fn new() -> Self {
		Self {
			was_down: false
		}
	}
	fn down(& mut self, state: bool) -> bool {
		if !self.was_down && state {
			self.was_down = true;
			return true
		}
		else if !state { self.was_down = false; }
		false
	}
}


struct Controller {
	cells: [[Cell; HEIGHT as usize]; WIDTH as usize],
	batch: graphics::spritebatch::SpriteBatch,
	frame: u128,
	fps: u32,
	paused: bool,
	draw_mode: u8,
	mouse_toggle: ToggleKey,
	space_toggle: ToggleKey,
	up_toggle: ToggleKey,
	down_toggle: ToggleKey
}
impl Controller {
	pub fn new(white_batch: graphics::spritebatch::SpriteBatch) -> Self {
		let mut temp_cells = [[Cell::new(); HEIGHT as usize]; WIDTH as usize];
		for x in 0..WIDTH as usize {
			for y in 0..HEIGHT as usize { temp_cells[x][y] = Cell::new(); }
		}
		Self {
			cells: temp_cells,
			batch: white_batch,
			frame: 0,
			fps: INITIAL_FPS,
			paused: false,
			draw_mode: 0,
			mouse_toggle: ToggleKey::new(),
			space_toggle: ToggleKey::new(),
			up_toggle: ToggleKey::new(),
			down_toggle: ToggleKey::new()
		}
	}
	fn update_cells(&mut self) {
		for x in 0..WIDTH as usize {
			for y in 0..HEIGHT as usize {
				self.cells[x][y].neighbors = 0;
				let offsets: [[i8; 2]; 8] = [[1, 0], [1, 1], [1, -1], [0, 1], [0, -1], [-1, 0], [-1, 1], [-1, -1]];
				for offset in offsets {
					if x as i8 + offset[0] >= 0 && x as i8 + offset[0] < WIDTH as i8 && y as i8 + offset[1] >= 0 && y as i8 + offset[1] < HEIGHT as i8 && self.cells[(x as i8 + offset[0]) as usize][(y as i8 + offset[1]) as usize].alive {
						self.cells[x][y].neighbors += 1;
					}
				}
			}
		}
		for x in 0..WIDTH as usize {
			for y in 0..HEIGHT as usize { self.cells[x][y].sync(); }
		}
	}
	fn draw_cells(&mut self) {
		for x in 0..WIDTH as usize {
			for y in 0..HEIGHT as usize {
				let color = self.cells[x][y].draw(self.draw_mode);
				let params = graphics::DrawParam::default();
				params.dest(mint::Point2 {
					x: x as f32 * (SIZE + SPACER) + SPACER,
					y: y as f32 * (SIZE + SPACER) + SPACER
				});
				params.color(color);
				self.batch.add(params);
			}
		}
	}
	fn handle_input(&mut self, context: &Context) {
		if keyboard::is_key_pressed(context, KeyCode::R) { self.randomize_cells(); }
		if self.space_toggle.down(keyboard::is_key_pressed(context, KeyCode::Space)) { self.draw_mode = (self.draw_mode + 1) % 4; }
		if self.up_toggle.down(keyboard::is_key_pressed(context, KeyCode::Up)) { self.fps += 1; }
		if self.down_toggle.down(keyboard::is_key_pressed(context, KeyCode::Down)) {
			if self.fps > 1 { self.fps -= 1; }
		}
		if self.space_toggle.down(keyboard::is_key_pressed(context, KeyCode::Space)) { self.paused = !self.paused; }
		if self.mouse_toggle.down(mouse::button_pressed(context, MouseButton::Left)) { self.paused = !self.paused; }
	}
	fn randomize_cells(&mut self) {
		for x in 0..WIDTH as usize {
			for y in 0..HEIGHT as usize { self.cells[x][y] = Cell::new(); }
		}
		self.update_cells();
	}
}
impl event::EventHandler for Controller {
	fn update(&mut self, context: &mut Context) -> GameResult {
		self.frame += Duration::as_nanos(&timer::delta(context));
		self.handle_input(context);

		if !self.paused && self.frame >= 1_000_000_000/self.fps as u128 {
			self.update_cells();
			while self.frame >= 1_000_000_000/self.fps as u128 { self.frame -= 1_000_000_000/self.fps as u128; }
		}
		Ok(())
	}
	fn draw(&mut self, context: &mut Context) -> GameResult {
		graphics::clear(context, graphics::Color::BLACK);

		self.batch.clear();
		self.draw_cells();
		graphics::draw(context, &self.batch, graphics::DrawParam::default())?;
		
		graphics::present(context)?;
		
		Ok(())
	}
}


fn main() -> GameResult {
	let cb = ContextBuilder::new("game_of_life", "Lelsers Lasers")
		.window_mode(conf::WindowMode::default().dimensions(WIDTH * (SIZE + SPACER) + SPACER, HEIGHT * (SIZE + SPACER) + SPACER));
	let (mut context, event_loop) = cb.build()?;
	graphics::set_window_title(&context, "Game Of Life");

	let controller = Controller::new(graphics::spritebatch::SpriteBatch::new(graphics::Image::solid(&mut context, SIZE as u16, graphics::Color::WHITE)?));

	event::run(context, event_loop, controller);
}