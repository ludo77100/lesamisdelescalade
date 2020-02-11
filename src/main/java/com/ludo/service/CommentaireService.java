package com.ludo.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ludo.entities.Commentaire;

public interface CommentaireService {

	public void saveCommentaire(@Valid Commentaire commentaire, Long spotId);

	public void saveEditCommentaire(@Valid Commentaire commentaire, Long comId);

	public void deleteById(Long comId);

	public Optional<Commentaire> findById(Long comId);

	public List<Commentaire> findCommentaireBySpot(Long spotId);



}
