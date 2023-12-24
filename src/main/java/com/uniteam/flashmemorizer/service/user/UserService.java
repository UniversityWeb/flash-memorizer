package com.uniteam.flashmemorizer.service.user;

import com.uniteam.flashmemorizer.dto.UserDTO;
import com.uniteam.flashmemorizer.entity.VerificationToken;
import com.uniteam.flashmemorizer.form.ChangePassForm;
import com.uniteam.flashmemorizer.record.RegistrationRequest;

import java.util.List;

public interface UserService {
    UserDTO add(UserDTO userDTO);
    UserDTO updateNotPassword(UserDTO userDTO);
    List<UserDTO> getUsers();
    UserDTO findByEmail(String email);
    UserDTO findByUsername(String username);
    Long getCurrentUserId();
    UserDTO registerUser(RegistrationRequest request);
    boolean isExistsEmail(String email);
    boolean isExistUsername(String username);
    boolean passwordNotMatch(String password, String passwordConfirm);
    void saveUserVerifycationToken(UserDTO theUser, String token);
    String validateToken(String theToken);
    VerificationToken generateNewVerificationCode(String oldToken);
    UserDTO getById(Long id);
    boolean delete(Long userId);
    boolean changePassword(ChangePassForm passForm);
    void deleteUnverifiedUsers();
}