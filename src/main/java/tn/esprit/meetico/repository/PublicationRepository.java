package tn.esprit.meetico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.meetico.entity.Publication;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

}
