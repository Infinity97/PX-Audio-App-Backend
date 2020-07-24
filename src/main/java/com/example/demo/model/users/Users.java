package com.example.demo.model.users;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.Image;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "users")
@Table(name = "users")
public class Users extends BaseEntity {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "user_id", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
	private String userId;

	@Column(name = "name")
	private String name;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "password")
	private String password;

	@OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Address> address;

	@Column(name = "user_type")
	private String userType;

	@OneToOne(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Image image;

	@Column(name = "login_type")
	private String loginType;

	@JsonManagedReference
	@OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Review> reviews;

}
