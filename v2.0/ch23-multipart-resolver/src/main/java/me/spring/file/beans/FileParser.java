package me.spring.file.beans;

import java.util.List;
import java.util.Vector;

public class FileParser {
	// TODO fileDTO -> fileEntity
	public static FileEntity parseFileDTOtoEntity(FileDTO fileDTO) {
		if(fileDTO == null) return null;
		
		FileEntity fileEntity = new FileEntity();
		fileEntity.setFno(fileDTO.getFno());
		fileEntity.setUserName(fileDTO.getUserName());
		fileEntity.setTitle(fileDTO.getTitle());
		fileEntity.setContent(fileDTO.getContent());
		fileEntity.setOriFileNames(parseOriginalFileNameDTOtoEntity(fileDTO));
		fileEntity.setStoredFileNames(parseStoredFileNameDTOtoEntity(fileDTO));
		fileEntity.setfSeperator("|");
		fileEntity.setfCount(fileDTO.getFileRealCount() + "");
		fileEntity.setfType(parseFileTypeDTOtoEntity(fileDTO));
		fileEntity.setfSize(parseFileSizeDTOtoEntity(fileDTO));
		
		return fileEntity;
	}
	
	
	// TODO fileEntity -> fileDTO
	public static FileDTO parseFileEntitytoDTO(FileEntity fileEntity) throws NumberFormatException {
		if(fileEntity == null) return null;
		
		FileDTO fileDTO = new FileDTO();
		fileDTO.setFno(fileEntity.getFno());
		fileDTO.setUserName(fileEntity.getUserName());
		fileDTO.setTitle(fileEntity.getTitle());
		fileDTO.setContent(fileEntity.getContent());
		fileDTO.setFileSeperator(fileEntity.getfSeperator());
		fileDTO.setFileRealCount(Integer.parseInt(fileEntity.getfCount()));

		List<FileInfo> fileInfos = new Vector<>();		
		List<String> oriFileNames    =  List.of(fileEntity.getOriFileNames().split(fileEntity.getfSeperator()));
		List<String> storedFileNames =  List.of(fileEntity.getStoredFileNames().split(fileEntity.getfSeperator()));
		List<String> fileTypes =  List.of(fileEntity.getfType().split(fileEntity.getfSeperator()));
		List<String> fileSizes =  List.of(fileEntity.getfSize().split(fileEntity.getfSeperator()));
		
		System.out.println(fileDTO.getFileRealCount());
		System.out.println(oriFileNames.size());
		System.out.println(storedFileNames.size());
		System.out.println(fileTypes.size());
		System.out.println(fileSizes.size());
	
		
		for(int i = 0; i < fileDTO.getFileRealCount(); i++) {
			FileInfo FileInfo = new FileInfo();
			FileInfo.setOriginalFileName(oriFileNames.get(i));
			FileInfo.setStoredFileName(storedFileNames.get(i));
			FileInfo.setfType(fileTypes.get(i));
			FileInfo.setfSize(Long.parseLong(fileSizes.get(i)));
			fileInfos.add(FileInfo);
		}
		fileDTO.setFileInfos(fileInfos);
		
		
		return fileDTO;
		
	}
	
	public static String parseOriginalFileNameDTOtoEntity(FileDTO fileDTO) {
		List<FileInfo> fileInfos =  fileDTO.getFileInfos();
		String originalFileNamesDB  = "";
		
		for(FileInfo fileInfo : fileInfos) {
			originalFileNamesDB += fileInfo.getOriginalFileName() + "|";
		}
		return originalFileNamesDB.substring(0, originalFileNamesDB.length()-1);
	}
	
	public static String parseStoredFileNameDTOtoEntity(FileDTO fileDTO) {
		List<FileInfo> fileInfos =  fileDTO.getFileInfos();
		String storedFileNamesDB  = "";
		
		for(FileInfo fileInfo : fileInfos) {
			storedFileNamesDB += fileInfo.getStoredFileName() + "|";
		}
		return storedFileNamesDB.substring(0, storedFileNamesDB.length()-1);
	}
	
	public static String parseFileTypeDTOtoEntity(FileDTO fileDTO) {
		List<FileInfo> fileInfos =  fileDTO.getFileInfos();
		String fileTypesDB  = "";
		
		for(FileInfo fileInfo : fileInfos) {
			fileTypesDB += fileInfo.getfType() + "|";
		}
		return fileTypesDB.substring(0, fileTypesDB.length()-1);
	}
	
	public static String parseFileSizeDTOtoEntity(FileDTO fileDTO) {
		List<FileInfo> fileInfos =  fileDTO.getFileInfos();
		String fileSizesDB  = "";
		
		for(FileInfo fileInfo : fileInfos) {
			fileSizesDB += fileInfo.getfSize() + "|";
		}
		return fileSizesDB.substring(0, fileSizesDB.length()-1);
	}
}
