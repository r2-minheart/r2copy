package com.r2comms.r2copy.service;

import com.r2comms.r2copy.dto.UserResponseDto;
import com.r2comms.r2copy.entity.User;
import com.r2comms.r2copy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto getUserByLoginId(String loginId) {
        Optional<User> result = userRepository.findByLoginId(loginId);
        return result.isPresent() ? new UserResponseDto(result.get()): null;
    }

    public void updateLastLoginTime(String loginId) {
        Optional<User> result = userRepository.findByLoginId(loginId);
        if (result.isPresent()) {
            User user = result.get();
            user.updateLastLoginTime(LocalDateTime.now());
            userRepository.save(user);
        }
    }

	public List<UserResponseDto> getList() {
		return userRepository.findAll(Sort.by(Sort.Direction.DESC, "userId"))
				.stream()
				.map(entity -> new UserResponseDto(entity))
				.collect(Collectors.toList());	
	}
}
