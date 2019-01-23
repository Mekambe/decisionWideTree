package com.decisionTree.wiki.services;

import com.decisionTree.wiki.dao.AnwsersImageAndLinksRepository;
import com.decisionTree.wiki.dao.QuestionsDomainRepository;
import com.decisionTree.wiki.domain.QuestionsDomain;
import com.decisionTree.wiki.exceptions.FileStorageException;
import com.decisionTree.wiki.exceptions.MyFileNotFoundException;
import com.decisionTree.wiki.property.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    AnwsersImageAndLinksRepository anwsersImageAndLinksRepository;

QuestionsDomainRepository questionsDomainRepository;
    FileStorageProperties fileStorageProperties;
    QuestionsDomain questionsDomain;

    public FileStorageService(Path fileStorageLocation, AnwsersImageAndLinksRepository anwsersImageAndLinksRepository, QuestionsDomainRepository questionsDomainRepository, FileStorageProperties fileStorageProperties, QuestionsDomain questionsDomain) {
        this.fileStorageLocation = fileStorageLocation;
        this.anwsersImageAndLinksRepository = anwsersImageAndLinksRepository;
        this.questionsDomainRepository = questionsDomainRepository;
        this.fileStorageProperties = fileStorageProperties;
        this.questionsDomain = questionsDomain;
    }

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(int fileNumber) {
        try {
            Path filePath = Paths.get(questionsDomainRepository.findByIdQuestions(fileNumber).getImage().getImage());
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileNumber);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileNumber, ex);
        }
    }


}
