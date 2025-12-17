package com.project.FileConvertor.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.FileConvertor.Model.FileStorageProperties;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibreOfficeConvertor {

    private final FileStorageProperties fileStorageProperties;
    
    @Value("${libreoffice.path:/usr/bin/soffice}")
    private String libreOfficePath;

    @Value("${conversion.timeout:120}")
    private int timeoutSeconds;

    public byte[] convertDocxToPdf(InputStream docxInputStream, String originalFileName) throws IOException{
        Path tempDir = Files.createTempDirectory(fileStorageProperties.getBaseDir());
        Path inputFile = tempDir.resolve(originalFileName);
        Path outputFile = tempDir.resolve(originalFileName.replace(".docx", ".pdf"));

        try{
            Files.copy(docxInputStream, inputFile);

            ProcessBuilder processBuilder = new ProcessBuilder(
                libreOfficePath,
                "--headless", 
                "--convert-to", 
                "pdf", 
                "--outdir", 
                tempDir.toString(), 
                inputFile.toString()
            );

            processBuilder.environment().put("HOME", tempDir.toString());
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
                String line;
                while((line = reader.readLine()) != null){
                    output.append(line).append("\n");
                }
            }

            boolean finished = process.waitFor(timeoutSeconds, TimeUnit.SECONDS);

            if(!finished){
                process.destroyForcibly();
                throw new IOException("Convertion timeout after " + timeoutSeconds + " seconds");
            }
            if (process.exitValue() != 0) {
                throw new IOException("Conversion failed with exit code " + 
                    process.exitValue() + "\nOutput: " + output);
            }

            // Check if PDF was created
            if (!Files.exists(outputFile)) {
                throw new IOException("PDF file was not created. Output: " + output);
            }

            // Read PDF bytes
            return Files.readAllBytes(outputFile);
        } catch (Exception e) {
            throw new RuntimeException("Conversion failed", e);
        }finally {
            // Clean up temporary files
            cleanupTempFiles(tempDir, inputFile, outputFile);
        }
    }

    private void cleanupTempFiles(Path tempDir, Path inputFile, Path outputFile) {
        try {
            if (Files.exists(inputFile)) {
                Files.delete(inputFile);
            }
            if (Files.exists(outputFile)) {
                Files.delete(outputFile);
            }
            if (Files.exists(tempDir)) {
                Files.delete(tempDir);
            }
        } catch (IOException e) {
            // Log but don't throw - cleanup is best effort
            System.err.println("Warning: Could not clean up temp files: " + e.getMessage());
        }
    }

    public boolean isLibreOfficeAvailable() {
        try {
            ProcessBuilder pb = new ProcessBuilder(libreOfficePath, "--version");
            Process process = pb.start();
            boolean finished = process.waitFor(5, TimeUnit.SECONDS);
            return finished && process.exitValue() == 0;
        } catch (Exception e) {
            return false;
        }
    }
}
