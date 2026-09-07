package com.example.lab5.Controller;

import com.example.lab5.API.ApiResponse;
import com.example.lab5.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {


    ArrayList<Student> students = new ArrayList<>();


    @GetMapping("/get-students")
    public ArrayList<Student> getStudents(){
        return students;

    }

    @PostMapping("/add-students")
    public ApiResponse addStudents(@RequestBody Student student){
        students.add(student);
        return new ApiResponse("Student added successfully");
    }

    @PutMapping("/updated/{id}")
    public ApiResponse updateStudents(@PathVariable String id, @RequestBody Student updatedstudent){

        for(Student student1: students){
            if(student1.getId().equals(id)){
                student1.setName(updatedstudent.getName());
                student1.setAge(updatedstudent.getAge());
                student1.setDegree(updatedstudent.getDegree());
                student1.setGPA(updatedstudent.getGPA());
                return new ApiResponse("Student updated successfully");
            }
        }
        return new ApiResponse("Student not found");
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteStudent(@PathVariable String id){
        for(int i=0; i<students.size();i++){
            if(students.get(i).getId().equals(id)){
                students.remove(i);
                return new ApiResponse("Student deleted successfully");
            }
        }
        return new ApiResponse("Student not found");
    }


    @GetMapping("/honors")
    public List<List<Student>> classifyByHonors(){

        List<Student> CumLaude = new ArrayList<>();
        List<Student> MagnaCumLaude = new ArrayList<>();
        List<Student> SummaCumLaude = new ArrayList<>();

        for(Student student : students){
            double gpa = student.getGPA();
            if(gpa>=3.90){
                SummaCumLaude.add(student);
            } else if (gpa>=3.70 ) {
                MagnaCumLaude.add(student);
            } else if (gpa>=3.50 ) {
                CumLaude.add(student);
            }
        }

        List <List<Student>> honors = new ArrayList<>();
        honors.add(CumLaude);
        honors.add(MagnaCumLaude);
        honors.add(SummaCumLaude);

        return honors;

    }


    @GetMapping("/above-gpa")
    public ArrayList<Student> aboveAvgGpa(){

        ArrayList<Student> avgGpa = new ArrayList<>();

        if(students.isEmpty()){
            return avgGpa;
        }
        double sum = 0;

        for(Student student: students){
            sum+=student.getGPA();
        }
        double avg = sum/students.size();

        for(Student student:students){
            if(student.getGPA()>avg){
                avgGpa.add(student);
            }
        }
        return avgGpa;
    }



}
