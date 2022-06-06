package me.spring.ajaxse.beans;

public class UsersParser {
	public static UsersEntity parseUsersDTOtoEntity(UsersDTO usersDTO) {
		UsersEntity usersEntity = new UsersEntity();
		usersEntity.setUserName  (usersDTO.getUserName());
		usersEntity.setUserAge   (usersDTO.getUserAge());
		usersEntity.setUserGender(usersDTO.getUserGender());
		usersEntity.setUserEmail (usersDTO.getUserEmail());
		return usersEntity;
	}
	public static UsersDTO parseUsersEntitytoDTO(UsersEntity usersEntity) {
		UsersDTO usersDTO = new UsersDTO();
		usersDTO.setUserName  (usersEntity.getUserName());
		usersDTO.setUserAge   (usersEntity.getUserAge());
		usersDTO.setUserGender(usersEntity.getUserGender());
		usersDTO.setUserEmail (usersEntity.getUserEmail());
		return usersDTO;
	}
}
