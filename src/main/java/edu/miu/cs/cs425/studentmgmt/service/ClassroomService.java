package edu.miu.cs.cs425.studentmgmt.service;

import edu.miu.cs.cs425.studentmgmt.model.Classroom;

import java.util.List;

public interface ClassroomService {
    Classroom saveClassroom(Classroom classroom);
    List<Classroom> getAllClassrooms();
}
