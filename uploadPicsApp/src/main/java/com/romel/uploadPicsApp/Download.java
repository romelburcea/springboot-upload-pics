package com.romel.uploadPicsApp;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class Download {

    private final PService pService;

    public Download(PService pService) {
        this.pService = pService;
    }


    @GetMapping("download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){

        Picture picture = pService.get(id);
        if(picture == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        byte[] data = picture.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(picture.getContentType()));
        ContentDisposition build = ContentDisposition
                .builder("attachment")
                .filename(picture.getFileName())
                .build();
        headers.setContentDisposition(build);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
