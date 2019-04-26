package ita.springboot.application.web.dto;

import javax.validation.constraints.*;

public class NNetSettingsDto {

    @NotNull
    private String activationType;

    @NotNull
    private String trainingType;

    @NotNull
    @Min(1)
    @Max(3)
    private Integer iterationCount;

    @NotNull
    @Min(3)
    @Max(5)
    private Integer epochsCount;

    @NotNull
    @Min(1)
    @Max(3)
    private Integer hiddenLayerCount;

    @NotNull
    private Integer hiddenLayerNeuronCount;


    public String getActivationType() {
        return activationType;
    }

    public NNetSettingsDto setActivationType(String activationType) {
        this.activationType = activationType;
        return this;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public NNetSettingsDto setTrainingType(String trainingType) {
        this.trainingType = trainingType;
        return this;
    }

    public Integer getIterationCount() {
        return iterationCount;
    }

    public NNetSettingsDto setIterationCount(Integer iterationCount) {
        this.iterationCount = iterationCount;
        return this;
    }

    public Integer getEpochsCount() {
        return epochsCount;
    }

    public NNetSettingsDto setEpochsCount(Integer epochsCount) {
        this.epochsCount = epochsCount;
        return this;
    }

    public Integer getHiddenLayerCount() {
        return hiddenLayerCount;
    }

    public NNetSettingsDto setHiddenLayerCount(Integer hiddenLayerCount) {
        this.hiddenLayerCount = hiddenLayerCount;
        return this;
    }

    public Integer getHiddenLayerNeuronCount() {
        return hiddenLayerNeuronCount;
    }

    public NNetSettingsDto setHiddenLayerNeuronCount(Integer hiddenLayerNeuronCount) {
        this.hiddenLayerNeuronCount = hiddenLayerNeuronCount;
        return this;
    }
}
