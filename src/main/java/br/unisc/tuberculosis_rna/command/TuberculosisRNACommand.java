package br.unisc.tuberculosis_rna.command;

public record TuberculosisRNACommand(String encryptedFilePath,
                                     int numCamadas,
                                     int tamCamada,
                                     double taxaAprendizado,
                                     int margemErro,
                                     int numInteracoes) {}
