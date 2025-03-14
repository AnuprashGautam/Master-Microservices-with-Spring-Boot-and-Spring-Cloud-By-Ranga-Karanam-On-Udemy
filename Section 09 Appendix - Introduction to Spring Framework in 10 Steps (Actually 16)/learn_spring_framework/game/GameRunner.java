package com.in28minutes.spring.learn_spring_framework.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
	
	// private MarioGame game;
	// private SuperContraGame game;
	
	@Autowired
	private GamingConsole game;

	public GameRunner(GamingConsole game) {
		this.game=game;
	}

	public void run() {
		game.up();
		game.down();
		game.left();
		game.right();
	}
}
