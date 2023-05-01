package com.backend.todolist.controller;

import com.backend.todolist.dto.notedto.NoteDetailOutputDto;
import com.backend.todolist.dto.notedto.NoteInputDto;
import com.backend.todolist.dto.notedto.NoteOutputDto;
import com.backend.todolist.dto.searchdto.SearchNoteInputDto;
import com.backend.todolist.entity.UserDetailEntity;
import com.backend.todolist.response.Pagination;
import com.backend.todolist.response.Response;
import com.backend.todolist.service.note.NoteService;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    @GetMapping("/detail")
    Response<NoteDetailOutputDto> getNoteDetailById(@RequestParam Long id) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        return Response.success(noteService.getNoteDetail(id, userDetailEntity.getAccount().getUserId()));
    }
    @GetMapping("/search")
    Response<Pagination<NoteOutputDto>> searchNoteByUser(Pageable pageable,@ModelAttribute SearchNoteInputDto search) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        Pagination<NoteOutputDto> noteOutputDtoPagination = noteService.getNotesByUserId(userDetailEntity.getAccount().getUserId(), pageable, search);
        Response response = Response.success(noteOutputDtoPagination);
        return response;
    }
    @GetMapping("/search/{projectId}")
    Response<Pagination<NoteOutputDto>> searchNoteByProject(Pageable pageable, SearchNoteInputDto search, @PathVariable Long projectId) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        return Response.success(noteService.getNotesByProjectId(userDetailEntity.getAccount().getUserId(), pageable, search, projectId));
    }
    @PostMapping()
    Response<NoteDetailOutputDto> createNote(@RequestBody NoteInputDto noteInputDto) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        return Response.success(noteService.createNote(noteInputDto, userDetailEntity.getAccount().getUserId()));
    }
    @PutMapping()
    Response<NoteDetailOutputDto> updateNote(@RequestBody NoteInputDto noteInputDto, @RequestParam Long id) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        return Response.success(noteService.updateNote(noteInputDto, id, userDetailEntity.getAccount().getUserId()));
    }
    @DeleteMapping()
    Response<String> deleteNote(@RequestParam Long id) {
        UserDetailEntity userDetailEntity = getUserDetailEntity();
        noteService.deleteNote(id, userDetailEntity.getAccount().getUserId());
        return Response.success();
    }
    private UserDetailEntity getUserDetailEntity() {
        return (UserDetailEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
