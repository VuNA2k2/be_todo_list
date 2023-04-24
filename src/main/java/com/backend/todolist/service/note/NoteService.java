package com.backend.todolist.service.note;

import com.backend.todolist.dto.notedto.NoteDetailOutputDto;
import com.backend.todolist.dto.notedto.NoteInputDto;
import com.backend.todolist.dto.notedto.NoteOutputDto;
import com.backend.todolist.dto.searchdto.SearchNoteInputDto;
import com.backend.todolist.entity.NoteEntity;
import com.backend.todolist.response.Pagination;
import org.springframework.data.domain.Pageable;

public interface NoteService {
    NoteDetailOutputDto getNoteDetail(Long noteId, Long userId);
    Pagination<NoteOutputDto> getNotesByUserId(Long userId, Pageable pageable, SearchNoteInputDto searchNoteInputDto);
    NoteDetailOutputDto createNote(NoteInputDto noteInputDto, Long userId);
    NoteDetailOutputDto updateNote(NoteInputDto noteInputDto, Long noteId, Long userId);
    void deleteNote(Long noteId, Long userId);
    boolean isNoteExist(Long noteId, Long userId);
    boolean isNoteExist(Long noteId);
    Pagination<NoteOutputDto> getNotesByProjectId(Long userId, Pageable pageable, SearchNoteInputDto search, Long projectId);

    NoteDetailOutputDto getNoteDetailOutputDtoFromNoteEntity(NoteEntity noteEntity);

}
