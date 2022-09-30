package com.app.dojo.mappers;

import com.app.dojo.builders.builderDTO.TeacherDTOBuilder;
import com.app.dojo.dtos.TeacherDTO;
import com.app.dojo.models.Teacher;

public class TeacherDTOMapper {
    public static TeacherDTO mapTeacherDTO(Teacher teacher){
        return new TeacherDTOBuilder()
                .setId(teacher.getId())
                .setDni(teacher.getDni())
                .setNames(teacher.getNames())
                .setLastnames(teacher.getLastnames())
                .setEmail(teacher.getEmail())
                .setPassword(teacher.getPassword())
                .build();
    }
}
