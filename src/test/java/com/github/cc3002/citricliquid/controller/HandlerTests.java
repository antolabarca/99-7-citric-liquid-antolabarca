package com.github.cc3002.citricliquid.controller;

import com.github.cc3002.citricjuice.controller.GameController;
import com.github.cc3002.citricjuice.model.unit.Player;
import org.junit.jupiter.api.BeforeEach;

public class HandlerTests {
    GameController controller;
    Player player;

    @BeforeEach
    public void setUp(){
        controller = new GameController();
        player = new Player("suguri", 4, 2, 1, -1);

    }

}
