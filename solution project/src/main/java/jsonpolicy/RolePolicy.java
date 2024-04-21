package jsonpolicy;

import com.google.gson.Gson;

public record RolePolicy(String PolicyName, PolicyDocument PolicyDocument) {
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
