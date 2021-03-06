package tn.esprit.meetico.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.Module;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.meetico.entity.Comment;
import tn.esprit.meetico.entity.DictionnaireBadWords;
import tn.esprit.meetico.entity.PostLike;
import tn.esprit.meetico.entity.Publication;
import tn.esprit.meetico.entity.User;
import tn.esprit.meetico.repository.CommentRepository;
import tn.esprit.meetico.repository.DictionnaireRepository;
import tn.esprit.meetico.repository.PublicationRepository;
import tn.esprit.meetico.repository.UserRepository;



@Service
public class CommentServiceImpl implements ICommentService {
	@Autowired
	CommentRepository commentRepo;
	@Autowired
	PublicationRepository publciationRepo;
	@Autowired
	DictionnaireRepository badwordsRepo;
	@Autowired
	UserRepository userRepo;

	
	/////////////// Partie Back Admin////////
	@Override
	public List<Comment> ListAllCommentsAdmin(Long idPublicaiton) {
		return commentRepo.findAll();
	}
	
	
	
	
	
	
	
	
	
	//////////////////////Partie Front Client/////////////////
	@Override
	public boolean verif(Comment c) {

		for (DictionnaireBadWords d : badwordsRepo.findAll()) {

			if (c.getContents().toLowerCase().contains(d.getWord().toLowerCase()) &&c.getContents()==null && c.getContents().length() == 0) {
				return false;
			}

		}
		return true;
	}
	
	
	
@Override
	public void addcomments(Comment comment , Long idpub,Long idUser ) {
	Publication  pub=publciationRepo.findById(idpub).orElse(null);	
	User  user=userRepo.findById(idUser).orElse(null);
		String commentWords = comment.getContents();
		List<String> badwords1 = new ArrayList<String>();
		Date currentSqlDate = new Date(System.currentTimeMillis());
		comment.setDate(currentSqlDate);
		comment.setPublications(pub);
		comment.setUser(user);
		List<DictionnaireBadWords> badwords = badwordsRepo.findAll();
		for (DictionnaireBadWords bd : badwords) {
			badwords1.add(bd.getWord());
		}
		if (verif(comment)) {	
			commentRepo.save(comment);
		} else {
			comment.setContents("*****");
			commentRepo.save(comment);

		}

	}

	
	
	// a corriger
@Override
	public void updateComment(Long idComment, Long idpub,Long idUser ) {
		Comment  com=commentRepo.findById(idComment).orElse(null);
		Publication  pub=publciationRepo.findById(idpub).orElse(null);	
		User  user=userRepo.findById(idUser).orElse(null);
		
	
		
		Date currentSqlDate = new Date(System.currentTimeMillis());
		
		com.setDate(currentSqlDate);
		com.setPublications(pub);
		com.setUser(user);
		
		if (verif(com)) {	
			commentRepo.save(com);
		} else {
			com.setContents("*****");
			commentRepo.save(com);

		}

	}
	
@Override
	public void deleteComment(Long idComment) {
		commentRepo.deleteById(idComment);
	}

@Override
	public List<Comment> listCommentsByPublication(Long idPublicaiton) {
		Publication publication = publciationRepo.findById(idPublicaiton).orElse(null);

		List<Comment> listComments = commentRepo.listcommentsByPublication(idPublicaiton);

		return listComments;
	}

}
