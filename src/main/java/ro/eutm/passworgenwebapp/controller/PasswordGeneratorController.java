package ro.eutm.passworgenwebapp.controller;

import io.swagger.annotations.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.eu.passwallet.service.PasswordGenerator;

import java.util.Objects;

@RestController
@RequestMapping("/password-generator")
public class PasswordGeneratorController {
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("generates a random password based on the given configuration. " +
            "The minimum length of the password is 4." +
            "The maximum is 256." +
            "Example:" +
            "{\n" +
            "  \"includeLowerCase\": true,\n" +
            "  \"includeNumbers\": true,\n" +
            "  \"includeSymbols\": true,\n" +
            "  \"includeUpperCase\": true,\n" +
            "  \"length\": 16\n" +
            "}")
    public String generate(@RequestBody PasswordGeneratorConfig config) {
        Objects.nonNull(config);

        if (config.length > 256 || config.length < 4) {
            return "";
        }

        PasswordGenerator passwordGenerator = new PasswordGenerator();
        passwordGenerator.setLength(config.length);
        passwordGenerator.setIncludeLowerCase(config.includeLowerCase);
        passwordGenerator.setIncludeUpperCase(config.includeUpperCase);
        passwordGenerator.setIncludeNumbers(config.includeNumbers);
        passwordGenerator.setIncludeSymbols(config.includeSymbols);

        return passwordGenerator.generate();
    }

    @Getter
    @Setter
    @ApiModel
    public static class PasswordGeneratorConfig {
        @ApiModelProperty(position = 1, required = true, value = "16", example = "16")
        private int length = 16;
        private boolean includeLowerCase = true;
        private boolean includeUpperCase = true;
        private boolean includeNumbers = true;
        private boolean includeSymbols = false;
    }
}
