package com.example.lab5q3.Controller;

import com.example.lab5q3.API.ApiResponse;
import com.example.lab5q3.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/event")
public class EventController {

    ArrayList<Event>  events = new ArrayList<>();

    @GetMapping("/get-events")
    public ArrayList<Event> getEvents(){
        return events;
    }

    @PostMapping("/add-event")
    public ApiResponse addEvents(@RequestBody Event event){
        events.add(event);
        return new ApiResponse("Event added successfully");
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateEvent(@PathVariable String id, @RequestBody Event updateEvent){

        for(Event event: events){
            if(event.getId().equals(id)){
                event.setDescription(updateEvent.getDescription());
                event.setCapacity(updateEvent.getCapacity());
                event.setStartDate(updateEvent.getStartDate());
                event.setEndDate(updateEvent.getEndDate());
                return new ApiResponse("Event updated successfully");
            }
        }
        return new ApiResponse("Event not found");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteEvent(@PathVariable String id){
        for(int i=0; i<events.size(); i++){
            if(events.get(i).getId().equals(id)){
                events.remove(i);
                return new ApiResponse("Event deleted successfully");
            }
        }
        return new ApiResponse("Event not found");
    }


    @PutMapping("/change-capacity/{id}/{capacity}")
    public ApiResponse changeCapacity(@PathVariable String id, @PathVariable int capacity){
        for(Event event: events){
            if(event.getId().equals(id)){
                event.setCapacity(capacity);
                return new ApiResponse("Capacity updated successfully");
            }
        }
        return new ApiResponse("Event not found");
    }

@GetMapping("/search-byID/{id}")
    public Event searchById(@PathVariable String id){
        for(Event event: events){
            if(event.getId().equals(id)){
                return event;
            }
        }
        return null;
    }



}
