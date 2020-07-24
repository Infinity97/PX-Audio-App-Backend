package com.example.demo.model.cart;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.users.Users;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "cart")
public class Cart extends BaseEntity {

	@Id
	String userId;
	@MapsId
	@OneToOne
	@JoinColumn(name = "user_id")
	private Users users;

	@OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<CartDetail> cartDetails = new HashSet<>();

}
