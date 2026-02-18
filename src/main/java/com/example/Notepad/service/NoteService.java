package com.example.Notepad.service;


import com.example.Notepad.ExceptionHandler.ResourceNotFoundException;
import com.example.Notepad.dto.NoteRequestDTO;
import com.example.Notepad.dto.NoteResponseDTO;
import com.example.Notepad.model.NoteModel;
import com.example.Notepad.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    @Autowired
    private final NoteRepository noteRepository;

    public NoteResponseDTO save(NoteRequestDTO noteRequestDTO) throws Exception {
        try {
            NoteModel noteModel = NoteModel.ToEntity(noteRequestDTO);
            noteModel.setCreatedAt(Instant.now());
            noteModel.setUpdatedAt(Instant.now());

            NoteModel save = noteRepository.save(noteModel);
            NoteResponseDTO noteResponseDTO = NoteResponseDTO.toNoteResponseDTO(save);
            return noteResponseDTO;

        }
        catch (Exception exception){
            throw new Exception("Something went wrong");
        }
    }



    public List<NoteResponseDTO> findAll(String id){
        List<NoteModel> noteModel = noteRepository.findByUserId(id).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        List<NoteResponseDTO> noteResponseDTOS = noteModel.stream().map((NoteModel model) -> {
            return NoteResponseDTO.toNoteResponseDTO(model);
        }).toList();
        return noteResponseDTOS;
    }

    public NoteResponseDTO update(String title,String content,String id) throws Exception {
        try {
            NoteModel noteModel = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note not found with ID: " + id));
            noteModel.setContent(content);
            noteModel.setTitle(title);
            noteModel.setUpdatedAt(Instant.now());
            NoteModel save = noteRepository.save(noteModel);
            NoteResponseDTO noteResponseDTO = NoteResponseDTO.toNoteResponseDTO(save);
            return noteResponseDTO;
        }
        catch (Exception exception){
            throw new Exception("Something went wrong");
        }
    }



    public void  deleteNote(String id) throws Exception {
        try {
            NoteModel noteModel = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note not found with ID: " + id));
            noteRepository.delete(noteModel);
        }
        catch (Exception exception){
            throw new Exception("Something went wrong");
        }
    }
}

