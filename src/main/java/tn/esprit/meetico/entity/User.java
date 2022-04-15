package tn.esprit.meetico.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import tn.esprit.meetico.entity.Trip;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long userId;

	private Boolean active;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Set<ActivityField> activityFields;

	private String city;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@NonNull
	private String email;

	@NonNull
	private String firstName;

	@NonNull
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@NonNull
	private String lastName;

	@NonNull
	private String password;

	@NonNull
	private Long phoneNumber;

	private String picturePath;
	@Value("${some.key:disponible}")
	private String etat;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Set<Profession> professions;

	@JsonIgnore
	@OneToMany(mappedBy = "sender", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Set<Request> requests;

	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private Role role;

	@NonNull
	private String username;

	private Integer verificationCode;
	@ManyToMany
	@JsonIgnore
	private Set<Trip> trips;
	@OneToMany( mappedBy = "user",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Trip> tripss;

}
