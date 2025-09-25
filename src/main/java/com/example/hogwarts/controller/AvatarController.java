package com.example.hogwarts.controller;

import com.example.hogwarts.service.AvatarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/avatar")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping("/paged")
    public <Avatar> ResponseEntity<List<Avatar>> getAvatarsPaged(
            @RequestParam("page") int pageNumber,
            @RequestParam("size") int pageSize) {
        List<Avatar> avatars = (List<Avatar>) avatarService.getAvatarsPaged(pageNumber, pageSize);
        return ResponseEntity.ok(avatars);
    }
}