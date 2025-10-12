package com.example.tpapiarticle.service;


import com.example.tpapiarticle.entity.User;
import com.example.tpapiarticle.repository.UserRepository;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean isValidEmail(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            return true;
        } catch (AddressException e) {
            return false;
        }
    }

    public boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("^\\d{10}$");
    }
    public boolean isValidCityCode(String cityCode) {
        return cityCode != null && cityCode.matches("^\\d{5}$");
    }

    public String generateNewPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        java.util.Random random = new java.util.Random();
        
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }
        
        return password.toString();
    }
    
    public ResponseDTO<User> login(User user) {
        ResponseDTO<User> userResponseDTO = new ResponseDTO<>();
        User existingUser = userRepository.findByEmail(user.email);
        
        if (UserLoginCheckFields.checkFields(user) || !isValidEmail(user.email)) {
            User userMissingFields = new User();
            if (user.email == null || user.email.trim().isEmpty()) {
                userMissingFields.email = "L'email est manquante";
            }
            if (user.password == null || user.password.trim().isEmpty()) {
                userMissingFields.password = "Le mot de passe est manquant";
            }
            if (!isValidEmail(user.email)) {
                userMissingFields.email = "L'email est n'est pas une url valide";
            }
            userResponseDTO.code = 710;
            userResponseDTO.message = "Contrôle de surface non valide";
            userResponseDTO.data = userMissingFields;

        } else if (existingUser == null || !existingUser.password.equals(user.password)) {
            userResponseDTO.code = 702;
            userResponseDTO.message = "Email ou mot de passe incorect";
            userResponseDTO.data = null;

        } else {
            userResponseDTO.code = 200;
            userResponseDTO.message = "Utilisateur récupéré avec succès";
        }

        return userResponseDTO;
    }


    public ResponseDTO<User> signup(User user) {
        ResponseDTO<User> userResponseDTO = new ResponseDTO<>();
        User existingUser = userRepository.findByEmail(user.email);
        User userMissingFields = new User();

        if (UserSignupCheckFields.checkFields(user) || !isValidEmail(user.email) || !isValidPhoneNumber(user.phone) || !isValidCityCode(user.cityCode)) {
            if (user.email == null || user.email.trim().isEmpty()) {
                userMissingFields.email = "L'email est manquante";
            }
            if (!isValidEmail(user.email)) {
                userMissingFields.email = "L'email n'est pas valide";
            }
            if (user.password == null || user.password.trim().isEmpty()) {
                userMissingFields.password = "Le mot de passe est manquant";
            }
            if (user.pseudo == null || user.pseudo.trim().isEmpty()) {
                userMissingFields.pseudo = "Le pseudo est manquant";
            }
            if (user.city == null || user.city.trim().isEmpty()) {
                userMissingFields.city = "La ville est manquante";
            }
            if (user.cityCode == null || user.cityCode.trim().isEmpty()) {
                userMissingFields.cityCode = "Le code postal est manquant";
            }
            if (!isValidCityCode(user.cityCode)) {
                userMissingFields.cityCode = "Le code postal doit contenir exactement 5 chiffres";
            }
            if (user.phone == null || user.phone.trim().isEmpty()) {
                userMissingFields.phone = "Le numéro de téléphone est manquant";
            }
            if (!isValidPhoneNumber(user.phone)) {
                userMissingFields.phone = "Le numéro de téléphone doit contenir exactement 10 chiffres";
            }

            userResponseDTO.code = 710;
            userResponseDTO.message = "Contrôle de surface non valide";
            userResponseDTO.data = userMissingFields;

        } else if (existingUser != null) {
            userResponseDTO.code = 701;
            userResponseDTO.message = "Un utilisateur existe déjà avec cet email, faites mot de passe oublié pour réinitialiser votre mot de passe";
            userResponseDTO.data = null;

        } else {
            userResponseDTO.code = 200;
            userResponseDTO.message = "Utilisateur créé avec succès";
            userResponseDTO.data = user;
            userRepository.save(user);
        }

        return userResponseDTO;
    }

    public ResponseDTO resetPassword(User user) {
        ResponseDTO userResponseDTO = new ResponseDTO<>();
        User existingUser = userRepository.findByEmail(user.email);
        User userMissingFields = new User();
        if (UserResetPasswordCheckFields.checkFields(user) || !isValidEmail(user.email)) {
            if (user.email == null || user.email.trim().isEmpty()) {
                userMissingFields.email = "L'email est manquante";
            }
            if (!isValidEmail(user.email)) {
                userMissingFields.email = "L'email n'est pas valide";
            }
            userResponseDTO.code = 710;
            userResponseDTO.message = "Contrôle de surface non valide";
            userResponseDTO.data = userMissingFields;

        } else if (existingUser == null) {
            userResponseDTO.code = 702;
            userResponseDTO.message = "Impossible de réinitialiser le mot de passe de l'utilisateur car l'email n'existe pas";
            userResponseDTO.data = null;
        } else {
            existingUser.password = generateNewPassword();
            userResponseDTO.code = 200;
            userResponseDTO.message = "Mot de passe réinitialisé avec succès";
            userResponseDTO.data = existingUser.password;
            userRepository.save(existingUser);
        }
        return userResponseDTO;
    }

}
