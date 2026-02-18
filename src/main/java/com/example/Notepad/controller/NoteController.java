package com.example.Notepad.controller;


import com.example.Notepad.dto.NoteRequestDTO;
import com.example.Notepad.dto.NoteResponseDTO;
import com.example.Notepad.dto.UpdateDTO;
import com.example.Notepad.service.NoteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "NoteController API",description = "Here is the CRUD api of the NoteController")
@RequestMapping("/app/v1/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

//    READ

    @GetMapping("/{userid}")
    public ResponseEntity<List<NoteResponseDTO>> getAllNotes(@PathVariable String userid){
        List<NoteResponseDTO> all = noteService.findAll(userid);
        return ResponseEntity.ok(all);
    }

    @PostMapping
    public ResponseEntity<NoteResponseDTO> saveNote(@Valid @RequestBody NoteRequestDTO noteRequestDTO) throws Exception {
        NoteResponseDTO note = noteService.save(noteRequestDTO);
        return ResponseEntity.ok(note);
    }


    @PutMapping("/{id}")
    public ResponseEntity<NoteResponseDTO> saveNote(@PathVariable String id,@RequestBody UpdateDTO updateDTO) throws Exception {
        NoteResponseDTO note = noteService.update(updateDTO.getTitle(),updateDTO.getContent(), id);

        return ResponseEntity.ok(note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteNote(@PathVariable  String id) throws Exception {
        noteService.deleteNote(id);
        Map<String,String> message=new HashMap<>();
        message.put("message","Note deleted");
        return  ResponseEntity.ok(message);
    }
}
