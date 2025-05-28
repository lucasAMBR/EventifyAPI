package com.equipeAcelera.EventifyAPI.services;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.ConfirmationCode.ConfirmationCode;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.DataNotFoundException;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.UnauthorizedFunctionAccessException;
import com.equipeAcelera.EventifyAPI.models.Event.Event;
import com.equipeAcelera.EventifyAPI.models.Event.PresentialEvent;
import com.equipeAcelera.EventifyAPI.models.Subscription.Subscription;
import com.equipeAcelera.EventifyAPI.models.User.User;
import com.equipeAcelera.EventifyAPI.utils.CryptoUtils;

@Service
public class ConfirmationService {
    public static Map<Integer, ConfirmationCode> confirmationCodes = new HashMap<>();

    private static Set<String> auxCodeList = new HashSet<>(); 

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @Autowired 
    SubscriptionService subscriptionService;

    public ConfirmationCode generateConfirmationCode(int eventId, int userId){
        Event foundedEvent = eventService.getEventById(eventId);

        if(foundedEvent.getOrganizerId() != userId){
            throw new UnauthorizedFunctionAccessException("You cannot generate a invite from someone event");
        }

        String code;

        do{
            code = generateRandomString();
        }while(auxCodeList.contains(code));

        ConfirmationCode confirmationCode = new ConfirmationCode(generateRandomString(), foundedEvent.getDate().plusDays(1), foundedEvent.getHour());

        confirmationCodes.put(foundedEvent.getId(), confirmationCode);

        return confirmationCode;
    }

    public boolean useConfirmationCode(String email, String password, String code, double latitude, double logitude){
        Integer eventId = GetEventIdByConfirmationCode(code);

        if(eventId == null){
            return false;
        }

        Event foundedEvent = eventService.getEventById(eventId);

        User user = userService.findUserByEmail(email);

        if(!CryptoUtils.verifyPassword(password, user.getPassword())){
            throw new DataNotFoundException("User not found!");
        }

        for(Subscription subs : foundedEvent.getSubscriptionList()){
            if(subs.getUserId() == user.getId()){
                double distance = calculateDistance(latitude, logitude, ((PresentialEvent) foundedEvent).getLatitude(), ((PresentialEvent) foundedEvent).getLongitude());
                if(distance < 0.2){
                    subs.setStatus("PRESENT");
                    return true;
                }else{
                    throw new RuntimeException("Your are not in the local!");
                }
            }
        }

        return false;
    }

    public double calculateDistance(double latitudeUser, double longitudeUser, double latitudeEvent, double longitudeEvent) {
        final int R = 6371;

        double latDistance = Math.toRadians(latitudeEvent - latitudeUser);
        double lonDistance = Math.toRadians(longitudeEvent - longitudeUser);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(latitudeUser)) * Math.cos(Math.toRadians(latitudeEvent))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
}


    public Integer GetEventIdByConfirmationCode(String code){
        for(Map.Entry<Integer,ConfirmationCode> entry : confirmationCodes.entrySet()){
            if(entry.getValue().getCode().equals(code)){
                return entry.getKey();
            }
        }
        return null;
    }

    public String generateRandomString(){
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        int codeLength = 10;
        SecureRandom random = new SecureRandom();

        StringBuilder sb = new StringBuilder(codeLength);
        for(int i = 0; i < codeLength; i++){
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }
}
