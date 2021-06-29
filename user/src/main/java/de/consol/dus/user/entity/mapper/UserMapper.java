package de.consol.dus.user.entity.mapper;

import de.consol.dus.user.boundary.transfer.request.CreateNewUserRequest;
import de.consol.dus.user.boundary.transfer.response.UserResponse;
import de.consol.dus.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi", uses = ColorMapper.class)
public interface UserMapper {
  User createNewUserRequestToEntity(CreateNewUserRequest request);

  UserResponse entityToResponse(User user);
}
