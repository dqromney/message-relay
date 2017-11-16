package com.dqr.messagerelay.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "messages")
@Data
public class Message implements Serializable {

    private static final long serialVersionUID = -2131700490698128545L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER) //TODO this is reversed.  We want to think in objects not relationships.
   	@JoinColumn(name = "user_id", updatable = false, insertable = false)
   	private User user;

    @Column(name="user_id")//TODO this is reversed.  We want to think in objects not relationships.
    private Long userId;

    @Column(name = "message")
    private String message;

    @Column(name = "creation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
}
