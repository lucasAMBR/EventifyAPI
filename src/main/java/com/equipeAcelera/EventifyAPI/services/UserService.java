package com.equipeAcelera.EventifyAPI.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.user.RegisterNormalUserDTO;
import com.equipeAcelera.EventifyAPI.DTOs.user.RegisterOrganizerUserDTO;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.InvalidArgumentException;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.UserAlreadyExistException;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.DataNotFoundException;
import com.equipeAcelera.EventifyAPI.models.User.NormalUser;
import com.equipeAcelera.EventifyAPI.models.User.OrganizerUser;
import com.equipeAcelera.EventifyAPI.models.User.User;
import com.equipeAcelera.EventifyAPI.utils.AuthUtils;
import com.equipeAcelera.EventifyAPI.utils.CryptoUtils;
import com.equipeAcelera.EventifyAPI.utils.ValidationUtils;
import com.equipeAcelera.EventifyAPI.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.equipeAcelera.EventifyAPI.services.EmailService;




@Service
public class UserService {

    @Autowired
    private EmailService emailService;

    // Lista que armazena todos os usuarios do sistema, normais e organizadores
    public static List<User> userList = new ArrayList<>();

    // Cadastra usuario normal
    public NormalUser RegisterNormalUser(RegisterNormalUserDTO user){ 

        ValidationUtils.verifyEmail(user.getEmail());

        if(user.getPassword().length() < 6){
            throw new InvalidArgumentException("Invalid password, minimum: 6 chars");
        }
        
        String formatedCPF = ValidationUtils.verifyAndFormatCPF(user.getCpf());

        if(AuthUtils.verifyExistentUser(userList, user.getEmail())){
            throw new UserAlreadyExistException("This email is already in use!");
        }

        NormalUser newUser = new NormalUser(
            userList.size() + 1, 
            user.getName(),
            formatedCPF, 
            user.getEmail(), 
            CryptoUtils.encryptPassword(user.getPassword()), 
            ImageUtils.saveProfilePic(user.getProfilePic()),
            user.getBirth(),
            new ArrayList<>(), 
            new ArrayList<>(), 
            new ArrayList<>(), 
            new ArrayList<>(), 
            new ArrayList<>()
        );

        userList.add(newUser);

        //! Inserir aqui a função para enviar o email de boas vindas
        String assunto = "Cadastro realizado com sucesso!";
        String html = """
        <html>
            <body>
                <h2 style='color: green;'>Bem-vindo(a) usuário, """ + user.getName() + """
            </body>
        </html>
        """;

        try {
            emailService.sendHtmlEmail(user.getEmail(), assunto, html);
        } catch (Exception e) {
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
        }


        return newUser;
    }

    //Cadastra um organizador
    public OrganizerUser RegisterOganizerUser(RegisterOrganizerUserDTO user){
        
        ValidationUtils.verifyEmail(user.getEmail());

        if(user.getPassword().length() < 6){
            throw new InvalidArgumentException("Invalid password, minimum: 6 chars");
        }
        
        String formatedCPF = ValidationUtils.verifyAndFormatCPF(user.getCpf());

        if(AuthUtils.verifyExistentUser(userList, user.getEmail())){
            throw new UserAlreadyExistException("This email is already in use!");
        }

        OrganizerUser newOrganizer = new OrganizerUser(
            userList.size() + 1, 
            user.getName(), 
            formatedCPF, 
            user.getEmail(), 
            CryptoUtils.encryptPassword(user.getPassword()), 
            ImageUtils.saveProfilePic(user.getProfilePic()), 
            new ArrayList<>(), 
            user.getContact(),
            new ArrayList<>(), 
            new ArrayList<>(), 
            new ArrayList<>(), 
            new ArrayList<>()
        );
        
        userList.add(newOrganizer);

        String assunto = "Cadastro de organizador realizado!";
        String html = """
        <html>
            <body>
                <h2 style='color: blue;'>Olá, organizador """ + user.getName() + """
            </body>
        </html>
        """;

        try {
            emailService.sendHtmlEmail(user.getEmail(), assunto, html);
        } catch (Exception e) {
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
        }


        return newOrganizer;
    }

    // Acha um usuario pelo Id
    public User findUserById(int id){
        for(User user : userList){
            if(user.getId() == id){
                return user;
            }
        }
        throw new DataNotFoundException("User not found, invalid id!");
    }

    // Retorna lista de usuarios
    public List<User> viewUserList(){
        List<User> newList = new ArrayList<>();

        for(User user: userList){
            newList.add(user);
        }

        return newList;
    }

}
