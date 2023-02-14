package com.websocket.RadioChanel.model;
import lombok.Data;

import jakarta.persistence.*;
import lombok.experimental.Accessors;


@Entity
@Data
@Accessors(chain = true)
public class Channel {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
}
