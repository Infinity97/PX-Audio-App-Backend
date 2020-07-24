package com.example.demo.model.users;
import javax.persistence.*;

/**
 * @author Infinity97
 */
@Entity
@Table(name = "user_referral_earnings")
public class UserReferralEarnings {
	@Id
	private String userId;
	@MapsId
	@OneToOne
	@JoinColumn
	private Users users;

	@Column(name = "referral_code")
	private String referralCode;

	@Column(name = "referral_earnings")
	private double referral_earnings;

	@Column(name = "company_specific_earnings")
	private double companySpecificEarnings;

	@Column(name = "wallet_amount")
	private double walletAmount;
}
