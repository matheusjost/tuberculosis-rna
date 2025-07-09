package br.unisc.tuberculosis_rna.service;

import br.unisc.tuberculosis_rna.exception.TuberculosisRNAException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArquivoService {

    @Autowired
    private AESEncryptionService encryptionService;

    @Value("${br.unisc.tuberculosis_rna.diretorio.entrada}")
    private String DIRETORIO_ENTRADA;

    public String armazenarArquivo(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String filePath = getFilePath(originalFileName);
        File uploadedFile = new File(filePath);

        if(!uploadedFile.exists()) {
            try {
                File directory = uploadedFile.getParentFile();
                Files.createDirectories(directory.toPath());
                if (uploadedFile.createNewFile()) {
                    log.info("Armazenado com sucesso em: " + uploadedFile.getAbsolutePath());
                }

                try (FileOutputStream fop = new FileOutputStream(uploadedFile)) {
                    fop.write(file.getBytes());
                    fop.flush();
                }

            } catch (Exception e) {
                throw new TuberculosisRNAException("Erro ao salvar o arquivo: " + e.getMessage());
            }
        }

        return filePath;
    }

    private String getFilePath(String fileName) {
        String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
        String fileExtension = fileName.substring(fileName.lastIndexOf('.'));

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String newFileName = fileNameWithoutExtension + "_" + timestamp + fileExtension;

        return DIRETORIO_ENTRADA + newFileName;
    }

    public String encryptFilePath(String filePath) {
        try {
            return encryptionService.encrypt(filePath);
        } catch (Exception e) {
            throw new TuberculosisRNAException("Erro ao criptografar o caminho do arquivo: " + e.getMessage());
        }
    }

    public String decryptFilePath(String encryptedFilePath) {
        try {
            return encryptionService.decrypt(encryptedFilePath);
        } catch (Exception e) {
            throw new TuberculosisRNAException("Erro ao descriptografar o caminho do arquivo: " + e.getMessage());
        }
    }

}
