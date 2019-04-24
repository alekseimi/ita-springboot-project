package ita.springboot.application.model.nnet;

public interface INeuralNetworkError {
    double getError(double[] outputData, double[] idealData);
}
