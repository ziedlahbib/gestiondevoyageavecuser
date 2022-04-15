package tn.esprit.meetico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.meetico.repository.DislikeRepository;
import tn.esprit.meetico.repository.LikeRepository;

@Service
public class LikeSericeImpl implements ILikeService {
@Autowired 
LikeRepository likeRepo;
@Autowired
DislikeRepository dislikeRepo;










}
