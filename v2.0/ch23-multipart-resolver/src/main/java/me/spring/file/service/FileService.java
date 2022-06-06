package me.spring.file.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import me.spring.file.beans.FileDTO;
import me.spring.file.beans.FileEntity;
import me.spring.file.beans.FileParser;
import me.spring.file.dao.FileDao;

@Service
public class FileService implements IFileService {
	private final FileDao fileDao;

	@Autowired
	public FileService(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Override
	public boolean insert(FileDTO file) throws SQLException, DataAccessException {
		// TODO DAO 들어가기 전에 동일한 fno를 가진 사람이 이미 있으면 오류
		if(!canInsert(file)) return false;
		
		
		// TODO Auto-generated method stub
		FileEntity fileEntity = FileParser.parseFileDTOtoEntity(file);
		System.out.println(file);
		System.out.println(fileEntity);
		return fileDao.insert(fileEntity) > 0;
	}

	@Override
	public FileDTO select(String fno) throws SQLException, DataAccessException {
		// TODO DAO 들어가기 전에 fno를 통해 조회할 수 있는지 확인
		if(!canSelect(fno)) return null;		
		
		// TODO Auto-generated method stub
		FileEntity fileEntity = fileDao.select(fno);
		FileDTO fileDTO = FileParser.parseFileEntitytoDTO(fileEntity);
		return fileDTO;
	}

	@Override
	public boolean update(FileDTO file) throws SQLException, DataAccessException {
		// TODO DAO 들어가기 전에 fno를 통해 조회할 수 있는지 확인
		if(!canUpdate(file)) return false;		
				
		
		// TODO Auto-generated method stub
		FileEntity fileEntity = FileParser.parseFileDTOtoEntity(file);
		return fileDao.update(fileEntity) > 0;
	}

	@Override
	public boolean delete(String fno) throws SQLException, DataAccessException {
		// TODO DAO 들어가기 전에 fno를 통해 조회할 수 있는지 확인
		if(!canDelete(fno)) return false;	
		
		// TODO Auto-generated method stub
		return fileDao.delete(fno) > 0;
	}

	@Override
	public List<FileDTO> selectAll() throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		List<FileDTO> fileDTOs = new Vector<>();
		List<FileEntity> fileEntities = fileDao.selectAll();
		for(FileEntity fileEntity : fileEntities) {
			fileDTOs.add(FileParser.parseFileEntitytoDTO(fileEntity));
		}
		return fileDTOs;
	}

	////////////////////////////////////////////////////////////////////
	/////////////////////////////// 예외 처리 ////////////////////////////
	///////////////////////  DAO에 try-catch 없애기 //////////////////////
	public boolean canSelect(String fno) throws SQLException, DataAccessException {
		if(fno == null) return false;
		
		List<FileEntity> fileEntities = fileDao.selectAll();
		boolean isFile = false;
		for (FileEntity fileEntity : fileEntities) {
			if (fileEntity.getFno() != null && fileEntity.getFno().equals(fno)) {
				isFile = true;
				break;
			}
		}
		return isFile;
	}

	public boolean canInsert(FileDTO file) throws SQLException, DataAccessException {
		if(file == null) return false;
		
		FileEntity fileEntity = fileDao.select(file.getFno());
		if (fileEntity != null) {
			return false;
		}
		return true;
	}

	public boolean canUpdate(FileDTO file) throws SQLException, DataAccessException {
		if(file == null || file.getFno() == null) return false;
		
		FileEntity fileEntity = fileDao.select(file.getFno());
		if(fileEntity != null) {
			return false;
		}
		return true;
	}

	public boolean canDelete(String fno) throws SQLException, DataAccessException {
		if(fno == null) return false;
		
		FileEntity fileEntity = fileDao.select(fno);
		if(fileEntity != null) {
			return false;
		}
		return true;
	}

}
