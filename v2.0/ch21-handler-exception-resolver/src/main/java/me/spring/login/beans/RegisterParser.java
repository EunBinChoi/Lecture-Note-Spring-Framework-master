package me.spring.login.beans;

public class RegisterParser {
	// TODO RegisterDTO    -> RegisterEntity
	public static RegisterEntity parseRegisterDTOtoEntity(RegisterDTO registerDTO) {
		// UPDATE에서 접근 (curpwd, newpwd, newrepwd)
		if(registerDTO.getNewpwd() != null && !registerDTO.getNewpwd().equals("")) {
			return new RegisterEntity(registerDTO.getId(), registerDTO.getNewpwd(),
					registerDTO.getName(), registerDTO.getEmail(), registerDTO.getPhone());
		}
		// SIGNUP에서 접근 (curpwd, currepwd)
		else {
			return new RegisterEntity(registerDTO.getId(), registerDTO.getCurpwd(),
					registerDTO.getName(), registerDTO.getEmail(), registerDTO.getPhone());
		}
	}

	// TODO RegisterEntity -> RegisterDTO
	public static RegisterDTO parseRegisterEntitytoDTO(RegisterEntity registerEntity) {
		RegisterDTO registerDTO = new RegisterDTO();
		registerDTO.setId(registerEntity.getId());
		registerDTO.setCurpwd(registerEntity.getPwd());
		registerDTO.setName(registerEntity.getName());
		registerDTO.setEmail(registerEntity.getEmail());
		registerDTO.setPhone(registerEntity.getPhone());
		return registerDTO;
	}


}
