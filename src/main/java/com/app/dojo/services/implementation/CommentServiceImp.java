package com.app.dojo.services.implementation;

import com.app.dojo.builders.builderDTO.CommentResponseBuilder;
import com.app.dojo.dtos.CommentDTO;
import com.app.dojo.dtos.CommentResponse;
import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.mappers.MapperComment;
import com.app.dojo.models.Comment;
import com.app.dojo.models.Course;
import com.app.dojo.models.Student;
import com.app.dojo.models.Teacher;
import com.app.dojo.repositories.CommentRepository;
import com.app.dojo.services.Interfaces.CommentService;
import com.app.dojo.services.Interfaces.CourseService;
import com.app.dojo.services.Interfaces.StudentService;
import com.app.dojo.services.Interfaces.TeacherService;
import com.app.dojo.services.strategyComments.CommentsContext;
import com.app.dojo.services.strategyComments.CommentsStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    MapperComment mapperComment;

    @Autowired
    CourseService courseService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    CommentsContext commentsContext;

    @Override
    public Comment createOne(CommentDTO commentDTO) throws Exception {
        Course courseFound = this.courseService.getOne(commentDTO.getCourse());
        Teacher teacherFound = this.teacherService.getById(commentDTO.getTeacher());
        Student studentFound = this.studentService.getStudentById(commentDTO.getStudent());

        Comment commentCreated = this.commentRepository.save(this.mapperComment.createComment(commentDTO, courseFound, teacherFound, studentFound));

        return commentCreated;
    }

    @Override
    public CommentResponse getAll(int numberPage, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(numberPage, pageSize, sort);

        Page<Comment> commentsFound = this.commentRepository.findAll(pageable);

        return new CommentResponseBuilder()
                .setContent(commentsFound.getContent())
                .setNumberPage(commentsFound.getNumber())
                .setSizePage(commentsFound.getSize())
                .setTotalElements(commentsFound.getTotalElements())
                .setTotalPages(commentsFound.getTotalPages())
                .setLastOne(commentsFound.isLast())
                .build();
    }


}
