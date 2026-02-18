package com.example.Notepad.repository;


import com.example.Notepad.model.NoteModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends MongoRepository<NoteModel,String> {

    Optional<List<NoteModel>> findByUserId(String id);
}
