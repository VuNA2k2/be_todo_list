package com.backend.todolist.service.note;

import com.backend.todolist.dto.notedto.NoteDetailOutputDto;
import com.backend.todolist.dto.notedto.NoteInputDto;
import com.backend.todolist.dto.notedto.NoteOutputDto;
import com.backend.todolist.dto.projectdto.ProjectOutputDto;
import com.backend.todolist.dto.searchdto.SearchNoteInputDto;
import com.backend.todolist.entity.NoteEntity;
import com.backend.todolist.entity.ProjectEntity;
import com.backend.todolist.repository.NoteRepository;
import com.backend.todolist.repository.ProjectRepository;
import com.backend.todolist.response.Pagination;
import com.backend.todolist.service.project.ProjectMapper;
import com.backend.todolist.utils.exception.Errors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service
public class NoteServiceImpl implements NoteService {
    private final NoteMapper noteMapper;
    private final NoteRepository noteRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public NoteServiceImpl(NoteMapper noteMapper, NoteRepository noteRepository, ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.noteMapper = noteMapper;
        this.noteRepository = noteRepository;
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public NoteDetailOutputDto getNoteDetail(Long noteId, Long userId) {
        if (!isNoteExist(noteId, userId)) {
            throw Errors.NOTE_NOT_FOUND;
        }
        NoteEntity noteEntity = noteRepository.findById(noteId).orElseThrow(() -> Errors.NOTE_NOT_FOUND);
        return getNoteDetailOutputDtoFromNoteEntity(noteEntity);
    }

    @Override
    public Pagination<NoteOutputDto> getNotesByUserId(Long userId, Pageable pageable, SearchNoteInputDto searchNoteInputDto) {
        Pagination<NoteOutputDto> pagination = new Pagination<>();
        Page<NoteEntity> noteEntities = noteRepository.searchInUser(userId, searchNoteInputDto.getKeyword() != null ? searchNoteInputDto.getKeyword() : "", pageable);
        pagination.setItems(noteEntities.stream().map(noteMapper::getNoteOutputDtoFromNoteEntity).collect(Collectors.toList()));
        pagination.setTotals(noteEntities.getTotalElements());
        return pagination;
    }

    @Override
    public NoteDetailOutputDto createNote(NoteInputDto noteInputDto, Long userId) {
        NoteEntity noteEntity = noteMapper.getNoteEntityFromNoteInputDto(noteInputDto);
        validate(noteEntity, userId);
        return getNoteDetailOutputDtoFromNoteEntity(noteRepository.save(noteEntity));
    }

    @Override
    public NoteDetailOutputDto updateNote(NoteInputDto noteInputDto, Long noteId, Long userId) {
        NoteEntity noteEntity = noteMapper.getNoteEntityFromNoteInputDto(noteInputDto);
        noteEntity.setId(noteId);
        validate(noteEntity, userId);
        return getNoteDetailOutputDtoFromNoteEntity(noteRepository.save(noteEntity));
    }

    @Override
    public void deleteNote(Long noteId, Long userId) {
        if (!isNoteExist(noteId, userId)) {
            throw Errors.NOTE_NOT_FOUND;
        }
        noteRepository.deleteById(noteId);
    }

    @Override
    public boolean isNoteExist(Long noteId, Long userId) {
        return noteRepository.existsByIdAndUserId(noteId, userId);
    }

    @Override
    public boolean isNoteExist(Long noteId) {
        return noteRepository.existsById(noteId);
    }

    @Override
    public Pagination<NoteOutputDto> getNotesByProjectId(Long userId, Pageable pageable, SearchNoteInputDto search, Long projectId) {
        Pagination<NoteOutputDto> pagination = new Pagination<>();
        Page<NoteEntity> noteEntities = noteRepository.searchInProject(projectId, search.getKeyword() != null ? search.getKeyword() : "",userId , pageable);
        pagination.setItems(noteEntities.stream().map(noteMapper::getNoteOutputDtoFromNoteEntity).collect(Collectors.toList()));
        pagination.setTotals(noteEntities.getTotalElements());
        return pagination;
    }

    @Override
    public NoteDetailOutputDto getNoteDetailOutputDtoFromNoteEntity(NoteEntity noteEntity) {
        NoteDetailOutputDto noteDetailOutputDto = noteMapper.getNoteDetailOutputDtoFromNoteEntity(noteEntity);
        ProjectEntity projectEntity = projectRepository.findById(noteEntity.getProjectId()).orElseThrow(() -> Errors.PROJECT_NOT_FOUND);
        ProjectOutputDto projectOutputDto = projectMapper.getProjectOutputDtoFromProjectEntity(projectEntity);
        noteDetailOutputDto.setProject(projectOutputDto);
        return noteDetailOutputDto;
    }

    private void validate(NoteEntity noteEntity, Long userId) {
        if(noteEntity.getId() != null && !isNoteExist(noteEntity.getId(), userId)) {
            throw Errors.NOTE_NOT_FOUND;
        }
        if(!projectRepository.existsByIdAndUserId(noteEntity.getProjectId(), userId)) {
            throw Errors.PROJECT_NOT_FOUND;
        }
    }
}
