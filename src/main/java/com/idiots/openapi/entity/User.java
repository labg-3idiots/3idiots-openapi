package com.idiots.openapi.entity;

import com.idiots.openapi.exception.Exception400;
import com.idiots.openapi.exception.user.UserExceptionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "user")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column(length = 100)
    private String name;
    @Column(length = 100)
    private String password;
    @Column(length = 20)
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'INACTIVE'")
    private UserStatus status = UserStatus.INACTIVE;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();
    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();
    private LocalDateTime lastLogin;


    @Builder
    public User(Long id, String email, String name, String password, String phoneNumber, UserRole role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    // 본인인증 완료 후 수행할 method
    public void userVerified() {
        this.status = UserStatus.ACTIVE;
    }

    // 유저 삭제 시 검증할 method
    public void userDelete() {
        if (this.status == UserStatus.DELETE) {
            throw new Exception400(UserExceptionStatus.USER_ALREADY_DELETED);
        }
        this.status = UserStatus.DELETE;
    }

    public User hashPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

}
