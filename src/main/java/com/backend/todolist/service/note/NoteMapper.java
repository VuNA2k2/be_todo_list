package com.backend.todolist.service.note;

import com.backend.todolist.dto.notedto.NoteDetailOutputDto;
import com.backend.todolist.dto.notedto.NoteInputDto;
import com.backend.todolist.dto.notedto.NoteOutputDto;
import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import com.backend.todolist.entity.NoteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    NoteEntity getNoteEntityFromNoteInputDto(NoteInputDto noteInputDto);
    NoteOutputDto getNoteOutputDtoFromNoteEntity(NoteEntity noteEntity);
    NoteDetailOutputDto getNoteDetailOutputDtoFromNoteEntity(NoteEntity noteEntity);
}
