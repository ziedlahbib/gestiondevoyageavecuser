package tn.esprit.meetico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.meetico.entity.PostLike;

@Repository
public interface LikeRepository extends JpaRepository<PostLike, Long> {

}
