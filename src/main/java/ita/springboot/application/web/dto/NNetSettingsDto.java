package ita.springboot.application.web.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NNetSettingsDto {

    @NotNull
    @Size(min = 1, max = 200)
    private Integer iterationsCount;

    @NotNull
    @Size(min = 5, max = 100)
    private Integer epochsCount;

    @NotNull
    @Size(min = 50, max = 100)
    private Integer minHiddenLayerNeuronCount;

    @NotNull
    @Size(min = 100, max = 200)
    private Integer maxHiddenLayerNeuronCount;

    @NotNull
    @Size(min = 50, max = 100)
    private Integer hiddenLayerNeuronCountStep;

    @NotEmpty
    @Size(min = 1, max = 2)
    private Integer minHiddenLayerCount;

    @NotEmpty
    @Size(min = 2)
    private Integer maxHiddenLayerCount;


    public Integer getIterationsCount() {
        return iterationsCount;
    }

    public void setIterationsCount(Integer iterationsCount) {
        this.iterationsCount = iterationsCount;
    }

    public Integer getEpochsCount() {
        return epochsCount;
    }

    public void setEpochsCount(Integer epochsCount) {
        this.epochsCount = epochsCount;
    }

    public Integer getMinHiddenLayerNeuronCount() {
        return minHiddenLayerNeuronCount;
    }

    public void setMinHiddenLayerNeuronCount(Integer minHiddenLayerNeuronCount) {
        this.minHiddenLayerNeuronCount = minHiddenLayerNeuronCount;
    }

    public Integer getMaxHiddenLayerNeuronCount() {
        return maxHiddenLayerNeuronCount;
    }

    public void setMaxHiddenLayerNeuronCount(Integer maxHiddenLayerNeuronCount) {
        this.maxHiddenLayerNeuronCount = maxHiddenLayerNeuronCount;
    }

    public Integer getHiddenLayerNeuronCountStep() {
        return hiddenLayerNeuronCountStep;
    }

    public void setHiddenLayerNeuronCountStep(Integer hiddenLayerNeuronCountStep) {
        this.hiddenLayerNeuronCountStep = hiddenLayerNeuronCountStep;
    }

    public Integer getMinHiddenLayerCount() {
        return minHiddenLayerCount;
    }

    public void setMinHiddenLayerCount(Integer minHiddenLayerCount) {
        this.minHiddenLayerCount = minHiddenLayerCount;
    }

    public Integer getMaxHiddenLayerCount() {
        return maxHiddenLayerCount;
    }

    public void setMaxHiddenLayerCount(Integer maxHiddenLayerCount) {
        this.maxHiddenLayerCount = maxHiddenLayerCount;
    }
}
