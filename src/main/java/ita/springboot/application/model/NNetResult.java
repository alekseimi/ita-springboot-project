package ita.springboot.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "nnet_results")
public class NNetResult {

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


    public NNetResult() {
    }

    public NNetResult(String trainingType, String activationType, int epochsCount, int iterationsCount, int hiddenLayersCount,
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public String getActivationType() {
        return activationType;
    }

    public void setActivationType(String activationType) {
        this.activationType = activationType;
    }

    public int getEpochsCount() {
        return epochsCount;
    }

    public void setEpochsCount(int epochsCount) {
        this.epochsCount = epochsCount;
    }

    public int getIterationsCount() {
        return iterationsCount;
    }

    public void setIterationsCount(int iterationsCount) {
        this.iterationsCount = iterationsCount;
    }

    public int getHiddenLayersCount() {
        return hiddenLayersCount;
    }

    public void setHiddenLayersCount(int hiddenLayersCount) {
        this.hiddenLayersCount = hiddenLayersCount;
    }

    public int getHiddenLayersNeuronCount() {
        return hiddenLayersNeuronCount;
    }

    public void setHiddenLayersNeuronCount(int hiddenLayersNeuronCount) {
        this.hiddenLayersNeuronCount = hiddenLayersNeuronCount;
    }

    public double getAverageValidationError() {
        return averageValidationError;
    }

    public void setAverageValidationError(double averageValidationError) {
        this.averageValidationError = averageValidationError;
    }

    public double getAverageElapsed() {
        return averageElapsed;
    }

    public void setAverageElapsed(double averageElapsed) {
        this.averageElapsed = averageElapsed;
    }

    public double getAverageClassificationError() {
        return averageClassificationError;
    }

    public void setAverageClassificationError(double averageClassificationError) {
        this.averageClassificationError = averageClassificationError;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
