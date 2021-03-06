package uz.ds.app_word_file.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uz.ds.app_word_file.entity.User;
import uz.ds.app_word_file.payload.ApiResponse;
import uz.ds.app_word_file.payload.ReqLogin;
import uz.ds.app_word_file.payload.ReqUser;
import uz.ds.app_word_file.payload.ResToken;
import uz.ds.app_word_file.repository.UserRepository;
import uz.ds.app_word_file.security.JwtTokenProvider;
import uz.ds.app_word_file.service.AuthService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody ReqLogin reqLogin) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                reqLogin.getPhoneNumber(),
                reqLogin.getPassword()
        ));
        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new ResToken(token));
    }

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody ReqUser reqUser ) {
        ApiResponse response = authService.addRegister(reqUser);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @GetMapping("/lists")
    public HttpEntity<?> getUsers() {
        List<User> userList = authService.getUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getUser(@PathVariable UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getUser"));
        return ResponseEntity.ok(user);
    }
}
