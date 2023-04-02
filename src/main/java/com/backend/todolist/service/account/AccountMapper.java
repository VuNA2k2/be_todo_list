package com.backend.todolist.service.account;


import com.backend.todolist.dto.accountdto.AccountInputDto;
import com.backend.todolist.dto.accountdto.AccountOutputDto;
import com.backend.todolist.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountEntity getEntityFromInput(AccountInputDto inputDto);

    AccountOutputDto getOutputFromEntity(AccountEntity entity);
}
