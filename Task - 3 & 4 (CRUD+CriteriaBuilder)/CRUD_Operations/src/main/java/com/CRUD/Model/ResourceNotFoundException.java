package com.CRUD.Model;


import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.core.RepositoryCreationException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
        public ResourceNotFoundException(String message){
            super(message);
        }
}
