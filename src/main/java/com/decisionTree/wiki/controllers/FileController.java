/*
 * Developed by Jan Misiorny
 * February A.D. 2019
 */

package com.decisionTree.wiki.controllers;

import com.decisionTree.wiki.dao.AnwsersImageAndLinksRepository;
import com.decisionTree.wiki.dao.QuestionGroupRepository;
import com.decisionTree.wiki.dao.QuestionsDomainRepository;
import com.decisionTree.wiki.domain.AnwsersImageAndLinks;
import com.decisionTree.wiki.domain.QuestionGroupDomain;
import com.decisionTree.wiki.domain.QuestionsDomain;
import com.decisionTree.wiki.dto.ImageAndLinkDto;
import com.decisionTree.wiki.exceptions.IdNotFound;
import com.decisionTree.wiki.payload.UploadFileResponse;
import com.decisionTree.wiki.services.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);


    private FileStorageService fileStorageService;
    private QuestionsDomainRepository questionsDomainRepository;
    private QuestionGroupRepository questionGroupRepository;
    private AnwsersImageAndLinksRepository anwsersImageAndLinksRepository;



    @Autowired
    public FileController(FileStorageService fileStorageService, QuestionsDomainRepository questionsDomainRepository, QuestionGroupRepository questionGroupRepository, AnwsersImageAndLinksRepository anwsersImageAndLinksRepository) {
        this.questionsDomainRepository = questionsDomainRepository;
        this.questionGroupRepository = questionGroupRepository;
        this.anwsersImageAndLinksRepository = anwsersImageAndLinksRepository;
        this.fileStorageService = fileStorageService;
    }

    private UploadFileResponse uploadFile(MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        String pathUri = ""; //adres zapisanego obrazak na dysku

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
// dodac do konstruktora: pathUri);
    }

    @PostMapping("/uploadMultipleFiles")
    public void uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
                                                        @RequestParam("id") int id,
                                                        @RequestParam("questionId") int questionId,
                                                        @RequestParam("link") String link) {
        List<UploadFileResponse> collect = Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
       QuestionsDomain byNumberAndQuestion = (questionsDomainRepository.findByNumberAndQuestionHandler_IdQuestionGroup(questionId, id));


        AnwsersImageAndLinks anwsersImageAndLinks =  new AnwsersImageAndLinks();
        anwsersImageAndLinks.setLinks(link);
        anwsersImageAndLinks.setImage(collect.get(0).getFileDownloadUri());  //getPathUri
        anwsersImageAndLinks.setQuestionsDomain(byNumberAndQuestion);

        byNumberAndQuestion.setImage(anwsersImageAndLinksRepository.save(anwsersImageAndLinks));
        questionsDomainRepository.save(byNumberAndQuestion);


    }

    @GetMapping("/downloadFile/{imageID}")

    public ResponseEntity<Resource> downloadFile(@PathVariable int imageID, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(imageID);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/downloadFile/delete/{imageID}")
    public void deleteImage (@PathVariable("imageID") Integer id) throws IdNotFound {
        Optional<QuestionsDomain> byIdQuestions = Optional.ofNullable(questionsDomainRepository.findByIdQuestions(id));
        Optional<AnwsersImageAndLinks> image = Optional.ofNullable(byIdQuestions.get().getImage());
        if (!byIdQuestions.isPresent()||!image.isPresent()){
            throw new IdNotFound();}

        byIdQuestions.get().setImage(null);
        questionsDomainRepository.save(byIdQuestions.get());
        anwsersImageAndLinksRepository.delete(image.get());

        }










    }



