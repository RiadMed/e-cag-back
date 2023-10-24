package dz.gouv.ppas.web.cagapps.tools;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.User;
import dz.gouv.ppas.web.cagapps.business.reporting.model.ReportDto;
import dz.gouv.ppas.web.cagapps.exceptions.ECagException;
import io.jsonwebtoken.impl.DefaultClock;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class AppsUtils {


    public static Sort.Direction getSortDirection(String sort) {
        return sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

    public static Date getDate(String value) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.parse(value);
    }


    public static List<Integer> getIdList(String params) {
        String[] ids = params.split(",");
        return Arrays.stream(ids).map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }


    public static Date calculateExpirationDate(Date createdDate, long expiration) {
        return new Date(createdDate.getTime() + expiration);
    }

    public static String convertDateToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }

    public static <T> Boolean isNotEmpty(List<T> list) {
        return list != null && !list.isEmpty();
    }


    public static User getUserPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? (User) authentication.getPrincipal() : null;
    }

    public static Boolean isAccountNonExpired(long expiration) {
        return calculateExpirationDate(new Date(), expiration).before(DefaultClock.INSTANCE.now());
    }

    public static Collection<GrantedAuthority> getAuthorities(Collection<String> roles) {
        if (!roles.isEmpty())
            return roles.stream().map(group ->
                    new SimpleGrantedAuthority(group.split(",")[0].replace("CN=", "")))
                    .collect(Collectors.toList());
        return new ArrayList<>();
    }

    public static InputStream geQrCode(ReportDto report) throws WriterException, IOException {
        return AppsUtils.writeImage("png", AppsUtils.generateMatrix(report.getQrCode(), 400));
    }


    public static BitMatrix generateMatrix(String data, int size) throws WriterException {
        BitMatrix bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size);
        return bitMatrix;
    }

    public static InputStream writeImage(String imageFormat, BitMatrix bitMatrix) throws FileNotFoundException, IOException {
        ByteArrayOutputStream fileOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, fileOutputStream);
        fileOutputStream.close();
        InputStream qrCode = new ByteArrayInputStream(fileOutputStream.toByteArray());
        return qrCode;
    }

    public static String generateRandomPassword(int len, int randNumOrigin, int randNumBound) {
        SecureRandom random = new SecureRandom();
        return random.ints(randNumOrigin, randNumBound + 1)
                .filter(i -> Character.isAlphabetic(i) || Character.isDigit(i))
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    public static String asString(Resource resource) throws IOException {
        byte[] sourceBytes = IOUtils.toByteArray(resource.getInputStream());
        return Base64.getEncoder().encodeToString(sourceBytes);

    }

    public static Resource getResources(String url) {
        Path pathFile = Paths.get(url);
        UrlResource resource = null;
        try {
            resource = new UrlResource(pathFile.toUri());
        } catch (MalformedURLException e) {
            throw new ECagException(e.getMessage());
        }
        return resource;
    }

    public static boolean nullCheck(Object field) {
        if (field == null || (field instanceof String && ((String) field).isEmpty()))
            return true;
        return false;
    }


}
