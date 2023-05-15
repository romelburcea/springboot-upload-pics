package com.romel.uploadPicsApp;


import org.springframework.stereotype.Service;

@Service
public class PService {

    private final Repository repository;

    public PService(Repository repository) {
        this.repository = repository;
    }

    public Iterable<Picture> get() {
        return repository.findAll();
    }

    public Picture get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        repository.deleteById(id);
    }

    public Picture save(String fileName, String contentType, byte[] data) {
        Picture picture = new Picture();
        picture.setContentType(contentType);
        picture.setFileName(fileName);
        picture.setData(data);
        repository.save(picture);
        return picture;
    }
}
