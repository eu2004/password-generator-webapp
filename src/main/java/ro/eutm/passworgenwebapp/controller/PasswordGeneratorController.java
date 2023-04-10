package ro.eutm.passworgenwebapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.eu.passwallet.service.PasswordGenerator;

import java.util.Objects;

@RestController
@RequestMapping("/password-generator")
public class PasswordGeneratorController {
    private static final Logger logger = LoggerFactory.getLogger(PasswordGeneratorController.class);

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "generates a random password based on the given configuration. The minimum length of the password is 4.The maximum is 256.Example:{"
            + "includeLowerCase: true,"
            + "includeNumbers: true,"
            + "includeSymbols: true,"
            + "includeUpperCase: true,"
            + "length: 16"
            + "}")
    public String generate(@RequestHeader HttpHeaders headers, @RequestBody PasswordGeneratorConfig config) {
        if (Objects.isNull(config)) {
            logger.error("RequestBody is null");
            headers.forEach((key, value) -> logger.info(String.format("Header '%s' = %s", key, value)));
            return "";
        }

        if (config.length > 256 || config.length < 4) {
            logger.error(String.format("Exceeded length %s", config.length));
            headers.forEach((key, value) -> logger.info(String.format("Header '%s' = %s", key, value)));
            return "";
        }

        PasswordGenerator passwordGenerator = new PasswordGenerator();
        passwordGenerator.setLength(config.length);
        passwordGenerator.setIncludeLowerCase(config.includeLowerCase);
        passwordGenerator.setIncludeUpperCase(config.includeUpperCase);
        passwordGenerator.setIncludeNumbers(config.includeNumbers);
        passwordGenerator.setIncludeSymbols(config.includeSymbols);

        logger.debug(String.format("Generating password using %s", config));

        return passwordGenerator.generate();
    }

    @Getter
    @Setter
    @Schema
    @ToString
    public static class PasswordGeneratorConfig {
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, defaultValue = "16", example = "16")
        private int length = 16;
        private boolean includeLowerCase = true;
        private boolean includeUpperCase = true;
        private boolean includeNumbers = true;
        private boolean includeSymbols = false;
    }
}
