package com.equipeAcelera.EventifyAPI.services;

import java.security.SecureRandom;
import java.time.LocalDate;
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

    // Mapa: código → ID do evento (para busca rápida)
    private static Map<String, Integer> codeToEventId = new HashMap<>();

    // Mapa: ID do evento → código de confirmação
    private static Map<Integer, ConfirmationCode> confirmationCodes = new HashMap<>();

    private static Set<String> auxCodeList = new HashSet<>();

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @Autowired
    SubscriptionService subscriptionService;

    public ConfirmationCode generateConfirmationCode(int eventId, int userId) {
        Event foundedEvent = eventService.getEventById(eventId);

        if (foundedEvent.getOrganizerId() != userId) {
            throw new UnauthorizedFunctionAccessException("You cannot generate an invite for someone else's event");
        }

        String code;

        // Garante que o código seja único
        do {
            code = generateRandomString();
        } while (auxCodeList.contains(code));

        auxCodeList.add(code);

        ConfirmationCode confirmationCode = new ConfirmationCode(
            code,
            foundedEvent.getDate().plusDays(1),
            foundedEvent.getHour()
        );

        // Armazena nos dois mapas
        confirmationCodes.put(foundedEvent.getId(), confirmationCode);
        codeToEventId.put(code, foundedEvent.getId());

        System.out.println("Código gerado: " + code);

        return confirmationCode;
    }

    public boolean useOnlineConfirmationCode(String email, String password, String code) {
        Integer eventId = getEventIdByConfirmationCode(code);
         if (eventId == null) {
            System.out.println("Código não encontrado: " + code);
            return false;
        }

        Event foundedEvent = eventService.getEventById(eventId);
        User user = userService.findUserByEmail(email);

        if (!CryptoUtils.verifyPassword(password, user.getPassword())) {
            throw new DataNotFoundException("User not found!");
        }

        for (Subscription subs : foundedEvent.getSubscriptionList()) {
            if (subs.getUserId() == user.getId()) {
                    subs.setStatus("PRESENT");
                    return true;
            }
        }

        return false;
    }

    public boolean useConfirmationCode(String email, String password, String code, double latitude, double longitude) {
        Integer eventId = getEventIdByConfirmationCode(code);

        if (eventId == null) {
            System.out.println("Código não encontrado: " + code);
            return false;
        }

        Event foundedEvent = eventService.getEventById(eventId);
        User user = userService.findUserByEmail(email);

        if (!CryptoUtils.verifyPassword(password, user.getPassword())) {
            throw new DataNotFoundException("User not found!");
        }

        for (Subscription subs : foundedEvent.getSubscriptionList()) {
            if (subs.getUserId() == user.getId()) {
                double distance = calculateDistance(
                    latitude,
                    longitude,
                    ((PresentialEvent) foundedEvent).getLatitude(),
                    ((PresentialEvent) foundedEvent).getLongitude()
                );

                if (distance < 0.2) {
                    subs.setStatus("PRESENT");
                    return true;
                } else {
                    throw new RuntimeException("You are not in the local!");
                }
            }
        }

        return false;
    }

    public Integer getEventIdByConfirmationCode(String code) {
        if (code == null) return null;
        return codeToEventId.get(code.trim());
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

    public String generateRandomString() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        int codeLength = 10;
        SecureRandom random = new SecureRandom();

        StringBuilder sb = new StringBuilder(codeLength);
        for (int i = 0; i < codeLength; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }
}
