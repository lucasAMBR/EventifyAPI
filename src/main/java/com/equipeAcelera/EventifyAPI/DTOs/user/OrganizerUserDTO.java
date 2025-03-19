package com.equipeAcelera.EventifyAPI.DTOs.user;

import java.util.List;

import com.equipeAcelera.EventifyAPI.DTOs.post.ReducedPostDTO;
import com.equipeAcelera.EventifyAPI.models.Event.Event;
import com.equipeAcelera.EventifyAPI.models.Like.Like;

public class OrganizerUserDTO extends UserDTO{
    private String contact;
    private List<Event> eventList;
    
    public OrganizerUserDTO(int id, String name, String cpf, String profilePicPath, List<ReducedPostDTO> postList,
            List<Like> likelist, List<ReducedUserDTO> following, List<ReducedUserDTO> followers, String contact,
            List<Event> eventList) {
        super(id, name, cpf, profilePicPath, postList, likelist, following, followers);
        this.contact = contact;
        this.eventList = eventList;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    
}
