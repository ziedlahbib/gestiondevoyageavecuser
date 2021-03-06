package tn.esprit.meetico.service;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.meetico.entity.FileDB;
import tn.esprit.meetico.entity.Trip;
import tn.esprit.meetico.repository.FileDBRepository;
import tn.esprit.meetico.repository.TripRepository;

@Service
public class FileStorageService {
	Long idf;
  @Autowired
  private FileDBRepository fileDBRepo;
  @Autowired
  TripRepository tripRepo;
  public FileDB store(MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
    return fileDBRepo.save(FileDB);
  }public Long store1(MultipartFile file) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
	    fileDBRepo.save(FileDB);
	    return FileDB.getId();
	  }
  public void deletefile(Long idfile) {
	  FileDB f =fileDBRepo.findById(idfile).orElse(null);
	  f.setTrip(null);
	  fileDBRepo.delete(f);
  }
  public FileDB getFile(Long id) {
    return fileDBRepo.findById(id).orElse(null);
  }
  public Trip gettripbyfile(Long id) {
	  FileDB f = fileDBRepo.findById(id).orElse(null);
	  return f.getTrip();
  }
  public FileDB getFiletime(Integer id) {
	    Trip t = tripRepo.findById(id).orElse(null);
	     
	    	Timer timer = new Timer();
	    	TimerTask task = new TimerTask() {
	            public void run() {
	               for(FileDB f :t.getFiles())
	               {
	            	   idf= f.getId();
	               }
	            }
	        };
	         
	        timer.schedule(task, 50, 50);
			return getFile(idf);
	    
	  }
  
  public Stream<FileDB> getAllFiles() {
    return fileDBRepo.findAll().stream();
  }
  public Stream<FileDB> getAllFilesBytrip(Integer id) {
	  Trip t =tripRepo.findById(id).orElse(null);
	    return t.getFiles().stream();
	  }
  public List<FileDB> getFileByTrip(Integer id) {
	  Trip t =tripRepo.findById(id).orElse(null);
	    return t.getFiles();
	  }
}