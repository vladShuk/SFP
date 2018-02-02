package by.sfp.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
public class ViolationResponse {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, List<String>> fields;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> common;

    public static ViolationHandlerBuilder builder() {
        return new ViolationHandlerBuilder();
    }

    public static class ViolationHandlerBuilder {
        private ViolationResponse violationResponse = new ViolationResponse();

        public ViolationHandlerBuilder fields(Map<String, List<String>> fields) {
            violationResponse.fields = fields;
            return this;
        }

        public ViolationHandlerBuilder common(String common) {
            violationResponse.common = Collections.singletonList(common);
            return this;
        }

        public ViolationHandlerBuilder common(List<String> common) {
            violationResponse.common = common;
            return this;
        }

        public ViolationResponse build() {
            return violationResponse;
        }
    }
}
