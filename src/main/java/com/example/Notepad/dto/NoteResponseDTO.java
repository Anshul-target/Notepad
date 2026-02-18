package com.example.Notepad.dto;


import com.example.Notepad.model.NoteModel;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class NoteResponseDTO {

    private  String id;
    private String title;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;
    private String userId;

    public static NoteResponseDTO toNoteResponseDTO(NoteModel note){
        return NoteResponseDTO.builder()
                .id(note.getId())
                .content(note.getContent())
                .title(note.getTitle())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .userId(note.getUserId())
                .build();
    }
}
