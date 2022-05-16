# Game Of Life using Rust + ggez

![Normal View](https://github.com/LelsersLasers/GameOfLife/raw/main/ggez/Showcase/normalView.PNG)
![Difference View](https://github.com/LelsersLasers/GameOfLife/raw/main/ggez/Showcase/differenceView.PNG)
![Neighbors View](https://github.com/LelsersLasers/GameOfLife/raw/main/ggez/Showcase/neighborsView.PNG)
![Neighbors Advanced View](https://github.com/LelsersLasers/GameOfLife/raw/main/ggez/Showcase/advancedView.PNG)

To run:
- Run the .exe in 'game_of_life\target\release'
- Or while in 'game_of_life' run 'cargo run -r'

Controls:
- Can close the window normally (with X button in top right, etc)
- R: re-randomize the cells
- SPACE: switch between view modes
	- normal view: shows alive cells
	- change view: shows newly alive, newly dead, and still alive cells
	- neighbor view: shows alive cells and neighbor counts as a greyscale
	- advanced neighbor view: shows neighor counts as a greyscale and text with neighbor count
- Mouse Down: toggle pause/freeze
- Up Arrow: increase updates per second by 1
- Down Arrow: decrease updates per second by 1
