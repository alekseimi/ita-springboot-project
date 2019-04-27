package ita.springboot.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "nnet_result")
public class NNetResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nnet_result_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    private String trainingType;

    private String activationType;

    private int epochsCount;

    private int iterationsCount;

    private int hiddenLayersCount;

    private int hiddenLayersNeuronCount;

    private double averageValidationError;

    private double averageElapsed;

    private double averageClassificationError;

    public NNetResults(String trainingType, String activationType, int epochsCount, int iterationsCount, int hiddenLayersCount,
                       int hiddenLayersNeuronCount, double averageValidationError,
                       double averageElapsed, double averageClassificationError) {
        this.trainingType = trainingType;
        this.activationType = activationType;
        this.epochsCount = epochsCount;
        this.iterationsCount = iterationsCount;
        this.hiddenLayersCount = hiddenLayersCount;
        this.hiddenLayersNeuronCount = hiddenLayersNeuronCount;
        this.averageValidationError = averageValidationError;
        this.averageElapsed = averageElapsed;
        this.averageClassificationError = averageClassificationError;
    }
}
