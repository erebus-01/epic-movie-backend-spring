package com.programing.movie.user.impl;

import com.programing.movie.user.config.JwtService;
import com.programing.movie.user.dto.*;
import com.programing.movie.user.model.ConfirmationCodesNumber;
import com.programing.movie.user.model.ConfirmationEmail;
import com.programing.movie.user.model.Role;
import com.programing.movie.user.model.User;
import com.programing.movie.user.repository.ConfirmationCodesNumberRepository;
import com.programing.movie.user.repository.ConfirmationEmailRepository;
import com.programing.movie.user.repository.UserRepository;
import com.programing.movie.user.service.UserService;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ConfirmationEmailRepository confirmationRepository;
    private final ConfirmationCodesNumberRepository confirmationCodesNumberRepository;
    private final EmailServiceImpl emailService;

    @Override
    @Transactional
    public AuthenticationResponse save(UserRequest request) throws MessagingException, TemplateException, IOException {
        String email = request.getEmail();

        User existingUser = repository.findByEmail(email).orElse(null);

        if (existingUser != null) {
            var jwtToken = jwtService.generatorToken(existingUser);
            return AuthenticationResponse.builder().token(jwtToken).build();
        }

        User newUser = createUserFromRequest(request);
        repository.save(newUser);

        ConfirmationEmail confirmation = createConfirmationEmail(newUser);
        confirmationRepository.save(confirmation);

        emailService.sendHtmlEmailService(newUser.getFirstname(), newUser.getEmail(), confirmation.getToken());

        var jwtToken = jwtService.generatorToken(newUser);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public String changeEmail(UUID userId, String newEmail) {
        Optional<User> optionalUser = repository.findById(userId);



        return null;
    }

    @Override
    public String updatePassword(UUID userId, UUID verifiedID, PasswordRequestDto newPassword) {

        if(verifiedID == null) return "Có vẻ như bạn không có quyền thay đổi mật khẩu của người dùng này.";

        Optional<User> userOptional = repository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            Optional<ConfirmationCodesNumber> codesNumber = confirmationCodesNumberRepository.findByUser(user);
            if(codesNumber.isPresent()) {
                ConfirmationCodesNumber number = codesNumber.get();
                if(number.getVerifiedUUID().equals(verifiedID)) {
                    user.setPassword(passwordEncoder.encode(newPassword.getPassword()));
                    repository.save(user);

                    confirmationCodesNumberRepository.delete(number);

                    return "Cập nhật mật khẩu thành công cho người dùng.";
                }
                return "Không có quyền để thay đổi mật khẩu người dùng này.";
            }
        }

        return "Opps!!! Không tìm thấy người dùng.";
    }

    @Override
    @Transactional
    public String updateVerification(UUID userId, Boolean newVerification) {
        Optional<User> optionalUser = repository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setVerification(newVerification);
            repository.save(user);

            return "Cập nhật trạng thái xác thức thành công";
        } else {
            return "Chúng tôi không tìm thấy người dùng mà bạn yêu cầu";
        }
    }

    @Override
    public Boolean verifyToken(String token) {
        ConfirmationEmail confirmationEmail = confirmationRepository.findByToken(token);

        if(confirmationEmail == null) return Boolean.FALSE;

        LocalDateTime expirationTime = confirmationEmail.getCreateDate().plusMinutes(5);
        LocalDateTime currentTime = LocalDateTime.now();

        if(currentTime.isAfter(expirationTime)) {
            confirmationRepository.delete(confirmationEmail);
            return Boolean.FALSE;
        }

        User user = repository.findByEmailIgnoreCase(confirmationEmail.getUser().getEmail());
        user.setVerification(true);
        repository.save(user);
        confirmationRepository.delete(confirmationEmail);

        return Boolean.TRUE;
    }

    @Override
    public AuthenticationResponse authentication(AuthenticationRequest request) {
        log.info("Ready to login with email: {} and password: {}", request.getEmail(), request.getPassword());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            var user = repository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng."));

            if (user.isVerification()) {
                log.info("User {} logged in to the system.", user);

                var jwtToken = jwtService.generatorToken(user);

                return AuthenticationResponse.builder().token(jwtToken).build();
            } else {
                log.error("Người dùng {} chưa được xác thực. Xác thực người dùng thất bại.", user.getEmail());
                throw new RuntimeException("Người dùng chưa được xác minh.");
            }
        } catch (AuthenticationException e) {
            log.error("Authentication failed: {}", e.getMessage());
            throw new RuntimeException("Xác thực thất bại.");
        }
    }

    @Override
    public String sendEmailCodesNumber(UUID userId) throws MessagingException, TemplateException, IOException {
        Optional<User> userOptional = repository.findById(userId);

        if(userOptional.isPresent()) {

            User user = userOptional.get();

            Optional<ConfirmationCodesNumber> confirmationCodesNumberOptional = confirmationCodesNumberRepository.findByUser(user);
            confirmationCodesNumberOptional.ifPresent(confirmationCodesNumberRepository::delete);

            ConfirmationCodesNumber codesNumber = new ConfirmationCodesNumber(user);
            confirmationCodesNumberRepository.save(codesNumber);

            emailService.sendHtmlEmailNumberService(user.getFirstname(), user.getEmail(), codesNumber.getToken());

            return "Mã định danh đã được gửi tới email: " + user.getEmail() + ". Mã này có hiệu lực trong vòng 5p";
        }

        return "Opps!!! Có vẻ như email của bạn chưa được đăng ký trong hệ thống của chúng tôi";
    }

    @Override
    public boolean verifyVerificationCode(UUID userId, String enteredCode) {
        Optional<User> userOptional = repository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<ConfirmationCodesNumber> codesNumberOptional = confirmationCodesNumberRepository.findByUser(user);

            if (codesNumberOptional.isPresent()) {
                ConfirmationCodesNumber codesNumber = codesNumberOptional.get();

                if (enteredCode.equals(codesNumber.getToken())) {
                    LocalDateTime expirationTime = codesNumber.getCreateDate().plusMinutes(5);
                    LocalDateTime currentTime = LocalDateTime.now();

                    if (currentTime.isBefore(expirationTime)) {
                        UUID verifiedUUID = UUID.randomUUID();
                        codesNumber.setVerifiedUUID(verifiedUUID);
                        confirmationCodesNumberRepository.save(codesNumber);

                        return true;
                    }
                }
            }
        }
        return false;
    }



    private User createUserFromRequest(UserRequest request) {
        Set<Role> roles = Set.of(Role.ROLE_USER);
        return User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .dateOfBirth(request.getDateOfBirth())
                .verification(false)
                .build();
    }

    private ConfirmationEmail createConfirmationEmail(User user) {
        return new ConfirmationEmail(user);
    }

    private UserResponse mapToUserResponse(User request) {
        return UserResponse.builder()
                .id(request.getId())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .verification(request.isVerification())
                .createAt(request.getCreateAt())
                .updateAt(request.getUpdateAt())
                .build();
    }

}
