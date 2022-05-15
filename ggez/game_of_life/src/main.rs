use ggez;
use ggez::{Context, GameResult};
use ggez::graphics;
use ggez::event;


const WIDTH: f32 = 45.0;
const HEIGHT: f32 = 28.0;
const SIZE: f32 = 16.0;
const SPACER: f32 = 4.0;


struct Controller {

}

impl Controller {
	pub fn new() -> Self {
		Controller {

		}
	}
}

impl event::EventHandler for Controller {
	fn update(&mut self, context: &mut Context) -> GameResult {
		Ok(())
	}
	fn draw(&mut self, context: &mut Context) -> GameResult {
		graphics::clear(context, graphics::Color::BLACK);
		graphics::present(context)?;
		Ok(())
	}
}

fn main() -> GameResult {
	println!("START!");

	let cb = ggez::ContextBuilder::new("game_of_life", "Lelsers Lasers")
		.window_mode(ggez::conf::WindowMode::default().dimensions(WIDTH * (SIZE + SPACER) + SPACER, HEIGHT * (SIZE + SPACER) + SPACER));
	let (context, event_loop) = cb.build()?;
	graphics::set_window_title(&context, "Game Of Life");

	let controller = Controller::new();
	event::run(context, event_loop, controller);
}
