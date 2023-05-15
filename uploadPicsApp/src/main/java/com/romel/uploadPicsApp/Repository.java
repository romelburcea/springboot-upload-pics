package com.romel.uploadPicsApp;

import com.romel.uploadPicsApp.Picture;
import org.springframework.data.repository.CrudRepository;

public interface Repository extends CrudRepository<Picture, Integer> {

}