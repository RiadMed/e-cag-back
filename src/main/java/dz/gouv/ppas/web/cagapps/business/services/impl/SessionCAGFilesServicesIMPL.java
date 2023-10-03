package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.UserDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGFilesDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionStatusDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.StatusDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.enums.StatusEnum;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAGFiles;
import dz.gouv.ppas.web.cagapps.business.repositories.SessionCAGFilesDao;
import dz.gouv.ppas.web.cagapps.business.services.*;
import dz.gouv.ppas.web.cagapps.exceptions.ECagException;
import dz.gouv.ppas.web.cagapps.tools.AppsUtils;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class SessionCAGFilesServicesIMPL extends GenericServiceImpl<SessionCAGFilesDao, SessionCAGFiles, SessionCAGFilesDto, Long> implements SessionCAGFilesServices {

    private final UserService userService;
    private final StatusServices statusServices;
    private final SessionCAGServices sessionCAGServices;
    private final SessionCAGFilesDao sessionCAGFilesDao;
    private final SessionStatusServices sessionStatusServices;

    private static String UPLOAD_FOLDER = "E:\\files\\";

    @Override
    public EntityResponse<SessionCAGFilesDto> saveFile(SessionCAGFilesDto file) throws IOException {
        if (file == null) {
            return new EntityResponse<>("Le fichier PDF est obligatoir. (7001)");
        }
        createDirIfNotExist(file.getSessionCAGId());
        String fileString64 = file.getFile().replace("data:application/pdf;base64,", "");
        byte[] bytes = Base64.getDecoder().decode(fileString64);
        Path path = Paths.get((UPLOAD_FOLDER) + "\\" + file.getSessionCAGId() + "\\" + file.getLabel() + ".pdf");
        file.setFilePath((UPLOAD_FOLDER) + file.getSessionCAGId() + "\\" + file.getLabel() + ".pdf");
        Files.write(path, bytes);
        return new EntityResponse<>(save(file));
    }

    private void createDirIfNotExist(Long sessionCAGId) {
        File directory = new File((UPLOAD_FOLDER) + sessionCAGId + "\\");
        if (!directory.exists()) {
            directory.mkdir();
        }
    }


    @Override
    public String getFiles(Long idFile) {
        Optional<SessionCAGFilesDto> dtoOpt = findById(idFile);
        if (dtoOpt.isPresent()) {
            try {
                return "data:application/pdf;base64," + AppsUtils.asString(AppsUtils.getResources(dtoOpt.get().getFilePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public String getListofFiles(Long idFile) {
        Optional<SessionCAGFilesDto> dtoOpt = findById(idFile);
        if (!dtoOpt.isPresent()) {
            return null;
        }
        List<String> list = new ArrayList<>();
        File files = new File(dtoOpt.get().getFilePath());
        String[] fileList = files.list();
        for (String name : fileList) {
            list.add(name);
        }

        return list.get(0);

    }

    @Override
    public EntityResponse<SessionCAGDto> terminerLeTelechargement(Long sessionCAGId) {
        Optional<SessionCAGDto> sessionOptional = sessionCAGServices.findById(sessionCAGId);
        if (!sessionOptional.isPresent()) {
            return new EntityResponse<>("Erreur dans l'envoi des invitation CAG (Statut). (7002)");
        }

        StatusDto status = statusServices.findStatusByLabel(StatusEnum.FILES_DOWN.toString());
        if (status == null) {
            return new EntityResponse<>("Erreur dans l'envoi des invitation CAG (Statut). (7002)");
        }

        Optional<UserDto> user = userService.findByAccountName(AppsUtils.getUserPrincipal().getAccountName());
        if (!user.isPresent()) {
            return new EntityResponse<>("Erreur dans l'envoi des invitation CAG (User). (7002)");
        }

        SessionCAGDto sessionCAG = sessionOptional.get();
        sessionCAG.setStatusId(status.getId());
        sessionCAG.setStatusLabel(status.getLabel());
        sessionCAGServices.save(sessionCAG);
        sessionStatusServices.save(new SessionStatusDto(status.getId(), sessionCAG.getId(), user.get().getId()));
        return new EntityResponse<>(sessionCAG);
    }

    @Override
    public List<SessionCAGFilesDto> getListSessionFile(Long sessionCAGId) {
        return mapToDto(sessionCAGFilesDao.findSessionCAGFilesBySessionCAGId(sessionCAGId));
    }

    @Override
    public Page<SessionCAGFilesDto> findSessionByPage(Long sessionId) {
        return sessionCAGFilesDao.findSessionByPage(sessionId, PageRequest.of(0, 5, Sort.by("price").descending())).map(x -> mapToDto(x));
    }

}
