package com.example.demo.model.users;

import com.example.demo.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "feedback")
@Data
public class Feedback extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String feedback;
	/**
	 * Enum FeedbackType
	 */
	@Column(name = "feedback_type")
	private String feedbackType;

	@Column(name = "feedback_description")
	private String feedbackDescription;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private Users users;
	//TODO: Allow for uploading Images as feedback.

}
