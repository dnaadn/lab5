package com.example.lab5q2.Controller;

import com.example.lab5q2.API.ApiResponse;
import com.example.lab5q2.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {


    ArrayList<Project> projects = new ArrayList<>();


    @GetMapping("get-projects")
    public ArrayList<Project> getProjects(){
        return projects;
    }

    @PostMapping("add-project")
    public ApiResponse addProject(@RequestBody Project project){
        projects.add(project);
        return new ApiResponse("Project added successfully");
    }


    @PutMapping("/update/{id}")
    public ApiResponse updateProject(@PathVariable String id , @RequestBody Project updatedproject){

        for(Project project1: projects){
            if(project1.getId().equals(id)){
                project1.setTitle(updatedproject.getTitle());
                project1.setDescription(updatedproject.getDescription());
                project1.setStatus(updatedproject.isStatus());
                project1.setCompanyName(updatedproject.getCompanyName());

                return new ApiResponse("Project updated successfully");
            }
        }
        return new ApiResponse("Project not found");
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteProject(@PathVariable String id){

        for(int i=0; i<projects.size(); i++){
            if(projects.get(i).getId().equals(id)){
                projects.remove(i);
                return new ApiResponse("Project deleted successfully");
            }
        }
        return new ApiResponse("Project not found");
    }

    @GetMapping("/search/title/{title}")
    public Project searchByTitle(@PathVariable String title){
        for (Project project : projects) {
            if (project.getTitle().equalsIgnoreCase(title)) {
                return project;
            }
        }
        return null;
    }

      @PutMapping("/status/{id}/{status}")
        public ApiResponse changeStatus(@PathVariable String id, @PathVariable boolean status) {
            for (Project project : projects) {
                if (project.getId().equals(id)) {
                    project.setStatus(status);
                    return new ApiResponse("Task status updated");
                }
            }
            return new ApiResponse("Task not found");

        }

        @GetMapping("/search/company/{companyName}")
        public ArrayList<Project> searchByCompanyName(@PathVariable String companyName){

        ArrayList<Project> result = new ArrayList<>();

            for (Project project : projects) {
                if (project.getCompanyName().equalsIgnoreCase(companyName)) {
                    result.add(project);
                }
            }
        return result;
        }



}
