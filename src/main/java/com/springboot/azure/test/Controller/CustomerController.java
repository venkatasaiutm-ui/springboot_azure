package com.springboot.azure.test.Controller;

import com.springboot.azure.test.DTO.UserRequestDTO;
import com.springboot.azure.test.DTO.UserResponseDTO;
import com.springboot.azure.test.Entity.User;
import com.springboot.azure.test.Repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class CustomerController {

    private final UserRepository userRepository;

    // CREATE
    @PostMapping
    public ResponseEntity<UserResponseDTO> create(
            @Valid @RequestBody UserRequestDTO dto) {

        User user = new User(
                null,
                dto.getName(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getPhoneNumber()
        );

        user = userRepository.save(user);

        return ResponseEntity.ok(mapToResponse(user));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return ResponseEntity.ok(
                userRepository.findAll()
                        .stream()
                        .map(this::mapToResponse)
                        .toList()
        );
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(mapToResponse(user));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAddress(dto.getAddress());
        user.setPhoneNumber(dto.getPhoneNumber());

        return ResponseEntity.ok(mapToResponse(userRepository.save(user)));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private UserResponseDTO mapToResponse(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAddress(),
                user.getPhoneNumber()
        );
    }
}
