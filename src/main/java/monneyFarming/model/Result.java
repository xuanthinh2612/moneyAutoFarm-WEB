package monneyFarming.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Result {

    final static String TAI = "tai";
    final static String XIU = "xiu";

    final static int SKIP_BET = 0;
    final static int BET = 1;

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
    private LocalDateTime createdAt;
}
