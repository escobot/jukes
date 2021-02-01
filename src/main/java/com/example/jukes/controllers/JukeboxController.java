package com.example.jukes.controllers;

import com.example.jukes.models.Jukebox;
import com.example.jukes.services.JukeboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class JukeboxController {

    private final JukeboxService jukeboxService;

    @Autowired
    public JukeboxController(JukeboxService jukeboxService) {
        this.jukeboxService = jukeboxService;
    }

    @GetMapping("/jukebox/{settingId}")
    public ResponseEntity<List<Jukebox>> getJukeboxes(@PathVariable String settingId,
                                                     @RequestParam(required = false) Optional<String> model,
                                                     @RequestParam(required = false) Optional<Integer> offset,
                                                     @RequestParam(required = false) Optional<Integer> limit) {
        List<Jukebox> jukeboxes = jukeboxService.getJukeboxes(UUID.fromString(settingId), model);
        return ResponseEntity.ok(jukeboxes.subList(offset.orElse(0), limit.orElse(jukeboxes.size())));
    }

}
