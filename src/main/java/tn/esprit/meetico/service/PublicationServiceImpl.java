package tn.esprit.meetico.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.esprit.meetico.entity.Comment;
import tn.esprit.meetico.entity.PostDislike;
import tn.esprit.meetico.entity.PostLike;
import tn.esprit.meetico.entity.Publication;
import tn.esprit.meetico.entity.User;
import tn.esprit.meetico.repository.DislikeRepository;
import tn.esprit.meetico.repository.LikeRepository;
import tn.esprit.meetico.repository.PublicationRepository;
import tn.esprit.meetico.repository.UserRepository;



@Service
public class PublicationServiceImpl implements IPublicationService {
@Autowired
PublicationRepository publicationrepo ;
@Autowired
UserRepository utiRepo ;
@Autowired
LikeRepository likeRepo;
@Autowired
DislikeRepository dislikeRepo;



public void addPublication(Publication publication) {
	publicationrepo.save(publication);
}

public void deletePublication(Long idPublication) {
	publicationrepo.deleteById(idPublication);	
}





/*
public void createLike(Long postId, Long idUser) {
	Publication  publication=publicationrepo.findById(postId).orElse(null);	
	Utilisateur  user=utiRepo.findById(idUser).orElse(null);
    
	
	publication.getLikes().add(user);
    
    publicationrepo.save(publication);
}
*/








public void addLike(Long idPublicaiton,Long idUser){
PostLike lk =    new PostLike();	
	Publication  publication=publicationrepo.findById(idPublicaiton).orElse(null);	
	User  user=utiRepo.findById(idUser).orElse(null);	
	lk.setPublication(publication);
	lk.setUtilis(user);

	
	
	likeRepo.save(lk);
		
	
}


	



public void addDisLike(Long idPublicaiton,Long idUser){
PostDislike lk =    new PostDislike();	
	Publication  publication=publicationrepo.findById(idPublicaiton).orElse(null);	
	User  user=utiRepo.findById(idUser).orElse(null);	
	lk.setPublication(publication);
	lk.setUtilis(user);
	dislikeRepo.save(lk);
}

public int nbrLikeByPub(Long idPublicaiton) {
	Publication  publication=publicationrepo.findById(idPublicaiton).orElse(null);	
return	 publication.getLikes().size();
}

public int nbrDisLikeByPub(Long idPublicaiton) {
	Publication  publication=publicationrepo.findById(idPublicaiton).orElse(null);	
return	 publication.getDislikes().size();
}   


public int nbrCommentsByPu(Long idPublicaiton) {
	Publication  publication=publicationrepo.findById(idPublicaiton).orElse(null);		
	return	 publication.getComments().size();	
}















}
