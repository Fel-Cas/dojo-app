package com.app.dojo.repositories;

import com.app.dojo.builders.builderModels.TeacherBuilder;
import com.app.dojo.models.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles(profiles = "test")
public class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    private Teacher teacher;

    //Formatted date
    String date = "04/02/1995";
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date formatDate = format.parse(date);

    public TeacherRepositoryTest() throws ParseException {
    }

    @BeforeEach
    void begin(){
        teacher = new TeacherBuilder()
                .setDni("987654321")
                .setNames("Jorge")
                .setLastnames("Ortíz")
                .setBirthday(formatDate)
                .setEmail("jorgeortiz@mail.com")
                .setPassword("987654321")
                .build();
    }

    @DisplayName("Test Repository to save a teacher")
    @Test
    void save(){
        Teacher teacherSaved = this.teacherRepository.save(teacher);

        assertNotNull(teacherSaved);
        assertThat(teacherSaved.getId()).isGreaterThan(0);
    }

    @DisplayName("Test Repository to get all teachers")
    @Test
    void getAll(){
        Teacher teacher1 = new TeacherBuilder()
                .setDni("864297531")
                .setNames("Carlos")
                .setLastnames("Andrade")
                .setBirthday(formatDate)
                .setEmail("carlosandrade@mail.com")
                .setPassword("864297531")
                .build();
        this.teacherRepository.save(teacher);
        this.teacherRepository.save(teacher1);

        List<Teacher> teacherList = this.teacherRepository.findAll();

        assertNotNull(teacherList);
        assertEquals(2, teacherList.size());
    }
}
