package com.websocket.RadioChanel.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Data
public class Message {

    @Id
    @GeneratedValue
    private Long id;
    private String text;
    @ManyToOne
    private User user;
    @ManyToOne
    private Channel channel;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
