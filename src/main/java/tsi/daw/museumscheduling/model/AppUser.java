package tsi.daw.museumscheduling.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import tsi.daw.museumscheduling.enums.UserProfile;
import tsi.daw.museumscheduling.interfaces.Messages;

@Entity
public class AppUser {
	
	@Id
	@SequenceGenerator(name = "user_id", sequenceName = "user_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id")
	private Long id;
	
	@NotBlank(message = Messages.VALIDATION_NAME_REQUIRED)
    private String name;
	
    @NotBlank(message = Messages.VALIDATION_CPF_REQUIRED)
    @Pattern(regexp = Messages.REGEX_EXPRESSION_CPF, message = Messages.VALIDATION_CPF_FORMAT)
	private String cpf;
    
    @NotBlank(message = Messages.VALIDATION_EMAIL_REQUIRED)
    @Email(message = Messages.VALIDATION_EMAIL_INVALID)
	private String email;
    
    @NotBlank(message = Messages.VALIDATION_PASSWORD_REQUIRED)
    @Size(min = 8, message = Messages.VALIDATION_PASSWORD_LENGTH)
	private String password;
	
    @Enumerated(EnumType.STRING)
    @NotNull(message = Messages.VALIDATION_USER_PROFILE_REQUIRED)
	private UserProfile userProfile;
	
    @NotNull(message = Messages.VALIDATION_MUSEUM)
    @ManyToOne
    @JoinColumn(name = "museum_id", nullable = false)
    private Museum museum;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserProfile getUserProfile() {
		return userProfile;
	}
	
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public Museum getMuseum() {
		return museum;
	}

	public void setMuseum(Museum museum) {
		this.museum = museum;
	}
} // class AppUser	
