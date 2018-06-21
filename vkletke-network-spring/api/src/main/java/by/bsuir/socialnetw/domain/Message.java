package by.bsuir.socialnetw.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "messages")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"sender",
		                 "reciever",
		                 "posted"})
@ToString(of = {"id", "body"})
public class Message implements Serializable{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender_id")
	@Getter
    @Setter
	private Person sender;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reciever_id")
	@Getter
    @Setter
	private Person reciever;

	@Getter
    @Setter
	@Column(nullable = false)
	private String body;

	@Column(updatable = false, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	private Date posted = new Date();

}
