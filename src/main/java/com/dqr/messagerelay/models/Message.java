package com.dqr.messagerelay.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "messages")
@Data
public class Message implements Serializable {

    private static final long serialVersionUID = -2131700490698128545L;


}
