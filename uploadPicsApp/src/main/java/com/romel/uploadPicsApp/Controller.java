package com.romel.uploadPicsApp;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class Controller {

    private final PService service;

    public Controller(PService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/pictures")
    public Iterable<Picture> get() {
        return service.get();
    }

    @GetMapping("/pictures/{id}")
    public Picture get(@PathVariable Integer id) {
        Picture picture = service.get(id);
        if (picture == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return service.get(id);
    }

    @DeleteMapping("/pictures/{id}")
    public void delete(@PathVariable Integer id) {
        service.remove(id);
    }

    @PostMapping("/pictures")
    public Picture create(@RequestPart("data") MultipartFile file) throws IOException {
        return service.save(file.getOriginalFilename(),file.getContentType(), file.getBytes());
    }


}