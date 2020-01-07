package com.sut62.team07.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
    private @NonNull Long id;
    private @NonNull String name;

}
