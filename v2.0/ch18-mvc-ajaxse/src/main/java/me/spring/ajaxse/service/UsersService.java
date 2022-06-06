package me.spring.ajaxse.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import me.spring.ajaxse.beans.UsersDTO;
import me.spring.ajaxse.beans.UsersEntity;
import me.spring.ajaxse.beans.UsersParser;
import me.spring.ajaxse.controller.UsersController;
import me.spring.ajaxse.dao.UsersDao;

@Service
public class UsersService implements IUsersService  {
	private final Logger logger = LoggerFactory.getLogger(UsersController.class);
	private final UsersDao usersDao;

	@Autowired
	public UsersService(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String select(String keyword) throws SQLException, DataAccessException {
		if(keyword == null) keyword = "";
		// select * from users where userName = ?
		// null이 포함된 userName을 구할 수 없음

		List<UsersEntity> usersEntities = usersDao.select(keyword);
		if(usersEntities == null) return null;

		// UsersEntity -> UsersDTO로 변환
		List<UsersDTO> usersDTOs = new Vector<>();
		for(UsersEntity usersEntity : usersEntities) {
			usersDTOs.add(UsersParser.parseUsersEntitytoDTO(usersEntity));
		}

		// select 결과를 JSON 형태로 반환
		JSONArray jsonArray = new JSONArray();
		for(UsersDTO userDTO : usersDTOs) {
			Map<String, String> map = new HashMap<>();
			map.put("userName",   userDTO.getUserName());
            map.put("userAge",    userDTO.getUserAge() + "");
            map.put("userGender", userDTO.getUserGender());
            map.put("userEmail",  userDTO.getUserEmail());

            JSONObject jsonObject = new JSONObject(map);
            jsonArray.add(jsonObject);
		}

		return jsonArray.toJSONString();
	}
	@Override
	public int insert(UsersDTO user) throws SQLException, DataAccessException {
		// UsersDTO -> UsersEntity로 변환
		UsersEntity usersEntity = new UsersEntity();
		usersEntity = UsersParser.parseUsersDTOtoEntity(user);
		return usersDao.insert(usersEntity);
	}


}
