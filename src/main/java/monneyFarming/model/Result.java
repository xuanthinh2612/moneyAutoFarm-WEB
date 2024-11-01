package monneyFarming.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String betResult;

    @Column
    private int betAmount = 0;

    @Column
    private Double winAmount = 0D;

    @Column
    private int betStatus;

    @Column
    private int serverNo;

    @Column
    private LocalDateTime createdAt;
}
