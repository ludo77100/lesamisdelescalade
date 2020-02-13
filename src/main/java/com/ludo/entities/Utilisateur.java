package com.ludo.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.ludo.enums.RoleEnum;
import com.ludo.security.BCryptManagerUtil;


/**
 * Couche entities utilisateur pour l'application
 * @author A87671
 *
 */
@Entity
public class Utilisateur implements Serializable, UserDetails {
	
	/**
	 * Constant serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id de l'utilisateur
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_utilisateur")
	private Long idUtilisateur;
	
	/**
	 * pseudo de l'utilisateur
	 */
	@NotNull
	@Column(name = "pseudo", nullable = false, unique = true)
	private String pseudo;
	
	/**
	 * mot de pass de l'utilisateur
	 */
	@NotNull
	@Column(name = "mot_de_pass", nullable = false, unique = false)
	private String motDePass;
	
	/**
	 * email de l'utilisateur
	 */
	@NotNull
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	/**
	 * Relation avec la table Spot
	 */
	@OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
	private Collection<Spot> spot;

	/**
	 * Relation avec la table Commentaire
	 */
	@OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
	private Collection<Commentaire> commentaire;

	/**
	 * Relation avec la table Topo
	 */
	@OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
	private Collection<Topo> topo;
	
	/**
	 * Relation avec la table Reservation
	 */
	@OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
	private Collection<Reservation> reservation ;

	/**
	 * Collection de roles possible pour un utilisateur
	 */
	@ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
	@Cascade(value = CascadeType.REMOVE)
	@JoinTable(indexes = {
			@Index(name = "INDEX_UTILISATEUR_ROLE", columnList = "id_utilisateur") }, name = "role", joinColumns = @JoinColumn(name = "id_utilisateur"))
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private Collection<RoleEnum> roles;

	/**
	 * Non utilisé
	 */
	@SuppressWarnings("unused")
	private boolean credentialsNonExpired;
	@SuppressWarnings("unused")
	private boolean enabled;
	@SuppressWarnings("unused")
	private boolean accountNonLocked;
	@SuppressWarnings("unused")
	private boolean accountNonExpired;

	/**
	 * Instanciation de utilisateur
	 */
	public Utilisateur() {
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = true;
		this.roles = Collections.singletonList(RoleEnum.USER);
	}

	/**
	 * instanciation de utilisateur
	 * @param pseudo pseudo de l'utilisateur
	 * @param motDePass mot de pass de l'utilisateur
	 * @param email email de l'utilisateur
	 * @param roles roles de l'utilisateur
	 */
	public Utilisateur(String pseudo, String motDePass, String email, Collection<RoleEnum> roles) {
		super();
		this.pseudo = pseudo;
		this.motDePass = BCryptManagerUtil.passwordencoder().encode(motDePass);
		this.email = email;
		this.roles = roles;
	}

	/**
	 * Liste des rôles dans un format que spring security peut interpréter 
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		String roles = org.springframework.util.StringUtils.collectionToCommaDelimitedString(getRoles().stream()
                .map(Enum::name).collect(Collectors.toList()));
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	public Long getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePass() {
		return motDePass;
	}

	public void setMotDePass(String motDePass) {
		if (!motDePass.isEmpty()) {
            this.motDePass = BCryptManagerUtil.passwordencoder().encode(motDePass);
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<RoleEnum> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleEnum> roles) {
		this.roles = roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return motDePass;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return pseudo;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}