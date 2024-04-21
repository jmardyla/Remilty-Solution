package jsonpolicy;

import java.util.List;

public record Statement(String Effect, List<String> Action, String Resource) {}