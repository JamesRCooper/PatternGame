package com.cooper.controllers.arena;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cooper.dto.InteractiveBlockDTO;
import com.cooper.game.pool.ActivePool;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pool/block")
public class BlockController {

    private ActivePool pool;

    public BlockController(ActivePool pool) {
        this.pool = pool;
    }

    @RequestMapping(value = "/", params = {"activeId"})
    @ResponseBody
    public InteractiveBlockDTO checkForBlock(@RequestParam final String activeId) {

        return pool.getBlockCommands(activeId);
    }

    @RequestMapping(value = "/", params = {"activeId"}, method = RequestMethod.PUT)
    @ResponseBody
    public InteractiveBlockDTO runCommand(
            @RequestParam final String activeId,
            @RequestBody InteractiveBlockDTO dto) {

        return pool.runBlockCommand(activeId, dto);
    }
}
