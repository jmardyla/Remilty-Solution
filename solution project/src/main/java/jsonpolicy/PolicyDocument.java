package jsonpolicy;

import java.util.List;

public record PolicyDocument(String Version, List<Statement> Statement) {}