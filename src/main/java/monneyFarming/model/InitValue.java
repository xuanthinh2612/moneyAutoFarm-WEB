package monneyFarming.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InitValue {

    public final static int NOT_READY = 0;
    public final static int START_PROGRAM = 1;
    public final static int STOP_PROGRAM = 2;
    public final static int OPEN_MINI_GAME = 3;
    public final static int START_BET = 4;
    public final static int STOP_BET = 5;

    public final static int SERVER_1 = 1;
    public final static int SERVER_2 = 2;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int serverNo;
    @Column
    private boolean serverRunning;
    @Column
    private int betType;
    @Column
    private int betPoint;
    @Column
    private int maxRound;
    @Column
    private String betIn;
    @Column
    private int betBase;
    @Column
    private int maxLoseNum;
}


