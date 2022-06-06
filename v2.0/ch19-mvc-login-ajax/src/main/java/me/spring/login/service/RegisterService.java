package me.spring.login.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import me.spring.login.beans.RegisterDTO;
import me.spring.login.beans.RegisterEntity;
import me.spring.login.beans.RegisterParser;
import me.spring.login.dao.RegisterDao;

@Service
public class RegisterService {
	private final RegisterDao registerDao;

	@Autowired
	public RegisterService(RegisterDao registerDao) throws SQLException, DataAccessException {
		this.registerDao = registerDao;
	}

	public boolean signup(RegisterDTO register) throws SQLException, DataAccessException {
		// TODO DAO 들어가기 전에 id를 가진 사람이 이미 있으면 오류
		if(!canSignup(register)) return false;


		// TODO RegisterDTO -> RegisterEntity로 변환과정 필요
		RegisterEntity registerEntity = RegisterParser.parseRegisterDTOtoEntity(register);
		System.out.println(register);
		System.out.println(registerEntity);
		return registerDao.insert(registerEntity) > 0;
	}

	public RegisterDTO select(String id) throws SQLException, DataAccessException {
		// TODO DAO 들어가기 전에 id를 통해 조회할 수 있는지 확인
		if(!canSelect(id)) return null;


		// TODO RegisterEntity -> RegisterDTO로 변환과정 필요
		RegisterEntity registerEntity = registerDao.select(id);
		RegisterDTO registertDTO = RegisterParser.parseRegisterEntitytoDTO(registerEntity);
		System.out.println(registerEntity);
		System.out.println(registertDTO);
		return registertDTO;
	}

	public boolean update(RegisterDTO register) throws SQLException, DataAccessException {
		// TODO DAO 들어가기 전에 id를 통해 조회할 수 있는지 확인
		if(!canUpdate(register)) return false;


		// TODO RegisterDTO -> RegisterEntity로 변환과정 필요
		RegisterEntity registerEntity = RegisterParser.parseRegisterDTOtoEntity(register);
		System.out.println(register);
		System.out.println(registerEntity);
		return registerDao.update(registerEntity) > 0;
	}

	public boolean delete(String id) throws SQLException, DataAccessException {
		// TODO DAO 들어가기 전에 id를 통해 조회할 수 있는지 확인
		if(!canDelete(id)) return false;


		// TODO RegisterDTO -> RegisterEntity로 변환과정 필요
		return registerDao.delete(id) > 0;
	}

	public List<RegisterDTO> selectAll() throws SQLException, DataAccessException {
		List<RegisterDTO> registerDTOs = new Vector<>();
		List<RegisterEntity> registerEntitys = registerDao.selectAll();
		for(RegisterEntity registerEntity : registerEntitys) {
			registerDTOs.add(RegisterParser.parseRegisterEntitytoDTO(registerEntity));
		}
		return registerDTOs;
	}


	////////////////////////////////////////////////////////////////////
	/////////////////////////////// 예외 처리 ////////////////////////////
	///////////////////////  DAO에 try-catch 없애기 //////////////////////
	public boolean canSelect(String id) throws SQLException, DataAccessException {
		List<RegisterEntity> registerEntitys = registerDao.selectAll();
		boolean isMember = false;
		for(RegisterEntity registerEntity : registerEntitys) {
			if(registerEntity.getId() != null && registerEntity.getId().equals(id)) {
				isMember = true;
				break;
			}
		}
		return isMember;
	}

	public boolean canSignup(RegisterDTO register) throws SQLException, DataAccessException {
		RegisterEntity registerEntityTest = registerDao.select(register.getId());
		if(registerEntityTest != null) {
			return false;
		}
		return true;
	}

	public boolean canUpdate(RegisterDTO register) throws SQLException, DataAccessException {
		RegisterEntity registerEntityTest = registerDao.select(register.getId());
		// TODO DAO 들어가기 전에 id의 curpwd와 DB에서 select한 curpwd가 다를 경우
		// SQL에서는 update register set pwd=?, name=?, email=?, phone=? where id=?
		if((registerEntityTest == null) || registerEntityTest.getPwd() == null || 
				!registerEntityTest.getPwd().equals(register.getCurpwd())) {
			return false;
		}
		return true;
	}

	public boolean canDelete(String id) throws SQLException, DataAccessException {
		RegisterEntity registerEntityTest = registerDao.select(id);
		if(registerEntityTest == null) {
			return false;
		}
		return true;
	}
}
