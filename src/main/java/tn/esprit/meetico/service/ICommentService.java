package tn.esprit.meetico.service;

import java.util.List;

import tn.esprit.meetico.entity.Comment;

public interface ICommentService {
	public List<Comment> ListAllCommentsAdmin(Long idPublicaiton);
	public boolean verif(Comment c);
	public void addcomments(Comment comment , Long idpub,Long idUser );
	public void updateComment(Long idComment, Long idpub,Long idUser );
	public void deleteComment(Long idComment);
	public List<Comment> listCommentsByPublication(Long idPublicaiton);
}
