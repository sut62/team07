package com.sut62.team07.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.*;

@Data
@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="ROOM")
public class Room {
    @Id
    @SequenceGenerator(name="ROOM_SEQ",sequenceName="ROOM_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROOM_SEQ")
    @Column(name="ROOM_ID",unique = true, nullable = true)
    private Long id;

    @NotNull(message = "Name cannot be null")
    private String name;

}
