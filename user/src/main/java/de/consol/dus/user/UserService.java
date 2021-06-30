package de.consol.dus.user;

import de.consol.dus.user.boundary.exceptions.UsernameOfUserCannotBeChangedException;
import de.consol.dus.user.boundary.messaging.jms.outgoing.NewColorEmitter;
import de.consol.dus.user.boundary.persistence.ColorRepository;
import de.consol.dus.user.boundary.persistence.UserRepository;
import de.consol.dus.user.boundary.transfer.request.CreateNewUserRequest;
import de.consol.dus.user.boundary.transfer.request.UpdateUserRequest;
import de.consol.dus.user.boundary.transfer.response.ColorResponse;
import de.consol.dus.user.boundary.transfer.response.UserResponse;
import de.consol.dus.user.entity.Color;
import de.consol.dus.user.entity.User;
import de.consol.dus.user.entity.mapper.ColorMapper;
import de.consol.dus.user.entity.mapper.UserMapper;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class UserService {
  private final UserMapper userMapper;
  private final ColorMapper colorMapper;
  private final UserRepository userRepository;
  private final ColorRepository colorRepository;
  private final NewColorEmitter newColorEmitter;

  public List<UserResponse> findAll() {
    return userRepository.findAll().stream()
        .map(userMapper::entityToResponse)
        .collect(Collectors.toList());
  }

  public Optional<UserResponse> findByUsername(String username) {
    return Optional.of(username)
        .flatMap(userRepository::findByUsername)
        .map(userMapper::entityToResponse);
  }

  @Transactional
  public UserResponse createNewUser(CreateNewUserRequest request) {
    final User newUser = userMapper.createNewUserRequestToEntity(request).toBuilder()
        .favoriteColor(getOrCreateColor(request.getFavoriteColor()))
        .build();
    final User saved = userRepository.save(newUser);
    return userMapper.entityToResponse(saved);
  }

  @Transactional
  public Optional<UserResponse> updateUser(String username, UpdateUserRequest request) {
    final String newUsername = request.getUsername();
    if (!Objects.equals(username, newUsername)) {
      throw UsernameOfUserCannotBeChangedException.of(username, newUsername);
    }
    final Color favoriteColor = getOrCreateColor(request.getFavoriteColor());
    return userRepository.findByUsername(username)
        .map(User::toBuilder)
        .map(builder -> builder.birthDate(request.getBirthDate()))
        .map(builder -> builder.favoriteColor(favoriteColor))
        .map(User.Builder::build)
        .map(userRepository::save)
        .map(userMapper::entityToResponse);
  }

  private Color getOrCreateColor(ColorResponse color) {
    return Optional.ofNullable(color)
        .map(newColorEmitter::emit)
        .map(ColorResponse::getName)
        .map(s -> colorRepository.findByName(s)
            .orElse(colorMapper.colorResponseToColor(color)))
        .map(colorRepository::save)
        .orElse(null);
  }
}
